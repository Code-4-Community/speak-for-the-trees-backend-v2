package com.codeforcommunity.dto.report;


public class GetCommunityStatsResponse {
  public final CommunityStats communityStats;

  public GetCommunityStatsResponse(CommunityStats communityStats) {
    this.communityStats = communityStats;
  }

  public CommunityStats getcommunityStats() {
    return communityStats;
  }
}
