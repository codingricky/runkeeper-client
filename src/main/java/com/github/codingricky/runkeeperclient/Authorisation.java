package com.github.codingricky.runkeeperclient;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Authorisation {

    public String convertToken(String code, String clientId, String clientSecret, String redirectUri) {
        HttpClient client = new DefaultHttpClient();

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("grant_type", "authorization_code"));
        nameValuePairs.add(new BasicNameValuePair("code", code));
        nameValuePairs.add(new BasicNameValuePair("client_id", clientId));
        nameValuePairs.add(new BasicNameValuePair("client_secret", clientSecret));
        nameValuePairs.add(new BasicNameValuePair("redirect_uri", redirectUri));

        try {
            HttpPost post = new HttpPost("https://runkeeper.com/apps/token");
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            String entityAsString = EntityUtils.toString(entity);
            Map<String, String> map = new HashMap<String, String>();
            map = (Map<String, String>) new Gson().fromJson(entityAsString, map.getClass());
            return map.get("access_token");
        } catch (Exception e) {
            throw new ClientException(e);
        }
    }

    public static void main(String[] args) {
//        stepOne();

        stepThree();
    }

    private static void stepThree() {
        HttpClient client = new DefaultHttpClient();
        String code = "af6c9e1b46a9416c99fee1bf23d9b0b7";
        String clientId = "b2c3bab71810423a84e53aa1a12e6bd8";
        String redirectUri = "http://www.dius.com.au";
        String clientSecret = "0f11ece96149468d95f9ae96f53e33f6";

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("grant_type", "authorization_code"));
        nameValuePairs.add(new BasicNameValuePair("code", code));
        nameValuePairs.add(new BasicNameValuePair("client_id", clientId));
        nameValuePairs.add(new BasicNameValuePair("client_secret", clientSecret));
        nameValuePairs.add(new BasicNameValuePair("redirect_uri", redirectUri));

        try {
            HttpPost post = new HttpPost("https://runkeeper.com/apps/token");
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            String output = EntityUtils.toString(entity);
            System.out.println(output);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private static void stepOne() {
        HttpClient client = new DefaultHttpClient();
        String clientId = "b2c3bab71810423a84e53aa1a12e6bd8";
        String url = String.format("https://runkeeper.com/apps/authorize?client_id=%s&response_type=%s&redirect_uri=%s", clientId, "code", "http://www.dius.com.au");
        System.out.println("Connecting to url=" + url);
        HttpGet get = new HttpGet(url);
        try {
            HttpResponse response = client.execute(get);
            System.out.println("statusCode=" + response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
//            Header location = response.getLastHeader("Location");
//            System.out.println("location=" + location.getValue());
//            String output = EntityUtils.toString(entity);
//            System.out.println(output);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
