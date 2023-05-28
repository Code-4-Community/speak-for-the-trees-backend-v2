package com.codeforcommunity.api;

import com.codeforcommunity.auth.JWTData;
import com.codeforcommunity.dto.emailer.AddTemplateRequest;

public interface IProtectedEmailerProcessor {
  /** Adds an HTML email template to cloud storage. */
  void addTemplate(JWTData userData, AddTemplateRequest addTemplateRequest);
  /** Edits an HTML email template on cloud storage. */
  void editTemplate(JWTData userData, String templateName, AddTemplateRequest addTemplateRequest);
}
