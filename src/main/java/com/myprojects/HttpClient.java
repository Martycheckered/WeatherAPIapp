package com.myprojects;

import com.myprojects.Services.LongNameSplitter;
import com.myprojects.Services.PropertyGetter;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClient{


    PropertyGetter propertyGetter = PropertyGetter.getInstance();

    final String MY_API_KEY = propertyGetter.getValueFromPropertiesFile("myAPIkey") ;

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

     void close() throws IOException {
        httpClient.close();
    }

    static  String throwRequest (String input) {
        LongNameSplitter longNameSplitter = LongNameSplitter.getInstance();
        StringBuilder resultedCityName= longNameSplitter.divideBySingleWord(input);
        String result = "";

        HttpClient obj = new HttpClient();

        try {
            //System.out.println("Testing 1 - Send Http GET request");
            try {
                result = obj.sendGet(resultedCityName.toString());
            } catch (IOException ex) {
                result = "У Вас ошибка сети";
                ex.printStackTrace();
            } catch (IllegalStateException ex){
                 result = "Ошибка внешнего ресурса, попробуйте позже.";
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


    String sendGet(String cityname) throws IOException {

         String  jsonResult= null;


            HttpGet request = new HttpGet(propertyGetter.getValueFromPropertiesFile("API_URI_pt1")
                    +cityname+
                    propertyGetter.getValueFromPropertiesFile("API_URI_pt2")
                    +MY_API_KEY);

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







}
