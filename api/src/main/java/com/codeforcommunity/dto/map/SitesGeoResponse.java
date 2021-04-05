package com.codeforcommunity.dto.map;

import java.util.List;

public class SitesGeoResponse {
    private final String type;
    private final String name;
    private final List<SiteFeature> features;

    public SitesGeoResponse(String type, String name, List<SiteFeature> features) {
        this.type = type;
        this.name = name;
        this.features = features;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public List<SiteFeature> getFeatures() {
        return features;
    }
}
