package com.codeforcommunity.processor;

import static org.jooq.generated.Tables.*;

import com.codeforcommunity.api.IReservationProcessor;
import com.codeforcommunity.auth.JWTData;
import com.codeforcommunity.dto.reservation.*;
import com.codeforcommunity.enums.PrivilegeLevel;
import com.codeforcommunity.enums.ReservationAction;
import com.codeforcommunity.exceptions.*;
import java.sql.Timestamp;
import java.util.Optional;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.*;

public class ReservationProcessorImpl implements IReservationProcessor {

  private final DSLContext db;

  public ReservationProcessorImpl(DSLContext db) {
    this.db = db;
  }

  public Optional<ReservationsRecord> lastAction(int blockId) {
    return Optional.ofNullable(
        db.selectFrom(RESERVATIONS)
            .where(RESERVATIONS.BLOCK_ID.eq(blockId))
            .orderBy(RESERVATIONS.PERFORMED_AT.desc())
            .limit(1)
            .fetchOne());
  }

  private void basicChecks(int blockId, Integer userId, Integer teamId) {
    if (!db.fetchExists(db.selectFrom(BLOCKS).where(BLOCKS.ID.eq(blockId)))) {
      throw new ResourceDoesNotExistException(blockId, "block");
    }

    if (userId != null) {
      if (!db.fetchExists(db.selectFrom(USERS).where(USERS.ID.eq(userId)))) {
        throw new UserDoesNotExistException(userId);
      }
    }

    if (teamId != null) {
      if (!db.fetchExists(db.selectFrom(TEAMS).where(TEAMS.ID.eq(teamId)))) {
        throw new ResourceDoesNotExistException(teamId, "team");
      }
    }

    if (userId != null && teamId != null) {
      if (!db.fetchExists(
          db.selectFrom(USERS_TEAMS)
              .where(USERS_TEAMS.USER_ID.eq(userId))
              .and(USERS_TEAMS.TEAM_ID.eq(teamId)))) {
        throw new UserNotOnTeamException(userId, teamId);
      }
    }
  }

  /**
   * Throws an exception if the user is not an admin or super admin.
   *
   * @param level the privilege level of the user calling the route
   */
  private void isAdminCheck(PrivilegeLevel level) {
    if (!(level.equals(PrivilegeLevel.ADMIN) || level.equals(PrivilegeLevel.SUPER_ADMIN))) {
      throw new AuthException("User does not have the required privilege level.");
    }
  }

  /**
   * Checks if the block is open, meaning users can reserve it. Blocks are open if there is no
   * record or if they've been released or uncompleted as the last action.
   *
   * @param blockId the id of the block to check
   */
  private void blockOpenCheck(int blockId) {
    Optional<ReservationsRecord> maybeReservation = lastAction(blockId);

    if (maybeReservation.isPresent()) {
      if (!(maybeReservation.get().getActionType().equals(ReservationAction.RELEASE.getName())
          || maybeReservation
              .get()
              .getActionType()
              .equals(ReservationAction.UNCOMPLETE.getName()))) {
        throw new IncorrectBlockStatusException(blockId, "open");
      }
    }
  }

  /**
   * Checks if the block is reserved by the user calling the route or a team they're on.
   *
   * @param blockId the id the block to check
   * @param userId the id of the user calling the route
   */
  private void blockReservedCheck(int blockId, int userId) {
    Optional<ReservationsRecord> maybeReservation = lastAction(blockId);

    // check if there are any entries
    if (!maybeReservation.isPresent()) {
      throw new IncorrectBlockStatusException(blockId, "reserved");
    }

    // check if the last entry was a reservation
    if (!maybeReservation.get().getActionType().equals(ReservationAction.RESERVE.getName())) {
      throw new IncorrectBlockStatusException(blockId, "reserved");
    }

    // check if the user reserved the block, if they did, return
    if (maybeReservation.get().getUserId() != null) {
      if (maybeReservation.get().getUserId().equals(userId)) {
        return;
      }
    }

    // check if a team the user is on reserved the block, if they did, return
    if (maybeReservation.get().getTeamId() != null) {
      int teamId = maybeReservation.get().getTeamId();
      if (db.fetchExists(
          db.selectFrom(USERS_TEAMS)
              .where(USERS_TEAMS.USER_ID.eq(userId))
              .and(USERS_TEAMS.TEAM_ID.eq(teamId)))) {
        return;
      }
    }

    // neither the user nor a team they are on reserved the block, so they did not reserve it
    throw new IncorrectBlockStatusException(blockId, "reserved");
  }

