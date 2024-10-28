package com.google.zxing.client.result;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GeoParsedResultTestCase {

  @Test
  public void testGeo() {
    doTest("geo:40.689247,-74.044502", 40.689247, -74.044502, 0.0, null);
    doTest("geo:40.689247,-74.044502,10", 40.689247, -74.044502, 10.0, null);
    doTest("geo:40.689247,-74.044502?q=Statue+of+Liberty", 40.689247, -74.044502, 0.0, "q=Statue+of+Liberty");
    doTest("geo:40.689247,-74.044502,10?q=Statue+of+Liberty", 40.689247, -74.044502, 10.0, "q=Statue+of+Liberty");
  }

  private void doTest(String uri, double expectedLatitude, double expectedLongitude, double expectedAltitude, String expectedQuery) {
    GeoParsedResult result = parseGeoURI(uri);
    assertEquals(expectedLatitude, result.getLatitude(), 0.000001);
    assertEquals(expectedLongitude, result.getLongitude(), 0.000001);
    assertEquals(expectedAltitude, result.getAltitude(), 0.000001);
    assertEquals(expectedQuery, result.getQuery());
    assertEquals(uri, formatGeoURI(result));
  }

  private GeoParsedResult parseGeoURI(String uri) {
    String[] parts = uri.split("[,:?]");
    double latitude = Double.parseDouble(parts[1]);
    double longitude = Double.parseDouble(parts[2]);
    double altitude = parts.length > 3 && parts[3].matches("\\d+(\\.\\d+)?") ? Double.parseDouble(parts[3]) : 0.0;
    String query = uri.contains("?") ? uri.substring(uri.indexOf('?') + 1) : null;
    return new GeoParsedResult(latitude, longitude, altitude, query);
  }

  private String formatGeoURI(GeoParsedResult result) {
    StringBuilder sb = new StringBuilder();
    sb.append("geo:").append(result.getLatitude()).append(",").append(result.getLongitude());
    if (result.getAltitude() != 0.0) {
      sb.append(",").append((int) result.getAltitude());
    }
    if (result.getQuery() != null) {
      sb.append("?").append(result.getQuery());
    }
    return sb.toString();
  }
}// 3 1/1
