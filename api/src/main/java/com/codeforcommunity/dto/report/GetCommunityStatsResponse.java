package com.codeforcommunity.dto.report;


public class GetCommunityStatsResponse {
  public final CommunityStats communityStatsReport;

  public GetCommunityStatsResponse(CommunityStats communityStatsReport) {
    this.communityStatsReport = communityStatsReport;
  }

  public CommunityStats getcommunityStatsReport() {
    return communityStatsReport;
  }
}