  /**
   * Checks if the block has been completed as its last action.
   *
   * @param blockId the id of the block to check
   */
  private void blockCompleteCheck(int blockId) {
    Optional<ReservationsRecord> maybeReservation = lastAction(blockId);

    if (maybeReservation.isPresent()) {
      if (!(maybeReservation.get().getActionType().equals(ReservationAction.COMPLETE.getName()))) {
        throw new IncorrectBlockStatusException(blockId, "complete");
      }
    } else {
      throw new IncorrectBlockStatusException(blockId, "complete");
    }
  }

  @Override
  public void makeReservation(JWTData userData, MakeReservationRequest makeReservationRequest) {
    basicChecks(
        makeReservationRequest.getBlockID(),
        userData.getUserId(),
        makeReservationRequest.getTeamID());

    ReservationsRecord reservationsRecord = db.newRecord(RESERVATIONS);
    reservationsRecord.setBlockId(makeReservationRequest.getBlockID());
    reservationsRecord.setUserId(userData.getUserId());
    reservationsRecord.setTeamId(makeReservationRequest.getTeamID());
    reservationsRecord.setActionType("reserve");
    reservationsRecord.setPerformedAt(new Timestamp(System.currentTimeMillis()));

    blockOpenCheck(makeReservationRequest.getBlockID());

    reservationsRecord.store();
  }

  @Override
  public void completeReservation(
      JWTData userData, CompleteReservationRequest completeReservationRequest) {
    basicChecks(
        completeReservationRequest.getBlockID(),
        userData.getUserId(),
        completeReservationRequest.getTeamID());

    ReservationsRecord reservationsRecord = db.newRecord(RESERVATIONS);
    reservationsRecord.setBlockId(completeReservationRequest.getBlockID());
    reservationsRecord.setUserId(userData.getUserId());
    reservationsRecord.setTeamId(completeReservationRequest.getTeamID());
    reservationsRecord.setActionType("complete");
    reservationsRecord.setPerformedAt(new Timestamp(System.currentTimeMillis()));

    blockReservedCheck(completeReservationRequest.getBlockID(), userData.getUserId());

    reservationsRecord.store();
  }

  @Override
  public void releaseReservation(
      JWTData userData, ReleaseReservationRequest releaseReservationRequest) {
    basicChecks(releaseReservationRequest.getBlockID(), userData.getUserId(), null);

    ReservationsRecord reservationsRecord = db.newRecord(RESERVATIONS);
    reservationsRecord.setBlockId(releaseReservationRequest.getBlockID());
    reservationsRecord.setUserId(userData.getUserId());
    reservationsRecord.setActionType("release");
    reservationsRecord.setPerformedAt(new Timestamp(System.currentTimeMillis()));

    blockReservedCheck(userData.getUserId(), releaseReservationRequest.getBlockID());

    reservationsRecord.store();
  }

  @Override
  public void uncompleteReservation(
      JWTData userData, UncompleteReservationRequest uncompleteReservationRequest) {
    isAdminCheck(userData.getPrivilegeLevel());
    basicChecks(uncompleteReservationRequest.getBlockID(), userData.getUserId(), null);

    ReservationsRecord reservationsRecord = db.newRecord(RESERVATIONS);
    reservationsRecord.setBlockId(uncompleteReservationRequest.getBlockID());
    reservationsRecord.setUserId(userData.getUserId());
    reservationsRecord.setActionType("uncomplete");
    reservationsRecord.setPerformedAt(new Timestamp(System.currentTimeMillis()));

    blockCompleteCheck(uncompleteReservationRequest.getBlockID());

    reservationsRecord.store();
  }

  @Override
  public void markForQA(JWTData userData, MarkForQARequest markForQARequest) {
    isAdminCheck(userData.getPrivilegeLevel());
    basicChecks(markForQARequest.getBlockID(), userData.getUserId(), null);

    ReservationsRecord reservationsRecord = db.newRecord(RESERVATIONS);
    reservationsRecord.setBlockId(markForQARequest.getBlockID());
    reservationsRecord.setUserId(userData.getUserId());
    reservationsRecord.setActionType("qa");
    reservationsRecord.setPerformedAt(new Timestamp(System.currentTimeMillis()));

    blockCompleteCheck(markForQARequest.getBlockID());

    reservationsRecord.store();
  }
}
