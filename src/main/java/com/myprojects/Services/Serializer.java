package com.myprojects.Services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myprojects.WeatherAPP_POJOs.RequestPOJO;

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

     public String parseJSONtoPOJO (String jsonString) {
        String rp2str = "";
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

            stringBuilder.append(getArrayNode(jsonNode,"weather").path("main").asText()).append("\n")
                    .append(getArrayNode(jsonNode,"weather").path("description").asText()).append("\n")
                    .append("Temperature,°C : ").append(jsonNode.path("main").path("temp").asDouble()).append("\n")
                    .append("Temperature feels like,°C : ").append(jsonNode.path("main").path("feels_like").asDouble())
                    .append("\n").
                    append("Atmospheric pressure, hPa : ").append(jsonNode.path("main").path("pressure").asInt()).
                    append("\n")
                    .append("Humidity, % : ").append(jsonNode.path("main").path("humidity").asInt()).append("\n")
                    .append("Wind speed, meter/sec : ").append(jsonNode.path("wind").path("speed").asInt()).append("\n")
                    .append("Cloudiness, % : ").append(jsonNode.path("clouds").path("all").asInt()).append("\n")
                    .append("Sunrise time: ").append(tztc.unixToDate(jsonNode.path("sys").path("sunrise").asLong(),
                    jsonNode.path("timezone").asLong())).append("\n")
                    .append("Sunset time: ")
                    .append(tztc.unixToDate(jsonNode.path("sys").path("sunset").asLong(),
                            jsonNode.path("timezone").asLong()));


                   /* weatherPOJO[0].getMain() +" : " + weatherPOJO[0].getDescription()+"\n"+
                    "Temperature,°C : "+ mainPOJO.getTemp()+"\n"+
                    "Temperature feels like,°C : " +mainPOJO.getFeels_like()+"\n"+
                    "Atmospheric pressure, hPa : " +mainPOJO.getPressure() +"\n"+
                    "Humidity, % : " +mainPOJO.getHumidity()+"\n"+
                    "Wind speed, meter/sec : "+windPOJO.getSpeed()+"\n"+
                    "Cloudiness, % : " +cloudsPOJO.getAll() +"\n"+
                    "Sunrise time: " + sysPOJO.unixToDate(sysPOJO.getSunrise(),timezone) +"\n"+
                    "Sunset time: " + sysPOJO.unixToDate(sysPOJO.getSunset(),timezone)*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public String getIconNameFromJSON (String pojoString) {
        int pos = pojoString.indexOf("icon");
        pos+=7;

        return pojoString.substring(pos,pos+3);
    }

    static public JsonNode getArrayNode (JsonNode node, String arrayNodeName) {
        JsonNode arrayNode =node.path (arrayNodeName);

        return arrayNode.get(0);
    }
}
