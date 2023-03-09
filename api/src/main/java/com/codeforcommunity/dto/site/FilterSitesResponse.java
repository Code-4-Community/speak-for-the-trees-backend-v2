package com.codeforcommunity.dto.site;

import java.util.Collection;
import java.util.List;

public class FilterSitesResponse {

  private final int amount;

  private final List<Integer> siteIds;

  public FilterSitesResponse(int amount, List<Integer> siteIds) {
    this.amount = amount;
    this.siteIds = siteIds;
  }

  public int getAmount() {
    return amount;
  }

  public List<Integer> getSiteIds() {
    return siteIds;
  }
}
