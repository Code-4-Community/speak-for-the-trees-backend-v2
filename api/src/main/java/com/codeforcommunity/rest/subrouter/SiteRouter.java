package com.codeforcommunity.rest.subrouter;

import static com.codeforcommunity.rest.ApiRouter.end;

import com.codeforcommunity.api.ISiteProcessor;
import com.codeforcommunity.dto.site.GetSiteResponse;
import com.codeforcommunity.dto.site.StewardshipActivitiesResponse;
import com.codeforcommunity.logger.SLogger;
import com.codeforcommunity.rest.IRouter;
import com.codeforcommunity.rest.RestFunctions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class SiteRouter implements IRouter {

  private final SLogger logger = new SLogger(SiteRouter.class);

  private final ISiteProcessor processor;

  public SiteRouter(ISiteProcessor processor) {
    this.processor = processor;
  }

  @Override
  public Router initializeRouter(Vertx vertx) {
    Router router = Router.router(vertx);

    registerGetSite(router);
    registerGetStewardshipActivities(router);

    return router;
  }

  private void registerGetSite(Router router) {
    Route getSiteRoute = router.get("/:site_id");
    getSiteRoute.handler(this::handleGetSiteRoute);
  }

  private void handleGetSiteRoute(RoutingContext ctx) {
    int siteId = RestFunctions.getRequestParameterAsInt(ctx.request(), "site_id");

    GetSiteResponse getSiteResponse = processor.getSite(siteId);

    end(ctx.response(), 200, JsonObject.mapFrom(getSiteResponse).toString());
  }

  private void registerGetStewardshipActivities(Router router) {
    Route getStewardshipActivities = router.get("/:site_id/stewardship_activities");
    getStewardshipActivities.handler(this::handleGetStewardshipActivities);
  }

  private void handleGetStewardshipActivities(RoutingContext ctx) {
    int siteId = RestFunctions.getRequestParameterAsInt(ctx.request(), "site_id");

    StewardshipActivitiesResponse stewardshipActivitiesResponse =
        processor.getStewardshipActivities(siteId);

    logger.info(JsonObject.mapFrom(stewardshipActivitiesResponse).toString());

    end(ctx.response(), 200, JsonObject.mapFrom(stewardshipActivitiesResponse).toString());
  }
}
