package com.codeforcommunity.dto.site;

import com.codeforcommunity.dto.ApiDto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class FilterSitesRequest extends ApiDto {

  private List<String> treeSpecies;

  private Date adoptedStart;

  private Date adoptedEnd;

  private Date lastActivityStart;

  private Date lastActivityEnd;

  private List<Integer> neighborhoodIds;

  public List<String> getTreeSpecies() {
    return treeSpecies;
  }

  public void setTreeSpecies(List<String> treeSpecies) {
    this.treeSpecies = treeSpecies;
  }

  public Date getAdoptedStart() {
    return adoptedStart;
  }

  public void setAdoptedStart(Date adoptedStart) {
    this.adoptedStart = adoptedStart;
  }

  public Date getAdoptedEnd() {
    return adoptedEnd;
  }

  public void setAdoptedEnd(Date adoptedEnd) {
    this.adoptedEnd = adoptedEnd;
  }

  public Date getLastActivityStart() {
    return lastActivityStart;
  }

  public void setLastActivityStart(Date lastActivityStart) {
    this.lastActivityStart = lastActivityStart;
  }

  public Date getLastActivityEnd() {
    return lastActivityEnd;
  }

  public void setLastActivityEnd(Date lastActivityEnd) {
    this.lastActivityEnd = lastActivityEnd;
  }

  public List<Integer> getNeighborhoodIds() {
    return neighborhoodIds;
  }

  public void setNeighborhoodIds(List<Integer> neighborhoodIds) {
    this.neighborhoodIds = neighborhoodIds;
  }

  @Override
  public List<String> validateFields(String fieldPrefix) {
    return new ArrayList<>();
  }
}
