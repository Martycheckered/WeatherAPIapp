package com.myprojects;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FrameDrawingTest {
    static ObjectMapper objectMapper= Serialize.getDefaultObjectMapper();


    @Test
    void printJSON () {
        String jsonStringFromAPI = HttpClientExample.throwRequest("moscow");

        System.out.println(jsonStringFromAPI);
    }
    @Test
    void parseJSONtoNode() throws IOException {
        String jsonStringFromAPI = HttpClientExample.throwRequest("moscow");

        JsonNode jsonNode = objectMapper.readTree(jsonStringFromAPI);
        JsonNode weather = jsonNode.path("weather");
    }
    @Test
    void parseJSONtoPOJO() throws IOException {
        String jsonStringFromAPI = HttpClientExample.throwRequest("moscow");
        System.out.println(jsonStringFromAPI);
        System.out.println(Serialize.parseJSONtoPOJO(jsonStringFromAPI));
    }
  /*  @Test
    void parseJSONtoPOJOvalue() throws IOException {
        String jsonStringFromAPI = HttpClientExample.throwRequest("moscow");
        System.out.println(jsonStringFromAPI);
        RequestPOJO requestPOJO = objectMapper.readValue(jsonStringFromAPI, RequestPOJO.class);
        String prettyStaff1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestPOJO);
        System.out.println(prettyStaff1);
    }
    @Test
    void parseJSONtoSeparatePOJOvalue() throws IOException {
        String jsonStringFromAPI = HttpClientExample.throwRequest("moscow");
        System.out.println(jsonStringFromAPI);
        MainPOJO mainPOJO = objectMapper.readValue(jsonStringFromAPI, MainPOJO.class);
        String prettyStaff1 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mainPOJO);
        System.out.println(prettyStaff1);
    }*/
  @Test
  void getIcon () {
      String jsonStringFromAPI = HttpClientExample.throwRequest("miami");
      System.out.println(jsonStringFromAPI);


      String iconName =Serialize.getIconNameFromJSON(jsonStringFromAPI);
      System.out.println(iconName);
     // String iconName = FrameDrawing.getIconFromParsedJSON ( jsonStringFromAPI) ;
      //System.out.println(iconName);

      }

}