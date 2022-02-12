package com.codeforcommunity.processor;

import com.codeforcommunity.api.IProtectedNeighborhoodsProcessor;
import com.codeforcommunity.auth.JWTData;
import com.codeforcommunity.dto.neighborhoods.SendEmailRequest;
import org.jooq.DSLContext;

import java.util.List;

public class ProtectedNeighborhoodsProcessorImpl implements IProtectedNeighborhoodsProcessor {
  private final DSLContext db;

  public ProtectedNeighborhoodsProcessorImpl(DSLContext db) {
    this.db = db;
  }

  @Override
  public void sendEmail(JWTData userData, SendEmailRequest sendEmailRequest) {
    List<Integer> neighborhoodIds = sendEmailRequest.getNeighborhoodIds();


  }
}
