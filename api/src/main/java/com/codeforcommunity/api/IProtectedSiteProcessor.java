package com.codeforcommunity.api;

import com.codeforcommunity.auth.JWTData;
import com.codeforcommunity.dto.site.AddSiteRequest;
import com.codeforcommunity.dto.site.AdoptedSitesResponse;
import com.codeforcommunity.dto.site.EditSiteRequest;
import com.codeforcommunity.dto.site.RecordStewardshipRequest;
import com.codeforcommunity.dto.site.UpdateSiteRequest;
import java.time.LocalDate;

public interface IProtectedSiteProcessor {

  /**
   * Creates a record in the adopted sites table linking the user, the site, and the date adopted
   */
  void adoptSite(JWTData userData, int siteId, LocalDate dateAdopted);

  /** Removes the record in the adopted sites table linking the user and the site */
  void unadoptSite(JWTData userData, int siteId);

  /** Get users adopted sites */
  AdoptedSitesResponse getAdoptedSites(JWTData userData);

  /** Records a new stewardship activity in the stewardship table linked to the given site */
  void recordStewardship(
      JWTData userData, int siteId, RecordStewardshipRequest recordStewardshipRequest);

  /** Creates a new site with entries in the sites and site_entries tables */
  void addSite(JWTData userData, AddSiteRequest addSiteRequest);

  /** Updates the latest state of the site and creates a new entry in the site_entries table */
  void updateSite(JWTData userData, int siteId, UpdateSiteRequest updateSiteRequest);

  /** Edits features of the site */
  void editSite(JWTData userData, int siteId, EditSiteRequest editSiteRequest);

  /** Removes the site */
  void deleteSite(JWTData userData, int siteId);

  /** Removes the given stewardship activity */
  void deleteStewardship(JWTData userData, int activityId);
}
