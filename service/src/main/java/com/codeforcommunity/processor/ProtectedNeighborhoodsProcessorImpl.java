package com.codeforcommunity.processor;

import static org.jooq.generated.Tables.NEIGHBORHOODS;

import com.codeforcommunity.api.IProtectedNeighborhoodsProcessor;
import com.codeforcommunity.auth.JWTData;
import com.codeforcommunity.dto.neighborhoods.EditCanopyCoverageRequest;
import com.codeforcommunity.dto.neighborhoods.SendEmailRequest;
import com.codeforcommunity.enums.PrivilegeLevel;
import com.codeforcommunity.exceptions.AuthException;
import com.codeforcommunity.exceptions.MalformedParameterException;
import com.codeforcommunity.exceptions.ResourceDoesNotExistException;
import com.codeforcommunity.requester.Emailer;

import org.jooq.DSLContext;

import org.jooq.generated.tables.records.NeighborhoodsRecord;

public class ProtectedNeighborhoodsProcessorImpl implements IProtectedNeighborhoodsProcessor {
  private final DSLContext db;
  private final Emailer emailer;

  public ProtectedNeighborhoodsProcessorImpl(DSLContext db, Emailer emailer) {
    this.db = db;
    this.emailer = emailer;
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




  public void editCanopyCoverage(
          JWTData userData, int neighborhoodID, EditCanopyCoverageRequest editCanopyCoverageRequest) {
    isAdminCheck(userData.getPrivilegeLevel());
    checkNeighborhoodExists(neighborhoodID);

    Double canopyCoverage = editCanopyCoverageRequest.getCanopyCoverage();
    checkIfCanopyCoverageValid(canopyCoverage);
    NeighborhoodsRecord record = db.selectFrom(NEIGHBORHOODS).where(NEIGHBORHOODS.ID.eq(neighborhoodID)).fetchOne();

    record.setCanopyCoverage(canopyCoverage);

    record.store();
  }

  /**
   * Check if a neighborhood with the given neighborhoodId exists.
   *
   * @param neighborhoodId to check
   */
  private void checkNeighborhoodExists(int neighborhoodId) {
    if (!db.fetchExists(db.selectFrom(NEIGHBORHOODS).where(NEIGHBORHOODS.ID.eq(neighborhoodId)))) {
      throw new ResourceDoesNotExistException(neighborhoodId, "Neighborhood");
    }
  }

  /**
   * Checks if the given canopyCoverage is valid.
   * An invalid canopyCoverage is negative or greater than one.
   *
   * @param canopyCoverage to check
   */
  private void checkIfCanopyCoverageValid(Double canopyCoverage) {
    if (canopyCoverage < 0 || canopyCoverage > 1) {
      throw new MalformedParameterException("Given canopy coverage is not valid: " + canopyCoverage);
    }
  }
}