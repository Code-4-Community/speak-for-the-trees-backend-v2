package com.codeforcommunity.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import static org.jooq.generated.Tables.ADOPTED_SITES;
import static org.jooq.generated.Tables.ENTRY_USERNAMES;
import static org.jooq.generated.Tables.SITES;
import static org.jooq.generated.Tables.SITE_ENTRIES;
import static org.jooq.generated.Tables.STEWARDSHIP;
import static org.jooq.generated.Tables.USERS;

import com.codeforcommunity.JooqMock;
import com.codeforcommunity.JooqMock.OperationType;
import com.codeforcommunity.dto.site.GetSiteResponse;
import com.codeforcommunity.exceptions.ResourceDoesNotExistException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.jooq.generated.tables.records.SiteEntriesRecord;
import org.jooq.generated.tables.records.SitesRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SiteProcessorImplTest {

  private JooqMock myJooqMock;
  private SiteProcessorImpl siteProcessor;

  private List<SitesRecord> sites;
  private List<SiteEntriesRecord> siteEntries;

  @BeforeEach
  public void setup() {
    this.myJooqMock = new JooqMock();
    this.siteProcessor = new SiteProcessorImpl(myJooqMock.getContext());

    this.setupSites();
    this.setupSiteEntries();
  }

  private void setupSites() {
    SitesRecord site1 = myJooqMock.getContext().newRecord(SITES);
    site1.setId(0);
    site1.setLat(BigDecimal.valueOf(32.93403));
    site1.setLng(BigDecimal.valueOf(-29.892922));
    site1.setCity("Boston");
    site1.setZip("02120");
    site1.setAddress("Street");
    site1.setNeighborhoodId(11);

    this.sites = new ArrayList<>();
    this.sites.add(site1);
  }

  private void setupSiteEntries() {
    // set up SiteEntries

    this.siteEntries = new ArrayList<>();
  }

  @Test
  public void testGetSiteInvalidSiteId() {
    myJooqMock.addEmptyReturn(OperationType.SELECT);

    try {
      GetSiteResponse response = this.siteProcessor.getSite(1);
      fail();
    } catch (ResourceDoesNotExistException e) {
      assertEquals(1, myJooqMock.timesCalled(OperationType.SELECT));
      assertEquals(1, e.getResourceId());
      assertEquals("site", e.getResourceType());
    }
  }

  @Test
  public void testGetSiteValidSiteId() {
    myJooqMock.addReturn(OperationType.SELECT, this.sites);
    myJooqMock.addReturn(OperationType.SELECT, this.siteEntries);
    GetSiteResponse response = this.siteProcessor.getSite(0);

    assertEquals(2, myJooqMock.timesCalled(OperationType.SELECT));

    SitesRecord site = this.sites.get(0);
    assertEquals(site.getId(), response.getSiteId());
    assertEquals(site.getLat(), response.getLat());
    assertEquals(site.getLng(), response.getLng());
    assertEquals(site.getCity(), response.getCity());
    assertEquals(site.getZip(), response.getZip());
    assertEquals(site.getAddress(), response.getAddress());
    assertEquals(site.getNeighborhoodId(), response.getNeighborhoodId());
  }
}
