package com.codeforcommunity.exceptions;

import com.codeforcommunity.rest.FailureHandler;

import io.vertx.ext.web.RoutingContext;

public class ExistingTeamNameException extends HandledException {
  private final String errors;

  public ExistingTeamNameException(String errors) { this.errors = errors; }

  @Override
  public void callHandler(FailureHandler handler, RoutingContext ctx) {
    handler.handleExistingTeamNameException(ctx, this.errors);
  }
}
