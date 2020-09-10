package com.myprojects;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myprojects.WeatherAPP_POJOs.RequestPOJO;

import java.io.IOException;

public class Serialize {
    private static final ObjectMapper objectMapper= getDefaultObjectMapper();

    public static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return defaultObjectMapper;
    }

    static String parseJSONtoPOJO (String jsonString) {
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

    static String getIconNameFromJSON (String pojoString) {
        int pos = pojoString.indexOf("icon");
        pos+=7;

        return pojoString.substring(pos,pos+3);
    }
}
