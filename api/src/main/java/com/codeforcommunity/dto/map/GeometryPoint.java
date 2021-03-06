package com.codeforcommunity.dto.map;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class GeometryPoint {
  String type;
  List<BigDecimal> coordinates;

  public GeometryPoint(BigDecimal latitude, BigDecimal longitude) {
    this.type = "Point";
    this.coordinates = Arrays.asList(longitude, latitude); // geoJSON standard is long, lat
  }

  public String getType() {
    return type;
  }

  public List<BigDecimal> getCoordinates() {
    return coordinates;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setCoordinates(List<BigDecimal> coordinates) {
    this.coordinates = coordinates;
  }
}
