package com.codeforcommunity.processor;

import static org.jooq.generated.Tables.ADOPTED_SITES;
import static org.jooq.generated.Tables.SITES;
import static org.jooq.generated.Tables.SITE_ENTRIES;
import static org.jooq.generated.Tables.STEWARDSHIP;

import com.codeforcommunity.api.IProtectedSiteProcessor;
import com.codeforcommunity.auth.JWTData;
import com.codeforcommunity.dto.site.AddSiteRequest;
import com.codeforcommunity.dto.site.AdoptedSitesResponse;
import com.codeforcommunity.dto.site.EditSiteRequest;
import com.codeforcommunity.dto.site.RecordStewardshipRequest;
import com.codeforcommunity.dto.site.UpdateSiteRequest;
import com.codeforcommunity.enums.PrivilegeLevel;
import com.codeforcommunity.exceptions.AuthException;
import com.codeforcommunity.exceptions.ResourceDoesNotExistException;
import com.codeforcommunity.exceptions.WrongAdoptionStatusException;
import java.sql.Timestamp;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.generated.tables.records.AdoptedSitesRecord;
import org.jooq.generated.tables.records.SiteEntriesRecord;
import org.jooq.generated.tables.records.SitesRecord;
import org.jooq.generated.tables.records.StewardshipRecord;

public class ProtectedSiteProcessorImpl implements IProtectedSiteProcessor {

  private final DSLContext db;

  public ProtectedSiteProcessorImpl(DSLContext db) {
    this.db = db;
  }

  private void checkSiteExists(int siteId) {
    if (!db.fetchExists(db.selectFrom(SITES).where(SITES.ID.eq(siteId)))) {
      throw new ResourceDoesNotExistException(siteId, "Site");
    }
  }

  private Boolean isAlreadyAdopted(int siteId) {
    return db.fetchExists(db.selectFrom(ADOPTED_SITES).where(ADOPTED_SITES.SITE_ID.eq(siteId)));
  }

  private Boolean isAlreadyAdoptedByUser(int userId, int siteId) {
    return db.fetchExists(
        db.selectFrom(ADOPTED_SITES)
            .where(ADOPTED_SITES.USER_ID.eq(userId))
            .and(ADOPTED_SITES.SITE_ID.eq(siteId)));
  }

  /**
   * Throws an exception if the user is not an admin or super admin.
   *
   * @param level the privilege level of the user calling the route
   */
  void isAdminCheck(PrivilegeLevel level) {
    if (!(level.equals(PrivilegeLevel.ADMIN) || level.equals(PrivilegeLevel.SUPER_ADMIN))) {
      throw new AuthException("User does not have the required privilege level.");
    }
  }

  @Override
  public void adoptSite(JWTData userData, int siteId) {
    checkSiteExists(siteId);
    if (isAlreadyAdopted(siteId)) {
      throw new WrongAdoptionStatusException(true);
    }

    AdoptedSitesRecord record = db.newRecord(ADOPTED_SITES);
    record.setUserId(userData.getUserId());
    record.setSiteId(siteId);
    record.store();
  }

  @Override
  public void unadoptSite(JWTData userData, int siteId) {
    checkSiteExists(siteId);
    if (!isAlreadyAdoptedByUser(userData.getUserId(), siteId)) {
      throw new WrongAdoptionStatusException(false);
    }

    db.deleteFrom(ADOPTED_SITES)
        .where(ADOPTED_SITES.USER_ID.eq(userData.getUserId()))
        .and(ADOPTED_SITES.SITE_ID.eq(siteId))
        .execute();
  }

  @Override
  public AdoptedSitesResponse getAdoptedSites(JWTData userData) {
    List<Integer> favoriteSites =
        db.selectFrom(ADOPTED_SITES)
            .where(ADOPTED_SITES.USER_ID.eq(userData.getUserId()))
            .fetch(ADOPTED_SITES.SITE_ID);

    return new AdoptedSitesResponse(favoriteSites);
  }

  @Override
  public void recordStewardship(
      JWTData userData, int siteId, RecordStewardshipRequest recordStewardshipRequest) {
    checkSiteExists(siteId);

    StewardshipRecord record = db.newRecord(STEWARDSHIP);
    record.setUserId(userData.getUserId());
    record.setSiteId(siteId);
    record.setPerformedOn(recordStewardshipRequest.getDate());
    record.setWatered(recordStewardshipRequest.getWatered());
    record.setMulched(recordStewardshipRequest.getMulched());
    record.setCleaned(recordStewardshipRequest.getCleaned());
    record.setWeeded(recordStewardshipRequest.getWeeded());

    record.store();
  }

