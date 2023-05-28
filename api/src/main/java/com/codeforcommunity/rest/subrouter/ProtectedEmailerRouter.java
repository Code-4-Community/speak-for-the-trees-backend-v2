package com.codeforcommunity.rest.subrouter;

import static com.codeforcommunity.rest.ApiRouter.end;

import com.codeforcommunity.api.IProtectedEmailerProcessor;
import com.codeforcommunity.auth.JWTData;
import com.codeforcommunity.dto.emailer.AddTemplateRequest;
import com.codeforcommunity.rest.IRouter;
import com.codeforcommunity.rest.RestFunctions;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class ProtectedEmailerRouter implements IRouter {

  private final IProtectedEmailerProcessor processor;

  public ProtectedEmailerRouter(IProtectedEmailerProcessor processor) {
    this.processor = processor;
  }

  @Override
  public Router initializeRouter(Vertx vertx) {
    Router router = Router.router(vertx);

    registerAddTemplate(router);
    registerEditTemplate(router);

    return router;
  }

  private void registerAddTemplate(Router router) {
    Route addTemplate = router.post("/add_template");
    addTemplate.handler(this::handleAddTemplate);
  }

  private void handleAddTemplate(RoutingContext ctx) {
    JWTData userData = ctx.get("jwt_data");
    AddTemplateRequest addTemplateRequest =
        RestFunctions.getJsonBodyAsClass(ctx, AddTemplateRequest.class);

    processor.addTemplate(userData, addTemplateRequest);

    end(ctx.response(), 200);
  }

  private void registerEditTemplate(Router router) {
    Route editTemplate = router.post("/edit_template/:template_name");
    editTemplate.handler(this::handleEditTemplate);
  }

  private void handleEditTemplate(RoutingContext ctx) {
    JWTData userData = ctx.get("jwt_data");
    String templateName = RestFunctions.getRequestParameterAsString(ctx.request(), "template_name");
    AddTemplateRequest editTemplateRequest =
            RestFunctions.getJsonBodyAsClass(ctx, AddTemplateRequest.class);

    processor.editTemplate(userData, templateName, editTemplateRequest);

    end(ctx.response(), 200);
  }
}
