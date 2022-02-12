package com.codeforcommunity.dto.neighborhoods;

import com.codeforcommunity.dto.ApiDto;
import com.codeforcommunity.exceptions.HandledException;

import java.util.ArrayList;
import java.util.List;

public class SendEmailRequest extends ApiDto {
  private List<Integer> neighborhoodIds;
  private String emailBody;

  public SendEmailRequest(List<Integer> neighborhoodIds, String emailBody) {
    this.neighborhoodIds = neighborhoodIds;
    this.emailBody = emailBody;
  }

  private SendEmailRequest() {}

  public List<Integer> getNeighborhoodIds() {
    return this.neighborhoodIds;
  }

  public void setNeighborhoodIds(List<Integer> neighborhoodIds) {
    this.neighborhoodIds = neighborhoodIds;
  }

  public String getEmailBody() {
    return this.emailBody;
  }

  public void setSites(String emailBody) {
    this.emailBody = emailBody;
  }

  @Override
  public List<String> validateFields(String fieldPrefix) throws HandledException {
    String fieldName = fieldPrefix + "send_email";
    List<String> fields = new ArrayList<>();

    if (neighborhoodIds == null) {
      fields.add(fieldName + "neighborhoodIds");
    }
    if (emailBody == null) {
      fields.add(fieldName + "emailBody");
    }

    return fields;
  }
}