  @Override
  public void addSite(JWTData userData, AddSiteRequest addSiteRequest) {
    SitesRecord sitesRecord = db.newRecord(SITES);

    sitesRecord.setBlockId(addSiteRequest.getBlockId());
    sitesRecord.setLat(addSiteRequest.getLat());
    sitesRecord.setLng(addSiteRequest.getLng());
    sitesRecord.setCity(addSiteRequest.getCity());
    sitesRecord.setZip(addSiteRequest.getZip());
    sitesRecord.setAddress(addSiteRequest.getAddress());

    sitesRecord.store();

    SiteEntriesRecord siteEntriesRecord = db.newRecord(SITE_ENTRIES);

    siteEntriesRecord.setUserId(userData.getUserId());
    siteEntriesRecord.setSiteId(sitesRecord.getId());
    siteEntriesRecord.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
    siteEntriesRecord.setTreePresent(addSiteRequest.isTreePresent());
    siteEntriesRecord.setStatus(addSiteRequest.getStatus());
    siteEntriesRecord.setGenus(addSiteRequest.getGenus());
    siteEntriesRecord.setSpecies(addSiteRequest.getSpecies());
    siteEntriesRecord.setCommonName(addSiteRequest.getCommonName());
    siteEntriesRecord.setConfidence(addSiteRequest.getConfidence());
    siteEntriesRecord.setDiameter(addSiteRequest.getDiameter());
    siteEntriesRecord.setCircumference(addSiteRequest.getCircumference());
    siteEntriesRecord.setMultistem(addSiteRequest.isMultistem());
    siteEntriesRecord.setCoverage(addSiteRequest.getCoverage());
    siteEntriesRecord.setPruning(addSiteRequest.getPruning());
    siteEntriesRecord.setCondition(addSiteRequest.getCondition());
    siteEntriesRecord.setDiscoloring(addSiteRequest.isDiscoloring());
    siteEntriesRecord.setLeaning(addSiteRequest.isLeaning());
    siteEntriesRecord.setConstrictingGate(addSiteRequest.isConstrictingGate());
    siteEntriesRecord.setWounds(addSiteRequest.isWounds());
    siteEntriesRecord.setPooling(addSiteRequest.isPooling());
    siteEntriesRecord.setStakesWithWires(addSiteRequest.isStakesWithWires());
    siteEntriesRecord.setStakesWithoutWires(addSiteRequest.isStakesWithoutWires());
    siteEntriesRecord.setLight(addSiteRequest.isLight());
    siteEntriesRecord.setBicycle(addSiteRequest.isBicycle());
    siteEntriesRecord.setBagEmpty(addSiteRequest.isBagEmpty());
    siteEntriesRecord.setBagFilled(addSiteRequest.isBagFilled());
    siteEntriesRecord.setTape(addSiteRequest.isTape());
    siteEntriesRecord.setSuckerGrowth(addSiteRequest.isSuckerGrowth());
    siteEntriesRecord.setSiteType(addSiteRequest.getSiteType());
    siteEntriesRecord.setSidewalkWidth(addSiteRequest.getSidewalkWidth());
    siteEntriesRecord.setSiteWidth(addSiteRequest.getSiteWidth());
    siteEntriesRecord.setSiteLength(addSiteRequest.getSiteLength());
    siteEntriesRecord.setMaterial(addSiteRequest.getMaterial());
    siteEntriesRecord.setRaisedBed(addSiteRequest.isRaisedBed());
    siteEntriesRecord.setFence(addSiteRequest.isFence());
    siteEntriesRecord.setTrash(addSiteRequest.isTrash());
    siteEntriesRecord.setWires(addSiteRequest.isWires());
    siteEntriesRecord.setGrate(addSiteRequest.isGrate());
    siteEntriesRecord.setStump(addSiteRequest.isStump());
    siteEntriesRecord.setTreeNotes(addSiteRequest.getTreeNotes());
    siteEntriesRecord.setSiteNotes(addSiteRequest.getSiteNotes());

    siteEntriesRecord.store();
  }

