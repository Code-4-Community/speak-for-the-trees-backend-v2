package com.codeforcommunity.dto.site;

import com.codeforcommunity.exceptions.HandledException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CSVSiteUpload {
  /* site columns */
  private Integer blockId;
  private BigDecimal lat;
  private BigDecimal lng;
  private String city;
  private String zip;
  private String address;
  private String neighborhood;

  /* site entries columns */
  private Boolean treePresent;
  private String status;
  private String genus;
  private String species;
  private String commonName;
  private String confidence;
  private Double diameter;
  private Double circumference;
  private Boolean multistem;
  private String coverage;
  private String pruning;
  private String condition;
  private Boolean discoloring;
  private Boolean leaning;
  private Boolean constrictingGrate;
  private Boolean wounds;
  private Boolean pooling;
  private Boolean stakesWithWires;
  private Boolean stakesWithoutWires;
  private Boolean light;
  private Boolean bicycle;
  private Boolean bagEmpty;
  private Boolean bagFilled;
  private Boolean tape;
  private Boolean suckerGrowth;
  private String siteType;
  private String sidewalkWidth;
  private Double siteWidth;
  private Double siteLength;
  private String material;
  private Boolean raisedBed;
  private Boolean fence;
  private Boolean trash;
  private Boolean wires;
  private Boolean grate;
  private Boolean stump;
  private String treeNotes;
  private String siteNotes;

  public CSVSiteUpload(
      Boolean treePresent,
      String status,
      String genus,
      String species,
      String commonName,
      String confidence,
      Double diameter,
      Double circumference,
      Boolean multistem,
      String coverage,
      String pruning,
      String condition,
      Boolean discoloring,
      Boolean leaning,
      Boolean constrictingGate,
      Boolean wounds,
      Boolean pooling,
      Boolean stakesWithWires,
      Boolean stakesWithoutWires,
      Boolean light,
      Boolean bicycle,
      Boolean bagEmpty,
      Boolean bagFilled,
      Boolean tape,
      Boolean suckerGrowth,
      String siteType,
      String sidewalkWidth,
      Double siteWidth,
      Double siteLength,
      String material,
      Boolean raisedBed,
      Boolean fence,
      Boolean trash,
      Boolean wires,
      Boolean grate,
      Boolean stump,
      String treeNotes,
      String siteNotes,
      Integer blockId,
      BigDecimal lat,
      BigDecimal lng,
      String city,
      String zip,
      String address,
      String neighborhood) {
    this.blockId = blockId;
    this.lat = lat;
    this.lng = lng;
    this.city = city;
    this.zip = zip;
    this.address = address;
    this.neighborhood = neighborhood;

    this.treePresent = treePresent;
    this.status = status;
    this.genus = genus;
    this.species = species;
    this.commonName = commonName;
    this.confidence = confidence;
    this.diameter = diameter;
    this.circumference = circumference;
    this.multistem = multistem;
    this.coverage = coverage;
    this.pruning = pruning;
    this.condition = condition;
    this.discoloring = discoloring;
    this.leaning = leaning;
    this.constrictingGrate = constrictingGate;
    this.wounds = wounds;
    this.pooling = pooling;
    this.stakesWithWires = stakesWithWires;
    this.stakesWithoutWires = stakesWithoutWires;
    this.light = light;
    this.bicycle = bicycle;
    this.bagEmpty = bagEmpty;
    this.bagFilled = bagFilled;
    this.tape = tape;
    this.suckerGrowth = suckerGrowth;
    this.siteType = siteType;
    this.sidewalkWidth = sidewalkWidth;
    this.siteWidth = siteWidth;
    this.siteLength = siteLength;
    this.material = material;
    this.raisedBed = raisedBed;
    this.fence = fence;
    this.trash = trash;
    this.wires = wires;
    this.grate = grate;
    this.stump = stump;
    this.treeNotes = treeNotes;
    this.siteNotes = siteNotes;
  }

  public CSVSiteUpload() {
    super();
  }

//  public AddSiteRequest toAddSiteRequest() {
//    Integer neighborhoodId = CSVSiteUpload.mapNeighborhoodNameToId(neighborhood);
//
////    return new AddSiteRequest(
////        treePresent,
////        blockId
////    );
//  }

  public Integer getBlockId() {
    return blockId;
  }

  public void setBlockId(Integer blockId) {
    this.blockId = blockId;
  }

  public BigDecimal getLat() {
    return lat;
  }

  public void setLat(BigDecimal lat) {
    this.lat = lat;
  }

  public BigDecimal getLng() {
    return lng;
  }

  public void setLng(BigDecimal lng) {
    this.lng = lng;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }


  public Boolean isTreePresent() {
    return falseIfNull(treePresent);
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getGenus() {
    return genus;
  }

  public void setGenus(String genus) {
    this.genus = genus;
  }

  public String getSpecies() {
    return species;
  }

  public void setSpecies(String species) {
    this.species = species;
  }

  public String getCommonName() {
    return commonName;
  }

  public void setCommonName(String commonName) {
    this.commonName = commonName;
  }

  public String getConfidence() {
    return confidence;
  }

  public void setConfidence(String confidence) {
    this.confidence = confidence;
  }

  public Double getDiameter() {
    return diameter;
  }

  public void setDiameter(Double diameter) {
    this.diameter = diameter;
  }

  public Double getCircumference() {
    return circumference;
  }

  public void setCircumference(Double circumference) {
    this.circumference = circumference;
  }

  public Boolean isMultistem() {
    return falseIfNull(multistem);
  }

  public void setMultistem(Boolean multistem) {
    this.multistem = multistem;
  }

  public String getCoverage() {
    return coverage;
  }

  public void setCoverage(String coverage) {
    this.coverage = coverage;
  }

  public String getPruning() {
    return pruning;
  }

  public void setPruning(String pruning) {
    this.pruning = pruning;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public Boolean isDiscoloring() {
    return falseIfNull(discoloring);
  }

  public void setDiscoloring(Boolean discoloring) {
    this.discoloring = discoloring;
  }

  public Boolean isLeaning() {
    return falseIfNull(leaning);
  }

  public void setLeaning(Boolean leaning) {
    this.leaning = leaning;
  }

  public Boolean isConstrictingGrate() {
    return falseIfNull(constrictingGrate);
  }

  public void setConstrictingGrate(Boolean constrictingGrate) {
    this.constrictingGrate = constrictingGrate;
  }

  public Boolean isWounds() {
    return falseIfNull(wounds);
  }

  public void setWounds(Boolean wounds) {
    this.wounds = wounds;
  }

  public Boolean isPooling() {
    return falseIfNull(pooling);
  }

  public void setPooling(Boolean pooling) {
    this.pooling = pooling;
  }

  public Boolean isStakesWithWires() {
    return falseIfNull(stakesWithWires);
  }

  public void setStakesWithWires(Boolean stakesWithWires) {
    this.stakesWithWires = stakesWithWires;
  }

  public Boolean isStakesWithoutWires() {
    return falseIfNull(stakesWithoutWires);
  }

  public void setStakesWithoutWires(Boolean stakesWithoutWires) {
    this.stakesWithoutWires = stakesWithoutWires;
  }

  public Boolean isLight() {
    return falseIfNull(light);
  }

  public void setLight(Boolean light) {
    this.light = light;
  }

  public Boolean isBicycle() {
    return falseIfNull(bicycle);
  }

  public void setBicycle(Boolean bicycle) {
    this.bicycle = bicycle;
  }

  public Boolean isBagEmpty() {
    return falseIfNull(bagEmpty);
  }

  public void setBagEmpty(Boolean bagEmpty) {
    this.bagEmpty = bagEmpty;
  }

  public Boolean isBagFilled() {
    return falseIfNull(bagFilled);
  }

  public void setBagFilled(Boolean bagFilled) {
    this.bagFilled = bagFilled;
  }

  public Boolean isTape() {
    return falseIfNull(tape);
  }

  public void setTape(Boolean tape) {
    this.tape = tape;
  }

  public Boolean isSuckerGrowth() {
    return falseIfNull(suckerGrowth);
  }

  public void setSuckerGrowth(Boolean suckerGrowth) {
    this.suckerGrowth = suckerGrowth;
  }

  public String getSiteType() {
    return siteType;
  }

  public void setSiteType(String siteType) {
    this.siteType = siteType;
  }

  public String getSidewalkWidth() {
    return sidewalkWidth;
  }

  public void setSidewalkWidth(String sidewalkWidth) {
    this.sidewalkWidth = sidewalkWidth;
  }

  public Double getSiteWidth() {
    return siteWidth;
  }

  public void setSiteWidth(Double siteWidth) {
    this.siteWidth = siteWidth;
  }

  public Double getSiteLength() {
    return siteLength;
  }

  public void setSiteLength(Double siteLength) {
    this.siteLength = siteLength;
  }

  public String getMaterial() {
    return material;
  }

  public void setMaterial(String material) {
    this.material = material;
  }

  public Boolean isRaisedBed() {
    return falseIfNull(raisedBed);
  }

  public void setRaisedBed(Boolean raisedBed) {
    this.raisedBed = raisedBed;
  }

  public Boolean isFence() {
    return falseIfNull(fence);
  }

  public void setFence(Boolean fence) {
    this.fence = fence;
  }

  public Boolean isTrash() {
    return falseIfNull(trash);
  }

  public void setTrash(Boolean trash) {
    this.trash = trash;
  }

  public Boolean isWires() {
    return falseIfNull(wires);
  }

  public void setWires(Boolean wires) {
    this.wires = wires;
  }

  public Boolean isGrate() {
    return falseIfNull(grate);
  }

  public void setGrate(Boolean grate) {
    this.grate = grate;
  }

  public Boolean isStump() {
    return falseIfNull(stump);
  }

  public void setStump(Boolean stump) {
    this.stump = stump;
  }

  public String getTreeNotes() {
    return treeNotes;
  }

  public void setTreeNotes(String treeNotes) {
    this.treeNotes = treeNotes;
  }

  public String getSiteNotes() {
    return siteNotes;
  }

  public void setSiteNotes(String siteNotes) {
    this.siteNotes = siteNotes;
  }

  private boolean falseIfNull(Boolean bool) {
    return Optional.ofNullable(bool).orElse(false);
  }

  private static Integer mapNeighborhoodNameToId(String neighborhood) {
    Map<String, Integer> neighborhoodIdsMap = new HashMap<>();
    neighborhoodIdsMap.put("Back Bay", 2);
    neighborhoodIdsMap.put("Charlestown", 4);
    neighborhoodIdsMap.put("Dorchester", 6);
    neighborhoodIdsMap.put("Downtown", 7);
    neighborhoodIdsMap.put("East Boston", 8);
    neighborhoodIdsMap.put("Hyde Park", 10);

//    { label: 'Jamaica Plain', value: 11 },
//    { label: 'Mattapan', value: 12 },
//    { label: 'Mission Hill', value: 13 },
//    { label: 'North End', value: 14 },
//    { label: 'Roslindale', value: 15 },
//    { label: 'Roxbury', value: 16 },
//    { label: 'South Boston', value: 17 },
//    { label: 'West Roxbury', value: 19 },
//    { label: 'Harbor Islands', value: 22 },
//    { label: 'Allston', value: 24 },
//    { label: 'Brighton', value: 25 },
//    { label: 'Chinatown', value: 26 },
//    { label: 'Leather District', value: 27 },
//    { label: 'Longwood', value: 28 },
//    { label: 'South Boston Waterfront', value: 29 },
//    { label: 'Beacon Hill', value: 30 },
//    { label: 'West End', value: 31 },
//    { label: 'South End', value: 32 },
//    { label: 'Bay Village', value: 33 },
//    { label: 'Fenway', value: 34 },

    return neighborhoodIdsMap.get(neighborhood);
  }
}
