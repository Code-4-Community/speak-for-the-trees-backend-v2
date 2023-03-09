package com.codeforcommunity.processor;

import static org.jooq.generated.Tables.ADOPTED_SITES;
import static org.jooq.generated.Tables.BLOCKS;
import static org.jooq.generated.Tables.NEIGHBORHOODS;
import static org.jooq.generated.Tables.PARENT_ACCOUNTS;
import static org.jooq.generated.Tables.SITES;
import static org.jooq.generated.Tables.SITE_ENTRIES;
import static org.jooq.generated.Tables.SITE_IMAGES;
import static org.jooq.generated.Tables.STEWARDSHIP;
import static org.jooq.generated.Tables.USERS;
import static org.jooq.impl.DSL.max;

import com.codeforcommunity.api.IProtectedSiteProcessor;
import com.codeforcommunity.auth.JWTData;
import com.codeforcommunity.dto.site.AddSiteRequest;
import com.codeforcommunity.dto.site.AddSitesRequest;
import com.codeforcommunity.dto.site.AdoptedSitesResponse;
import com.codeforcommunity.dto.site.EditSiteRequest;
import com.codeforcommunity.dto.site.EditStewardshipRequest;
import com.codeforcommunity.dto.site.FilterSitesRequest;
import com.codeforcommunity.dto.site.NameSiteEntryRequest;
import com.codeforcommunity.dto.site.ParentAdoptSiteRequest;
import com.codeforcommunity.dto.site.ParentRecordStewardshipRequest;
import com.codeforcommunity.dto.site.RecordStewardshipRequest;
import com.codeforcommunity.dto.site.UpdateSiteRequest;
import com.codeforcommunity.dto.site.UploadSiteImageRequest;
import com.codeforcommunity.enums.PrivilegeLevel;
import com.codeforcommunity.exceptions.AuthException;
import com.codeforcommunity.exceptions.HandledException;
import com.codeforcommunity.exceptions.InvalidCSVException;
import com.codeforcommunity.exceptions.LinkedResourceDoesNotExistException;
import com.codeforcommunity.exceptions.ResourceDoesNotExistException;
import com.codeforcommunity.exceptions.WrongAdoptionStatusException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.Result;
import org.jooq.SelectConditionStep;
import org.jooq.generated.tables.records.AdoptedSitesRecord;
import org.jooq.generated.tables.records.ParentAccountsRecord;
import org.jooq.generated.tables.records.SiteEntriesRecord;
import org.jooq.generated.tables.records.SiteImagesRecord;
import org.jooq.generated.tables.records.SitesRecord;
import org.jooq.generated.tables.records.StewardshipRecord;
import org.jooq.generated.tables.records.UsersRecord;

