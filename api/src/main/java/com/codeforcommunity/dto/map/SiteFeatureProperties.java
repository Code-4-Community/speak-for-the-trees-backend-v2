package com.codeforcommunity.dto.map;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Date;

public class SiteFeatureProperties {
  Integer id;
  Boolean treePresent;
  Double diameter;
  String species;
  Date plantingDate;
  Integer adopterId;

  public SiteFeatureProperties(
      Integer id, Boolean treePresent, Date plantingDate, Integer adopterId) {
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

  public Date getPlantingDate() {
    return plantingDate;
  }
}
