package com.codeforcommunity.rest.subrouter;

import com.codeforcommunity.api.IProtectedReportProcessor;
import com.codeforcommunity.auth.JWTData;
import com.codeforcommunity.dto.report.GetReportCSVRequest;
import com.codeforcommunity.dto.report.GetCommunityStatsResponse;
import com.codeforcommunity.dto.report.GetAdoptionReportResponse;
import com.codeforcommunity.dto.report.GetStewardshipReportResponse;
import com.codeforcommunity.rest.IRouter;
import com.codeforcommunity.rest.RestFunctions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.time.LocalDate;
import java.util.Optional;

import static com.codeforcommunity.rest.ApiRouter.end;

public class ProtectedReportRouter implements IRouter {
  private static final String PREVIOUS_DAYS_QUERY_PARAM_NAME = "previousDays";
  private final IProtectedReportProcessor processor;

  public ProtectedReportRouter(IProtectedReportProcessor processor) {
    this.processor = processor;
  }

  @Override
  public Router initializeRouter(Vertx vertx) {
    Router router = Router.router(vertx);

    registerGetCommunityStats(router);
    registerGetAdoptionReport(router);
    registerGetAdoptionReportCSV(router);
    registerGetStewardshipReport(router);
    registerGetStewardshipReportCSV(router);

    return router;
  }

  private void registerGetCommunityStats(Router router) {
    Route getAdoptionReportRoute = router.get("/stats");
    getAdoptionReportRoute.handler(this::handleGetCommunityStatsRoute);
  }

  private void handleGetCommunityStatsRoute(RoutingContext ctx) {
    GetCommunityStatsResponse communityStatsReportResponse = processor.getCommunityStats();
    end(ctx.response(), 200, JsonObject.mapFrom(communityStatsReportResponse).toString());
  }

  private void registerGetAdoptionReport(Router router) {
    Route getAdoptionReportRoute = router.get("/adoption");
    getAdoptionReportRoute.handler(this::handleGetAdoptionReportRoute);
  }

  private void handleGetAdoptionReportRoute(RoutingContext ctx) {
    JWTData userData = ctx.get("jwt_data");

    GetAdoptionReportResponse adoptionReportResponse = processor.getAdoptionReport(userData);

    end(ctx.response(), 200, JsonObject.mapFrom(adoptionReportResponse).toString());
  }

  private void registerGetAdoptionReportCSV(Router router) {
    Route getAdoptionReportCSVRoute = router.get("/csv/adoption");
    getAdoptionReportCSVRoute.handler(this::handleGetAdoptionReportCSVRoute);
  }

  private void handleGetAdoptionReportCSVRoute(RoutingContext ctx) {
    JWTData userData = ctx.get("jwt_data");

    Optional<Long> maybePreviousDays =
            RestFunctions.getOptionalQueryParam(ctx, PREVIOUS_DAYS_QUERY_PARAM_NAME, Long::parseLong);
    Long previousDays = maybePreviousDays.orElse(LocalDate.now().toEpochDay());

    GetReportCSVRequest getAdoptionReportCSVRequest = new GetReportCSVRequest(previousDays);

    String adoptionReportCSVResponse = processor.getAdoptionReportCSV(userData, getAdoptionReportCSVRequest);

    end(ctx.response(), 200, adoptionReportCSVResponse);
  }

  private void registerGetStewardshipReport(Router router) {
    Route getStewardshipReportRoute = router.get("/stewardship");
    getStewardshipReportRoute.handler(this::handleGetStewardshipReportRoute);
  }

  private void handleGetStewardshipReportRoute(RoutingContext ctx) {
    JWTData userData = ctx.get("jwt_data");

    GetStewardshipReportResponse stewardshipReportResponse =
        processor.getStewardshipReport(userData);

    end(ctx.response(), 200, JsonObject.mapFrom(stewardshipReportResponse).toString());
    }

  private void registerGetStewardshipReportCSV(Router router) {
    Route getStewardshipReportCSVRoute = router.get("/csv/stewardship");
    getStewardshipReportCSVRoute.handler(this::handleGetStewardshipReportCSVRoute);
  }

  private void handleGetStewardshipReportCSVRoute(RoutingContext ctx) {
    JWTData userData = ctx.get("jwt_data");

    Optional<Long> maybePreviousDays =
            RestFunctions.getOptionalQueryParam(ctx, PREVIOUS_DAYS_QUERY_PARAM_NAME, Long::parseLong);
    Long previousDays = maybePreviousDays.orElse(LocalDate.now().toEpochDay());

    GetReportCSVRequest getStewardshipReportCSVRequest = new GetReportCSVRequest(previousDays);

    String stewardshipReportCSVResponse = processor.getStewardshipReportCSV(userData, getStewardshipReportCSVRequest);

    end(ctx.response(), 200, stewardshipReportCSVResponse);
  }
}
