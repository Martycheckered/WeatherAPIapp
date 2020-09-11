package com.myprojects;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HttpClientExample {


    private final CloseableHttpClient httpClient = HttpClients.createDefault();



     void close() throws IOException {
        httpClient.close();
    }


    String sendGet(String cityname) throws IOException {
        final String myAPIkey = "f3c38b99990d434298d683cc28a21320";
         String  jsonResult= null;
        //CloseableHttpClient httpClient = HttpClients.createDefault();


            HttpGet request = new HttpGet("https://api.openweathermap.org/data/2.5/weather?q="+cityname+
                    "&units=metric&appid="+myAPIkey);

            // add request headers
           // request.addHeader("custom-key", "mkyong");
            //request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");
            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(request)) {

                if (response.getStatusLine().getStatusCode()==200) {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        // return it as a String
                        jsonResult = EntityUtils.toString(entity);

                    }
                }
                else throw new IllegalStateException ("Incorrect result of API request");

            }
            catch (IllegalStateException e) {
                jsonResult = e.getMessage();
            }


                // Get HttpResponse Status
               // System.out.println(response.getProtocolVersion());              // HTTP/1.1
               // System.out.println(response.getStatusLine().getStatusCode());   // 200
                //System.out.println(response.getStatusLine().getReasonPhrase()); // OK
                //System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

        return  jsonResult;
    }


    static  String throwRequest (String input) {
         LongNameSplitter longNameSplitter = LongNameSplitter.getInstance();

        StringBuilder resultedCityName= longNameSplitter.divideBySingleWord(input);
        String result = null;

        HttpClientExample obj = new HttpClientExample();

        try {
            //System.out.println("Testing 1 - Send Http GET request");
            try {
                result = obj.sendGet(resultedCityName.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                obj.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return result;

    }




}