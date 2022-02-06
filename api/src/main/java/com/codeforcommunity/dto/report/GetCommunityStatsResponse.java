package com.codeforcommunity.dto.report;

import java.util.List;

public class GetCommunityStatsResponse {
  public final List<CommunityStats> communityStatsReport;

  public GetCommunityStatsResponse(List<CommunityStats> communityStatsReport) {
    this.communityStatsReport = communityStatsReport;
  }

  public List<CommunityStats> getcommunityStatsReport() {
    return communityStatsReport;
  }
}
