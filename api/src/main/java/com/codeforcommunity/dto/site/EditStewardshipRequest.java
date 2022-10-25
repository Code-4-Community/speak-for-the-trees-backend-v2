package com.codeforcommunity.dto.site;

import com.codeforcommunity.exceptions.HandledException;

import java.util.ArrayList;
import java.util.List;

public class EditStewardshipRequest extends RecordStewardshipRequest {

  private Integer activityId;

  public EditStewardshipRequest(
      java.sql.Date date, boolean watered, boolean mulched, boolean cleaned, boolean weeded, Integer activityId) {
    super(date, watered, mulched, cleaned, weeded);
    this.activityId = activityId;
  }

  public Integer getActivityId() {
    return activityId;
  }

  public void setActivityId(Integer activityId) {
    this.activityId = activityId;
  }

  @Override
  public List<String> validateFields(String fieldPrefix) throws HandledException {
    String fieldName = fieldPrefix + "edit_stewardship_request.";
    List<String> fields = new ArrayList<>();

    if (getDate() == null) {
      fields.add(fieldName + "date");
    }
    if (!(getWatered() || getMulched() || getCleaned() || getWeeded())) {
      fields.add(fieldName + "activities");
    }
    if (activityId == null) {
      fields.add(fieldName + "activityId");
    }

    return fields;
  }
}
