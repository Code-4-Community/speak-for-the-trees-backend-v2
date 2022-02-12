package com.codeforcommunity.processor;

import com.codeforcommunity.api.IProtectedNeighborhoodsProcessor;
import com.codeforcommunity.auth.JWTData;
import com.codeforcommunity.dto.neighborhoods.SendEmailRequest;
import com.codeforcommunity.dto.neighborhoods.UserEmailRecord;
import com.codeforcommunity.requester.Emailer;
import org.jooq.DSLContext;

import java.util.List;

import static org.jooq.generated.Tables.ADOPTED_SITES;
import static org.jooq.generated.Tables.NEIGHBORHOODS;
import static org.jooq.generated.Tables.SITES;
import static org.jooq.generated.tables.Users.USERS;

public class ProtectedNeighborhoodsProcessorImpl implements IProtectedNeighborhoodsProcessor {
  private final DSLContext db;
  private final Emailer emailer;

  public ProtectedNeighborhoodsProcessorImpl(DSLContext db, Emailer emailer) {
    this.db = db;
    this.emailer = emailer;
  }

  @Override
  public void sendEmail(JWTData userData, SendEmailRequest sendEmailRequest) {
    List<Integer> neighborhoodIDs = sendEmailRequest.getNeighborhoodIDs();
    String emailBody = sendEmailRequest.getEmailBody();

    List<UserEmailRecord> userEmailRecords = db.select(USERS.EMAIL, SITES.ADDRESS)
        .from(USERS)
        .leftJoin(ADOPTED_SITES)
        .on(USERS.ID.eq(ADOPTED_SITES.USER_ID))
        .leftJoin(SITES)
        .on(ADOPTED_SITES.SITE_ID.eq(SITES.ID))
        .leftJoin(NEIGHBORHOODS)
        .on(SITES.NEIGHBORHOOD_ID.eq(NEIGHBORHOODS.ID))
        .where(NEIGHBORHOODS.ID.in(neighborhoodIDs)).fetchInto(UserEmailRecord.class);

    userEmailRecords.forEach(record -> {
      emailer.sendNeighborhoodsEmail(record.getEmail(), record.getName(), emailBody);
    });
  }
}
