package com.myprojects.WeatherAPP_Singletones;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myprojects.WeatherAPP_POJOs.RequestPOJO;

import java.io.IOException;

public class Serializer {
    private static final ObjectMapper objectMapper= getDefaultObjectMapper();

    private static Serializer instance;

    public static synchronized Serializer getInstance() {
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
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            RequestPOJO requestPOJO = objectMapper.treeToValue(jsonNode,RequestPOJO.class);
            rp2str = requestPOJO.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rp2str;
    }

    public String getIconNameFromJSON (String pojoString) {
        int pos = pojoString.indexOf("icon");
        pos+=7;

        return pojoString.substring(pos,pos+3);
    }
}
