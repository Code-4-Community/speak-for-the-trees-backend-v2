package com.codeforcommunity.dto.map;

import io.vertx.core.json.JsonObject;

public class SiteFeature {
  private final String type;
  private final SiteFeatureProperties properties;
  private final JsonObject geometry;

  public SiteFeature(String type, SiteFeatureProperties properties, JsonObject geometry) {
    this.type = type;
    this.properties = properties;
    this.geometry = geometry;
  }

  public String getType() {
    return type;
  }

  public SiteFeatureProperties getProperties() {
    return properties;
  }

  public JsonObject getGeometry() {
    return geometry;
  }
}
