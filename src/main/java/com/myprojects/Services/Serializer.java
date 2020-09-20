package com.myprojects.Services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myprojects.FrameDrawer;


import java.io.IOException;

public class Serializer {
    private static final ObjectMapper objectMapper= getDefaultObjectMapper();

    private static Serializer instance;

    public static  Serializer getInstance() {
        if (instance == null) {
            instance = new Serializer();
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

            //RequestPOJO requestPOJO = objectMapper.treeToValue(jsonNode,RequestPOJO.class);
           // rp2str = requestPOJO.toString();
            /*rp2str= getArrayNode(jsonNode,"weather").path("main").asText() +"\n"+
                    getArrayNode(jsonNode,"weather").path("description").asText()+"\n"+
                    "Temperature,°C : "+ jsonNode.path("main").path("temp").asDouble()+"\n"+
                    "Temperature feels like,°C : " + jsonNode.path("main").path("feels_like").asDouble()+"\n"+
                    "Atmospheric pressure, hPa : " + jsonNode.path("main").path("pressure").asInt()+"\n"+
                    "Humidity, % : " + jsonNode.path("main").path("humidity").asInt()+ "\n"+
                    "Wind speed, meter/sec : "+jsonNode.path("wind").path("speed").asInt()+"\n"+
                    "Cloudiness, % : " +jsonNode.path("clouds").path("all").asInt() +"\n"+
                    "Sunrise time: " + tztc.unixToDate(jsonNode.path("sys").path("sunrise").asLong(),
                    jsonNode.path("timezone").asLong()) +"\n"+
                    "Sunset time: " + tztc.unixToDate(jsonNode.path("sys").path("sunset").asLong(),
                    jsonNode.path("timezone").asLong());*/
            FrameDrawer.iconName = this.getArrayNode(jsonNode,"weather").path("icon").asText();

            stringBuilder.append(this.getArrayNode(jsonNode,"weather").path("main").asText()).append(" : ")
                    .append(this.getArrayNode(jsonNode,"weather").path("description").asText())
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

   /* public String getIconNameFromJSON (String jsonString) {
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return
                this.getArrayNode(jsonNode,"weather").path("icon").asText();
    }*/

     public JsonNode getArrayNode (JsonNode node, String arrayNodeName) {
        JsonNode arrayNode =node.path (arrayNodeName);

        return arrayNode.get(0);
    }
}