  @Override
  public void updateSite(JWTData userData, int siteId, UpdateSiteRequest updateSiteRequest) {
    checkSiteExists(siteId);

    SiteEntriesRecord record = db.newRecord(SITE_ENTRIES);

    record.setUserId(userData.getUserId());
    record.setSiteId(siteId);
    record.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
    record.setTreePresent(updateSiteRequest.isTreePresent());
    record.setStatus(updateSiteRequest.getStatus());
    record.setGenus(updateSiteRequest.getGenus());
    record.setSpecies(updateSiteRequest.getSpecies());
    record.setCommonName(updateSiteRequest.getCommonName());
    record.setConfidence(updateSiteRequest.getConfidence());
    record.setDiameter(updateSiteRequest.getDiameter());
    record.setCircumference(updateSiteRequest.getCircumference());
    record.setMultistem(updateSiteRequest.isMultistem());
    record.setCoverage(updateSiteRequest.getCoverage());
    record.setPruning(updateSiteRequest.getPruning());
    record.setCondition(updateSiteRequest.getCondition());
    record.setDiscoloring(updateSiteRequest.isDiscoloring());
    record.setLeaning(updateSiteRequest.isLeaning());
    record.setConstrictingGate(updateSiteRequest.isConstrictingGate());
    record.setWounds(updateSiteRequest.isWounds());
    record.setPooling(updateSiteRequest.isPooling());
    record.setStakesWithWires(updateSiteRequest.isStakesWithWires());
    record.setStakesWithoutWires(updateSiteRequest.isStakesWithoutWires());
    record.setLight(updateSiteRequest.isLight());
    record.setBicycle(updateSiteRequest.isBicycle());
    record.setBagEmpty(updateSiteRequest.isBagEmpty());
    record.setBagFilled(updateSiteRequest.isBagFilled());
    record.setTape(updateSiteRequest.isTape());
    record.setSuckerGrowth(updateSiteRequest.isSuckerGrowth());
    record.setSiteType(updateSiteRequest.getSiteType());
    record.setSidewalkWidth(updateSiteRequest.getSidewalkWidth());
    record.setSiteWidth(updateSiteRequest.getSiteWidth());
    record.setSiteLength(updateSiteRequest.getSiteLength());
    record.setMaterial(updateSiteRequest.getMaterial());
    record.setRaisedBed(updateSiteRequest.isRaisedBed());
    record.setFence(updateSiteRequest.isFence());
    record.setTrash(updateSiteRequest.isTrash());
    record.setWires(updateSiteRequest.isWires());
    record.setGrate(updateSiteRequest.isGrate());
    record.setStump(updateSiteRequest.isStump());
    record.setTreeNotes(updateSiteRequest.getTreeNotes());
    record.setSiteNotes(updateSiteRequest.getSiteNotes());

    record.store();
  }

  @Override
  public void editSite(JWTData userData, int siteId, EditSiteRequest editSiteRequest) {
    isAdminCheck(userData.getPrivilegeLevel());
    checkSiteExists(siteId);

    SitesRecord site = db.selectFrom(SITES).where(SITES.ID.eq(siteId)).fetchOne();

    site.setId(siteId);
    site.setBlockId(editSiteRequest.getBlockId());
    site.setAddress(editSiteRequest.getAddress());
    site.setCity(editSiteRequest.getCity());
    site.setZip(editSiteRequest.getZip());
    site.setLat(editSiteRequest.getLat());
    site.setLng(editSiteRequest.getLng());
    site.setNeighborhoodId(editSiteRequest.getNeighborhoodId());

    site.store();
  }

  @Override
  public void deleteSite(JWTData userData, int siteId) {
    isAdminCheck(userData.getPrivilegeLevel());
    checkSiteExists(siteId);

    SitesRecord site = db.selectFrom(SITES).where(SITES.ID.eq(siteId)).fetchOne();
    site.setDeletedAt(new Timestamp(System.currentTimeMillis()));
    site.store();
  }

  public void deleteStewardship(JWTData userData, int activityId) {
    StewardshipRecord activity =
        db.selectFrom(STEWARDSHIP).where(STEWARDSHIP.ID.eq(activityId)).fetchOne();

    if (activity == null) {
      throw new ResourceDoesNotExistException(activityId, "Stewardship Activity");
    }
    if (!(activity.getUserId().equals(userData.getUserId())
        || userData.getPrivilegeLevel().equals(PrivilegeLevel.SUPER_ADMIN)
        || userData.getPrivilegeLevel().equals(PrivilegeLevel.ADMIN))) {
      throw new AuthException(
          "User needs to be an admin or the activity's author to delete the record.");
    }

    db.deleteFrom(STEWARDSHIP).where(STEWARDSHIP.ID.eq(activityId)).execute();
  }
}
