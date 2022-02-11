package com.codeforcommunity.processor;

import static org.jooq.generated.tables.AdoptedSites.ADOPTED_SITES;
import static org.jooq.generated.tables.Stewardship.STEWARDSHIP;
import static org.jooq.generated.tables.Users.USERS;

import com.codeforcommunity.api.IReportProcessor;
import com.codeforcommunity.dto.report.CommunityStats;
import com.codeforcommunity.dto.report.GetCommunityStatsResponse;
import org.jooq.DSLContext;

import static org.jooq.impl.DSL.countDistinct;

public class ReportProcessorImpl implements IReportProcessor {

  private final DSLContext db;

  public ReportProcessorImpl(DSLContext db) {
    this.db = db;
  }

  @Override
  public GetCommunityStatsResponse getCommunityStats() {
    CommunityStats communityStats = db.select(
        countDistinct(USERS.ID),
        countDistinct(ADOPTED_SITES.SITE_ID),
        countDistinct(STEWARDSHIP.ID))
        .from(ADOPTED_SITES)
        .fullJoin(USERS)
        .on(ADOPTED_SITES.USER_ID.eq(USERS.ID))
        .fullJoin(STEWARDSHIP)
        .on(ADOPTED_SITES.SITE_ID.eq(STEWARDSHIP.SITE_ID))
        .fetchInto(CommunityStats.class).get(0);

    return new GetCommunityStatsResponse(communityStats);
  }
}