public class ProtectedSiteProcessorImpl extends AbstractProcessor
    implements IProtectedSiteProcessor {

  private final DSLContext db;

  public ProtectedSiteProcessorImpl(DSLContext db) {
    this.db = db;
  }

  /**
   * Check if a site with the given siteId exists.
   *
   * @param siteId to check
   */
  private void checkSiteExists(int siteId) {
    if (!db.fetchExists(db.selectFrom(SITES).where(SITES.ID.eq(siteId)))) {
      throw new ResourceDoesNotExistException(siteId, "Site");
    }
  }

  /**
   * Check if a block with the given blockId exists.
   *
   * @param blockId to check
   */
  private void checkBlockExists(int blockId) {
    if (!db.fetchExists(db.selectFrom(BLOCKS).where(BLOCKS.ID.eq(blockId)))) {
      throw new ResourceDoesNotExistException(blockId, "Block");
    }
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
   * Check if a stewardship record exists.
   *
   * @param activityId to check
   */
  private void checkStewardshipExists(int activityId) {
    if (!db.fetchExists(db.selectFrom(STEWARDSHIP).where(STEWARDSHIP.ID.eq(activityId)))) {
      throw new ResourceDoesNotExistException(activityId, "Stewardship Activity");
    }
  }

  /**
   * Check if an image exists
   *
   * @param imageId to check
   */
  private void checkImageExists(int imageId) {
    if (!db.fetchExists(db.selectFrom(SITE_IMAGES).where(SITE_IMAGES.ID.eq(imageId)))) {
      throw new ResourceDoesNotExistException(imageId, "Site Image");
    }
  }

  /**
   * Check if the user is an admin or the adopter of the site with the given siteId
   *
   * @param userData the user's data
   * @param siteId the ID of the site to check
   * @throws AuthException if the user is not an admin or the site's adopter
   */
  private void checkAdminOrSiteAdopter(JWTData userData, int siteId) throws AuthException {
    if (!(isAdmin(userData.getPrivilegeLevel())
        || isAlreadyAdoptedByUser(userData.getUserId(), siteId))) {
      throw new AuthException("User needs to be an admin or the site's adopter.");
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
   * Is the user an admin or super admin.
   *
   * @param level the privilege level of the user calling the route
   * @return true if user is ADMIN or SUPER_ADMIN, else false
   */
  boolean isAdmin(PrivilegeLevel level) {
    return level.equals(PrivilegeLevel.ADMIN) || level.equals(PrivilegeLevel.SUPER_ADMIN);
  }

  private boolean isAdminOrOwner(JWTData userData, Integer ownerId) {
    return isAdmin(userData.getPrivilegeLevel()) || userData.getUserId().equals(ownerId);
  }

  /**
   * Throws an exception if the user account is not the parent of the other user account.
   *
   * @param parentUserId the user id of the parent account
   * @param childUserId the user id of the child account
   */
  void checkParent(int parentUserId, int childUserId) {
    if (!isParent(parentUserId, childUserId)) {
      throw new LinkedResourceDoesNotExistException(
          "Parent->Child", parentUserId, "Parent User", childUserId, "Child User");
    }
  }

  /**
   * Determines if a user account is the parent of another user account.
   *
   * @param parentUserId the user id of the parent account
   * @param childUserId the user if of the child account
   * @return true if the user is a parent of the other user, else false
   */
  boolean isParent(int parentUserId, int childUserId) {
    ParentAccountsRecord parentAccountsRecord =
        db.selectFrom(PARENT_ACCOUNTS)
            .where(PARENT_ACCOUNTS.PARENT_ID.eq(parentUserId))
            .and(PARENT_ACCOUNTS.CHILD_ID.eq(childUserId))
            .fetchOne();
    return parentAccountsRecord != null;
  }

  /**
   * Gets the JWTData of the user with the given userId.
   *
   * @param userId user id of the user to get JWTData for
   * @return JWTData of the user
   */
  private JWTData getUserData(int userId) {
    UsersRecord user = db.selectFrom(USERS).where(USERS.ID.eq(userId)).fetchOne();
    PrivilegeLevel userPrivilegeLevel = user.getPrivilegeLevel();

    return new JWTData(userId, userPrivilegeLevel);
  }

  @Override
  public void adoptSite(JWTData userData, int siteId, Date dateAdopted) {
    checkSiteExists(siteId);
    if (isAlreadyAdopted(siteId)) {
      throw new WrongAdoptionStatusException(true);
    }

    AdoptedSitesRecord record = db.newRecord(ADOPTED_SITES);
    record.setUserId(userData.getUserId());
    record.setSiteId(siteId);
    record.setDateAdopted(dateAdopted);
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
  public void forceUnadoptSite(JWTData userData, int siteId) {
    assertAdminOrSuperAdmin(userData.getPrivilegeLevel());
    checkSiteExists(siteId);
    if (!isAlreadyAdopted(siteId)) {
      throw new WrongAdoptionStatusException(false);
    }

    AdoptedSitesRecord adoptedSite =
        db.selectFrom(ADOPTED_SITES)
            .where(ADOPTED_SITES.SITE_ID.eq(siteId))
            .fetchInto(AdoptedSitesRecord.class)
            .get(0);

    Integer adopterId = adoptedSite.getUserId();

    UsersRecord adopter = db.selectFrom(USERS).where(USERS.ID.eq(adopterId)).fetchOne();

    if (isAdmin(adopter.getPrivilegeLevel())
        && !(userData.getPrivilegeLevel().equals(PrivilegeLevel.SUPER_ADMIN))) {
      throw new AuthException("User does not have the required privilege level.");
    }

    db.deleteFrom(ADOPTED_SITES).where(ADOPTED_SITES.SITE_ID.eq(siteId)).execute();
  }

  @Override
  public void parentAdoptSite(
      JWTData parentUserData,
      int siteId,
      ParentAdoptSiteRequest parentAdoptSiteRequest,
      Date dateAdopted) {
    Integer parentId = parentUserData.getUserId();
    Integer childId = parentAdoptSiteRequest.getChildUserId();
    checkParent(parentId, childId);

    JWTData childUserData = getUserData(childId);

    adoptSite(childUserData, siteId, dateAdopted);
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
    if (!isAlreadyAdoptedByUser(userData.getUserId(), siteId)) {
      throw new WrongAdoptionStatusException(false);
    }

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
  public void parentRecordStewardship(
      JWTData parentUserData,
      int siteId,
      ParentRecordStewardshipRequest parentRecordStewardshipRequest) {
    Integer parentId = parentUserData.getUserId();
    Integer childId = parentRecordStewardshipRequest.getChildUserId();
    checkParent(parentId, childId);

    JWTData childUserData = getUserData(childId);

    recordStewardship(childUserData, siteId, parentRecordStewardshipRequest);
  }

  @Override
  public void addSite(JWTData userData, AddSiteRequest addSiteRequest) {
    if (addSiteRequest.getBlockId() != null) {
      checkBlockExists(addSiteRequest.getBlockId());
    }

    checkNeighborhoodExists(addSiteRequest.getNeighborhoodId());

    SitesRecord sitesRecord = db.newRecord(SITES);

    int newId = db.select(max(SITES.ID)).from(SITES).fetchOne(0, Integer.class) + 1;

    sitesRecord.setId(newId);
    sitesRecord.setBlockId(addSiteRequest.getBlockId());
    sitesRecord.setLat(addSiteRequest.getLat());
    sitesRecord.setLng(addSiteRequest.getLng());
    sitesRecord.setCity(addSiteRequest.getCity());
    sitesRecord.setZip(addSiteRequest.getZip());
    sitesRecord.setAddress(addSiteRequest.getAddress());
    sitesRecord.setNeighborhoodId(addSiteRequest.getNeighborhoodId());

    sitesRecord.store();

    SiteEntriesRecord siteEntriesRecord = db.newRecord(SITE_ENTRIES);

    int newSiteEntriesId =
        db.select(max(SITE_ENTRIES.ID)).from(SITE_ENTRIES).fetchOne(0, Integer.class) + 1;

    siteEntriesRecord.setId(newSiteEntriesId);
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
    siteEntriesRecord.setConstrictingGrate(addSiteRequest.isConstrictingGrate());
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

  public void updateSite(JWTData userData, int siteId, UpdateSiteRequest updateSiteRequest) {
    checkSiteExists(siteId);

    SiteEntriesRecord record = db.newRecord(SITE_ENTRIES);

    int newId = db.select(max(SITE_ENTRIES.ID)).from(SITE_ENTRIES).fetchOne(0, Integer.class) + 1;

    record.setId(newId);
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
    record.setConstrictingGrate(updateSiteRequest.isConstrictingGrate());
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
    assertAdminOrSuperAdmin(userData.getPrivilegeLevel());
    checkSiteExists(siteId);
    if (editSiteRequest.getBlockId() != null) {
      checkBlockExists(editSiteRequest.getBlockId());
    }
    checkNeighborhoodExists(editSiteRequest.getNeighborhoodId());

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
  public void addSites(JWTData userData, AddSitesRequest addSitesRequest) {
    assertAdminOrSuperAdmin(userData.getPrivilegeLevel());

    List<AddSiteRequest> addSiteRequests = this.parseCSVString(addSitesRequest.getCsvText());

    addSiteRequests.forEach(siteRequest -> addSite(userData, siteRequest));
  }

  /**
   * Parses the given CSV string containing data on a site and site entry to a list of
   * AddSiteRequests.
   *
   * @param sitesCSV CSV string to parse
   * @throws HandledException if the given CSV string cannot be parsed properly
   * @return the parsed list of AddSiteRequests
   */
  private List<AddSiteRequest> parseCSVString(String sitesCSV) throws HandledException {
    try {
      CsvMapper mapper = new CsvMapper();
      CsvSchema schema = CsvSchema.emptySchema().withHeader();
      MappingIterator<AddSiteRequest> sitesIterator =
          mapper.readerFor(AddSiteRequest.class).with(schema).readValues(sitesCSV);
      List<AddSiteRequest> addSiteRequests = sitesIterator.readAll();
      if (addSiteRequests.size() == 0) {
        throw new InvalidCSVException();
      }
      addSiteRequests.forEach(siteRequest -> siteRequest.validate());
      return addSiteRequests;
    } catch (HandledException | IOException e) {
      throw new InvalidCSVException();
    }
  }

  @Override
  public void deleteSite(JWTData userData, int siteId) {
    assertAdminOrSuperAdmin(userData.getPrivilegeLevel());
    checkSiteExists(siteId);

    SitesRecord site = db.selectFrom(SITES).where(SITES.ID.eq(siteId)).fetchOne();
    site.setDeletedAt(new Timestamp(System.currentTimeMillis()));
    site.store();
  }

  @Override
  public void editStewardship(
      JWTData userData, int activityId, EditStewardshipRequest editStewardshipRequest) {
    checkStewardshipExists(activityId);
    StewardshipRecord activity =
        db.selectFrom(STEWARDSHIP).where(STEWARDSHIP.ID.eq(activityId)).fetchOne();

    if (!isAdminOrOwner(userData, activity.getUserId())) {
      throw new AuthException(
          "User needs to be an admin or the activity's author to edit the record.");
    }

    activity.setPerformedOn(editStewardshipRequest.getDate());
    activity.setWatered(editStewardshipRequest.getWatered());
    activity.setMulched(editStewardshipRequest.getMulched());
    activity.setCleaned(editStewardshipRequest.getCleaned());
    activity.setWeeded(editStewardshipRequest.getWeeded());

    activity.store();
  }

  public void deleteStewardship(JWTData userData, int activityId) {
    checkStewardshipExists(activityId);
    StewardshipRecord activity =
        db.selectFrom(STEWARDSHIP).where(STEWARDSHIP.ID.eq(activityId)).fetchOne();

    if (!isAdminOrOwner(userData, activity.getUserId())) {
      throw new AuthException(
          "User needs to be an admin or the activity's author to delete the record.");
    }

    db.deleteFrom(STEWARDSHIP).where(STEWARDSHIP.ID.eq(activityId)).execute();
  }

  public void nameSiteEntry(
      JWTData userData, int siteId, NameSiteEntryRequest nameSiteEntryRequest) {
    checkSiteExists(siteId);
    checkAdminOrSiteAdopter(userData, siteId);

    SiteEntriesRecord siteEntry =
        db.selectFrom(SITE_ENTRIES)
            .where(SITE_ENTRIES.SITE_ID.eq(siteId))
            .orderBy(SITE_ENTRIES.UPDATED_AT.desc())
            .fetchOne();

    if (siteEntry == null) {
      throw new LinkedResourceDoesNotExistException(
          "Site Entry", userData.getUserId(), "User", siteId, "Site");
    }

    if (nameSiteEntryRequest.getName().isEmpty()) {
      siteEntry.setTreeName(null);
    } else {
      siteEntry.setTreeName(nameSiteEntryRequest.getName());
    }

    siteEntry.store();
  }

  @Override
  public void uploadSiteImage(
      JWTData userData, int siteId, UploadSiteImageRequest uploadSiteImageRequest) {
    checkSiteExists(siteId);
    checkAdminOrSiteAdopter(userData, siteId);

    // TODO upload image to S3 and save URL to database

    // SitesRecord site = db.selectFrom(SITES).where(SITES.ID.eq(siteId)).fetchOne();

    // site.store();
  }

  @Override
  public void deleteSiteImage(JWTData userData, int imageId) {
    assertAdminOrSuperAdmin(userData.getPrivilegeLevel());
    checkImageExists(imageId);

    db.deleteFrom(SITE_IMAGES).where(SITE_IMAGES.ID.eq(imageId)).execute();
  }
}

  @Override
  public List<Integer> filterSites(JWTData userData, FilterSitesRequest filterSitesRequest) {

    Collection<Integer> filteredSiteIds = filterSitesRequest.getNeighborhoodIds() == null
        ? db.select(SITES.ID).from(SITES).fetch(SITES.ID)
        : db.select(SITES.ID).from(SITES).where(SITES.NEIGHBORHOOD_ID
        .in(filterSitesRequest.getNeighborhoodIds())).fetch(SITES.ID);

    filteredSiteIds = filterByAdopted(filterSitesRequest.getAdoptedStart(), filterSitesRequest.getAdoptedEnd(), filteredSiteIds);
    filteredSiteIds = filterByActivity(filterSitesRequest.getLastActivityStart(), filterSitesRequest.getLastActivityEnd(), filteredSiteIds);
    filteredSiteIds = filterByTreeSpecies(filterSitesRequest.getTreeSpecies(), filteredSiteIds);

    return filteredSiteIds;
  }

  private Collection<Integer> filterByAdopted(Date adoptedStart, Date adoptedEnd, Collection<Integer> filteredSiteIds) {
    if (adoptedStart == null && adoptedEnd == null) {
      return filteredSiteIds;
    }

    SelectConditionStep<AdoptedSitesRecord> select = db
        .selectFrom(ADOPTED_SITES)
        .where(ADOPTED_SITES.SITE_ID.in(filteredSiteIds));

    if (adoptedStart != null) {
      select = select.and(ADOPTED_SITES.DATE_ADOPTED.greaterOrEqual(adoptedStart));
    }
    if (adoptedEnd != null) {
      select = select.and(ADOPTED_SITES.DATE_ADOPTED.lessOrEqual(adoptedEnd));
    }

    return select.fetch(ADOPTED_SITES.SITE_ID);
  }

  private Collection<Integer> filterByActivity(Date activityStart, Date activityEnd, Collection<Integer> filteredSiteIds) {
    if (activityStart == null && activityEnd == null) {
      return filteredSiteIds;
    }

    Result<Record2<Date, Integer>> latestActivities = db
        .select(max(STEWARDSHIP.PERFORMED_ON).as(STEWARDSHIP.PERFORMED_ON.getName()), STEWARDSHIP.SITE_ID)
        .from(STEWARDSHIP)
        .where(STEWARDSHIP.SITE_ID.in(filteredSiteIds)).fetch();

    return latestActivities.stream()
        .filter(r -> filterDate(r.get(STEWARDSHIP.PERFORMED_ON), activityStart, activityEnd))
        .map(r -> r.get(STEWARDSHIP.SITE_ID))
        .collect(Collectors.toList());

//    select max(performed_on) as performed_on, site_id from public.stewardship
//    where performed_on < '2023-01-11'
//    group by site_id;
  }

  private Collection<Integer> filterByTreeSpecies(List<String> treeSpecies, Collection<Integer> filteredSiteIds) {
    // select max(id) as id, site_id, species from public.site_entries group by site_id, species order by id asc
    SelectConditionStep<Record3<Integer, Integer, String>> select = db
        .select(max(SITE_ENTRIES.ID).as(SITE_ENTRIES.ID.getName()), SITE_ENTRIES.SITE_ID, SITE_ENTRIES.SPECIES)
        .from(SITE_ENTRIES)
        .where(SITE_ENTRIES.SITE_ID.in(filteredSiteIds));

    if (treeSpecies == null) {
      return select
          .groupBy(SITE_ENTRIES.SITE_ID, SITE_ENTRIES.SPECIES)
          .fetch(SITE_ENTRIES.SITE_ID);
    } else {
      return select
          .and(SITE_ENTRIES.SPECIES.in(treeSpecies))
          .groupBy(SITE_ENTRIES.SITE_ID, SITE_ENTRIES.SPECIES)
          .fetch(SITE_ENTRIES.SITE_ID);
    }
  }

  private boolean filterDate(Date activityDate, Date activityStart, Date activityEnd) {
    if (activityStart == null && activityEnd == null) {
      return true;
    }

    boolean accept = true;
    if (activityStart != null) {
      accept = accept && activityDate.compareTo(activityStart) >= 0;
    }
    if (activityEnd != null) {
      accept = accept && activityDate.compareTo(activityEnd) <= 0;
    }

    return accept;
  }
}

//  Map<Integer, Record3<Integer, Integer, String>> map = db
//      .select(max(SITE_ENTRIES.ID).as(SITE_ENTRIES.ID.getName()), SITE_ENTRIES.SITE_ID, SITE_ENTRIES.SPECIES)
//      .from(SITE_ENTRIES)
//      .where(SITE_ENTRIES.SITE_ID.in(filteredSiteIds))
//      .and(SITE_ENTRIES.SPECIES.in(filterSitesRequest.getTreeSpecies()))
//      .groupBy(SITE_ENTRIES.SITE_ID, SITE_ENTRIES.SPECIES)
//      .fetchMap(SITE_ENTRIES.SITE_ID);
