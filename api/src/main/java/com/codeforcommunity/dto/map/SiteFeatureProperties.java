package com.codeforcommunity.dto.map;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Date;
import java.sql.Timestamp;

public class SiteFeatureProperties {
  Integer id;
  Boolean treePresent;
  Double diameter;
  String species;
  Timestamp updatedAt;
  Date plantingDate;
  String updatedBy; // username
  Integer adopterId;
  String address;

  public SiteFeatureProperties(
      Integer id,
      Boolean treePresent,
      Date plantingDate,
      Integer adopterId) {
    this.id = id;
    this.treePresent = treePresent;
    this.plantingDate = plantingDate;
    this.adopterId = adopterId;
  }

  public Integer getId() {
    return id;
  }

  @JsonProperty("tree_present")
  public Boolean getTreePresent() {
    return treePresent;
  }

  public Double getDiameter() {
    return diameter;
  }

  public String getSpecies() {
    return species;
  }

  public String getAddress() {
    return address;
  }

  public Date getPlantingDate() {
    return plantingDate;
  }
}
