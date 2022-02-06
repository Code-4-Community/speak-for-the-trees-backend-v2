package com.codeforcommunity.dto.report;

public class CommunityStats {
    private final Integer adopterCount;
    private final Integer treesAdopted;
    private final Integer stewardshipActivities;

    public CommunityStats(Integer adopterCount,
                             Integer treesAdopted,
                             Integer stewardshipActivities) {
        this.adopterCount = adopterCount;
        this.treesAdopted = treesAdopted;
        this.stewardshipActivities = stewardshipActivities;
    }

    public Integer getAdopterCount() {
        return this.adopterCount;
    }

    public Integer getTreesAdopted() {
        return this.treesAdopted;
    }

    public Integer getStewardshipActivities() {
        return this.stewardshipActivities;
    }
}
