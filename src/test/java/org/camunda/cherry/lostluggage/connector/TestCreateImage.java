package org.camunda.cherry.lostluggage.connector;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TestCreateImage {

  @Test
  public void sanity() throws FileNotFoundException {
    RestTemplate restTemplate = new RestTemplate();
    String size = "500x400";
    String lat = "40.714728";
    String lon = "-73.998672";
    String GOOGLE_API_KEY = "AIzaSyBVkpHopbvXlup4-T0HNZcfyRGzKRfMxyY";
    String url = "https://maps.googleapis.com/maps/api/staticmap?zoom=10&center="+lat+","+lon+"&size="+size+"&key="+ GOOGLE_API_KEY;
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    String file = response.getBody();
    System.out.println(file);
    PrintWriter out = new PrintWriter("out/file.gif");
    out.println(file);
    assertEquals(response.getStatusCode(), HttpStatus.OK);
  }

}
