package com.codeforcommunity.dto.imports;

import com.codeforcommunity.dto.ApiDto;
import com.codeforcommunity.exceptions.HandledException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(
    ignoreUnknown = true) // Allows us to store data in JSON for future use but not import it
public class SiteImport extends ApiDto {
  private Integer blockId;
  private BigDecimal lat;
  private BigDecimal lng;
  private String city;
  private String zip;
  private String address;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
  private Timestamp deletedAt;

  private Integer siteId;
  private Integer userId;
  private String username;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
  private Timestamp updatedAt;

  private Boolean qa;
  private Boolean treePresent;
  private String status;
  private String genus;
  private String species;
  private String commonName;
  private String confidence;
  private Boolean multistem;
  private Double diameter;
  private Double circumference;
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
  private String melneaCassTrees;
  private Integer mcbNumber;
  private String treeDedicatedTo;

  public SiteImport(
      Integer blockId,
      BigDecimal lat,
      BigDecimal lng,
      String city,
      String zip,
      String address,
      Timestamp deletedAt,
      Integer siteId,
      Integer userId,
      String username,
      Timestamp updatedAt,
      Boolean qa,
      Boolean treePresent,
      String status,
      String genus,
      String species,
      String commonName,
      String confidence,
      Boolean multistem,
      Double diameter,
      Double circumference,
      String coverage,
      String pruning,
      String condition,
      Boolean discoloring,
      Boolean leaning,
      Boolean constrictingGrate,
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
      String melneaCassTrees,
      Integer mcbNumber,
      String treeDedicatedTo) {
    this.blockId = blockId;
    this.lat = lat;
    this.lng = lng;
    this.city = city;
    this.zip = zip;
    this.address = address;
    this.deletedAt = deletedAt;
    this.siteId = siteId;
    this.userId = userId;
    this.username = username;
    this.updatedAt = updatedAt;
    this.qa = qa;
    this.treePresent = treePresent;
    this.status = status;
    this.genus = genus;
    this.species = species;
    this.commonName = commonName;
    this.confidence = confidence;
    this.multistem = multistem;
    this.diameter = diameter;
    this.circumference = circumference;
    this.coverage = coverage;
    this.pruning = pruning;
    this.condition = condition;
    this.discoloring = discoloring;
    this.leaning = leaning;
    this.constrictingGrate = constrictingGrate;
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
    this.melneaCassTrees = melneaCassTrees;
    this.mcbNumber = mcbNumber;
    this.treeDedicatedTo = treeDedicatedTo;
  }

  private SiteImport() {}

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

  public Timestamp getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(Timestamp deletedAt) {
    this.deletedAt = deletedAt;
  }

  public Integer getSiteId() {
    return siteId;
  }

  public void setSiteId(Integer siteId) {
    this.siteId = siteId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Boolean getQa() {
    return qa;
  }

  public void setQa(Boolean qa) {
    this.qa = qa;
  }

  public Boolean getTreePresent() {
    return treePresent;
  }

  public void setTreePresent(Boolean treePresent) {
    this.treePresent = treePresent;
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

  public Boolean getDiscoloring() {
    return discoloring;
  }

  public void setDiscoloring(Boolean discoloring) {
    this.discoloring = discoloring;
  }

  public Boolean getLeaning() {
    return leaning;
  }

  public void setLeaning(Boolean leaning) {
    this.leaning = leaning;
  }

  public Boolean getConstrictingGrate() {
    return constrictingGrate;
  }

  public void setConstrictingGrate(Boolean constrictingGrate) {
    this.constrictingGrate = constrictingGrate;
  }

  public Boolean getWounds() {
    return wounds;
  }

  public void setWounds(Boolean wounds) {
    this.wounds = wounds;
  }

  public Boolean getPooling() {
    return pooling;
  }

  public void setPooling(Boolean pooling) {
    this.pooling = pooling;
  }

  public Boolean getStakesWithWires() {
    return stakesWithWires;
  }

  public void setStakesWithWires(Boolean stakesWithWires) {
    this.stakesWithWires = stakesWithWires;
  }

  public Boolean getStakesWithoutWires() {
    return stakesWithoutWires;
  }

  public void setStakesWithoutWires(Boolean stakesWithoutWires) {
    this.stakesWithoutWires = stakesWithoutWires;
  }

  public Boolean getLight() {
    return light;
  }

  public void setLight(Boolean light) {
    this.light = light;
  }

  public Boolean getBicycle() {
    return bicycle;
  }

  public void setBicycle(Boolean bicycle) {
    this.bicycle = bicycle;
  }

  public Boolean getBagEmpty() {
    return bagEmpty;
  }

  public void setBagEmpty(Boolean bagEmpty) {
    this.bagEmpty = bagEmpty;
  }

  public Boolean getBagFilled() {
    return bagFilled;
  }

  public void setBagFilled(Boolean bagFilled) {
    this.bagFilled = bagFilled;
  }

  public Boolean getTape() {
    return tape;
  }

  public void setTape(Boolean tape) {
    this.tape = tape;
  }

  public Boolean getSuckerGrowth() {
    return suckerGrowth;
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

  public Boolean getRaisedBed() {
    return raisedBed;
  }

  public void setRaisedBed(Boolean raisedBed) {
    this.raisedBed = raisedBed;
  }

  public Boolean getFence() {
    return fence;
  }

  public void setFence(Boolean fence) {
    this.fence = fence;
  }

  public Boolean getTrash() {
    return trash;
  }

  public void setTrash(Boolean trash) {
    this.trash = trash;
  }

  public Boolean getWires() {
    return wires;
  }

  public void setWires(Boolean wires) {
    this.wires = wires;
  }

  public Boolean getGrate() {
    return grate;
  }

  public void setGrate(Boolean grate) {
    this.grate = grate;
  }

  public Boolean getStump() {
    return stump;
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

  public String getMelneaCassTrees() {
    return melneaCassTrees;
  }

  public void setMelneaCassTrees(String melneaCassTrees) {
    this.melneaCassTrees = melneaCassTrees;
  }

  public Integer getMcbNumber() {
    return mcbNumber;
  }

  public void setMcbNumber(Integer mcbNumber) {
    this.mcbNumber = mcbNumber;
  }

  public String getTreeDedicatedTo() {
    return treeDedicatedTo;
  }

  public void setTreeDedicatedTo(String treeDedicatedTo) {
    this.treeDedicatedTo = treeDedicatedTo;
  }

  public Boolean getMultistem() {
    return multistem;
  }

  public void setMultistem(Boolean multistem) {
    this.multistem = multistem;
  }

  @Override
  public List<String> validateFields(String fieldPrefix) throws HandledException {
    String fieldName = fieldPrefix + "sites.";
    List<String> fields = new ArrayList<>();

    // blockId and zip are null sometimes, so not checked
    if (siteId == null) {
      fields.add(fieldName + "siteId");
    }
    if (lat == null) {
      fields.add(fieldName + "lat");
    }
    if (lng == null) {
      fields.add(fieldName + "lng");
    }
    if (city == null) {
      fields.add(fieldName + "city");
    }
    if (address == null) {
      fields.add(fieldName + "address");
    }
    if (updatedAt == null) {
      fields.add(fieldName + "updatedAt");
    }

    return fields;
  }
}
