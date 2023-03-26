package com.codeforcommunity.dto.site;

import com.codeforcommunity.exceptions.HandledException;
import java.math.BigDecimal;
import java.util.List;

public class CSVSiteUpload extends UpdateSiteRequest {
  private Integer blockId;
  private BigDecimal lat;
  private BigDecimal lng;
  private String city;
  private String zip;
  private String address;
  private String neighborhood;

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
    super(
        treePresent,
        status,
        genus,
        species,
        commonName,
        confidence,
        diameter,
        circumference,
        multistem,
        coverage,
        pruning,
        condition,
        discoloring,
        leaning,
        constrictingGate,
        wounds,
        pooling,
        stakesWithWires,
        stakesWithoutWires,
        light,
        bicycle,
        bagEmpty,
        bagFilled,
        tape,
        suckerGrowth,
        siteType,
        sidewalkWidth,
        siteWidth,
        siteLength,
        material,
        raisedBed,
        fence,
        trash,
        wires,
        grate,
        stump,
        treeNotes,
        siteNotes);
    this.blockId = blockId;
    this.lat = lat;
    this.lng = lng;
    this.city = city;
    this.zip = zip;
    this.address = address;
    this.neighborhood = neighborhood;
  }

  public CSVSiteUpload() {
    super();
  }

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

  @Override
  public List<String> validateFields(String fieldPrefix) throws HandledException {
    String fieldName = fieldPrefix + "csv_site_upload.";
    List<String> fields = super.validateFields("");

    if (lat == null) {
      fields.add(fieldName + "lat");
    }
    if (lng == null) {
      fields.add(fieldName + "lng");
    }
    if (neighborhood == null) {
      fields.add(fieldName + "neighborhood");
    }

    return fields;
  }
}
