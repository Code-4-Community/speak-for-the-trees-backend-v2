package com.codeforcommunity.dto.site;

import java.util.Date;

public class FilterSitesResponse {

  private int siteId;
  private String address;
  private int adoptedId;
  private String adopterName;
  private Date dateAdopted;
  private int adopterActivityCount;
  private int neighborhoodId;
  private Integer lastActivityWeeks;

  public FilterSitesResponse(int siteId, String address, int adoptedId, String adopterName, Date dateAdopted, int adopterActivityCount, int neighborhoodId, int lastActivityWeeks) {
    this.siteId = siteId;
    this.address = address;
    this.adoptedId = adoptedId;
    this.adopterName = adopterName;
    this.dateAdopted = dateAdopted;
    this.adopterActivityCount = adopterActivityCount;
    this.neighborhoodId = neighborhoodId;
    this.lastActivityWeeks = lastActivityWeeks;
  }

  public int getSiteId() {
    return siteId;
  }

  public void setSiteId(int siteId) {
    this.siteId = siteId;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getAdoptedId() {
    return adoptedId;
  }

  public void setAdoptedId(int adoptedId) {
    this.adoptedId = adoptedId;
  }

  public String getAdopterName() {
    return adopterName;
  }

  public void setAdopterName(String adopterName) {
    this.adopterName = adopterName;
  }

  public Date getDateAdopted() {
    return dateAdopted;
  }

  public void setDateAdopted(Date dateAdopted) {
    this.dateAdopted = dateAdopted;
  }

  public int getAdopterActivityCount() {
    return adopterActivityCount;
  }

  public void setAdopterActivityCount(int adopterActivityCount) {
    this.adopterActivityCount = adopterActivityCount;
  }

  public int getNeighborhoodId() {
    return neighborhoodId;
  }

  public void setNeighborhoodId(int neighborhoodId) {
    this.neighborhoodId = neighborhoodId;
  }

  public Integer getLastActivityWeeks() {
    return lastActivityWeeks;
  }

  public void setLastActivityWeeks(Integer lastActivityWeeks) {
    this.lastActivityWeeks = lastActivityWeeks;
  }
}
