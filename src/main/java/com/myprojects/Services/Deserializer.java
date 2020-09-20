package com.myprojects.Services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myprojects.FrameDrawer;


import java.io.IOException;

public class Deserializer {
    private static final ObjectMapper objectMapper= getDefaultObjectMapper();

    private static Deserializer instance;

    public static Deserializer getInstance() {
        if (instance == null) {
            instance = new Deserializer();
        }
        return instance;
    }
    public static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return defaultObjectMapper;
    }

     public String parseJSON (String jsonString) {
        //String rp2str = "";
        StringBuilder stringBuilder= new StringBuilder();
        TimeZoneTimeCounter tztc = TimeZoneTimeCounter.getInstance();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);


            FrameDrawer.iconName = this.getFirstArrayNode(jsonNode,"weather").path("icon").asText();

            stringBuilder.append(this.getFirstArrayNode(jsonNode,"weather").path("main").asText()).append(" : ")
                    .append(this.getFirstArrayNode(jsonNode,"weather").path("description").asText())
                    .append("\n")
                    .append("Temperature,°C : ").append(jsonNode.path("main").path("temp").asDouble())
                    .append("\n")
                    .append("Temperature feels like,°C : ").append(jsonNode.path("main").path("feels_like").asDouble())
                    .append("\n")
                    .append("Atmospheric pressure, hPa : ").append(jsonNode.path("main").path("pressure").asInt())
                    .append("\n")
                    .append("Humidity, % : ").append(jsonNode.path("main").path("humidity").asInt())
                    .append("\n")
                    .append("Wind speed, meter/sec : ").append(jsonNode.path("wind").path("speed").asInt())
                    .append("\n")
                    .append("Cloudiness, % : ").append(jsonNode.path("clouds").path("all").asInt())
                    .append("\n")
                    .append("Sunrise time: ").append(tztc.unixToDate(jsonNode.path("sys").path("sunrise").asLong(),
                            jsonNode.path("timezone").asLong()))
                    .append("\n")
                    .append("Sunset time: ").append(tztc.unixToDate(jsonNode.path("sys").path("sunset").asLong(),
                            jsonNode.path("timezone").asLong()));



        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }



     public JsonNode getFirstArrayNode(JsonNode node, String arrayNodeName) {
        JsonNode arrayNode =node.path (arrayNodeName);

        return arrayNode.get(0);
    }
}
