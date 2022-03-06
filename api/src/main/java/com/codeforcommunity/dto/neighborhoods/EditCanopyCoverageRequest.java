package com.codeforcommunity.dto.neighborhoods;

import com.codeforcommunity.dto.ApiDto;
import com.codeforcommunity.exceptions.HandledException;
import java.util.ArrayList;
import java.util.List;

public class EditCanopyCoverageRequest extends ApiDto {
  private Double canopyCoverage;
  private Integer neighborhoodID;

  public EditCanopyCoverageRequest(Double canopyCoverage, Integer neighborhoodID) {
    this.canopyCoverage = canopyCoverage;
    this.neighborhoodID = neighborhoodID;
  }

  private EditCanopyCoverageRequest() {}

  @Override
  public List<String> validateFields(String fieldPrefix) throws HandledException {
    String fieldName = fieldPrefix + "edit_canopy";
    List<String> fields = new ArrayList<>();

    if (this.canopyCoverage == null) {
      fields.add(fieldName + "canopyCoverage");
    }

    if (this.neighborhoodID == null) {
      fields.add(fieldName + "neighborhoodID");
    }

    return fields;
  }

  public Double getCanopyCoverage() {
    return this.canopyCoverage;
  }

  public Integer getNeighborhoodID() {
    return this.neighborhoodID;
  }
}
