package com.github.runkeeperclient;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import com.github.runkeeperclient.model.FitnessActivityFeed;
import com.github.runkeeperclient.model.FitnessActivity;
import com.github.runkeeperclient.model.User;
import com.github.runkeeperclient.model.WeightFeed;

import java.io.IOException;

public class Client {

    public static final String PRODUCTION_URL = "http://api.runkeeper.com";
    public static final String USER_RESOURCE = "/user";

    private final HttpClient httpClient;
    private final String accessToken;
    private final String url;
    private final Gson gson;

    public Client(String accessToken) {
        this(PRODUCTION_URL, accessToken);
    }

    public Client(String url, String accessToken) {
        this.url = url;
        this.accessToken = accessToken;
        httpClient = new DefaultHttpClient();
        gson = new Gson();
    }

    public User getUser(Callback<User> callback) {
        HttpGet get = createHttpGetRequest(USER_RESOURCE, ContentTypes.USER);
        return execute(get, User.class, callback);
    }

    public User getUser() {
        return getUser(null);
    }

    public String getUserJson() {
        final String[] userJson = new String[1];
        getUser(new Callback<User>() {
            @Override
            public void success(User result, String json) {
                userJson[0] = json;
            }
        });
        return userJson[0];
    }

    public FitnessActivityFeed getFitnessActivities(User user, Callback<FitnessActivityFeed> callback) {
        HttpGet get = createHttpGetRequest(user.getFitnessActivities(), ContentTypes.FITNESS_ACTIVITY_FEED);
        return execute(get, FitnessActivityFeed.class, callback);
    }

    public FitnessActivityFeed getFitnessActivities(User user) {
        return getFitnessActivities(user, null);
    }

    public FitnessActivity getFitnessActivity(String resource, Callback<FitnessActivity> callback) {
        HttpGet get = createHttpGetRequest(resource, ContentTypes.FITNESS_ACTIVITY);
        return execute(get, FitnessActivity.class, callback);
    }

    public FitnessActivity getFitnessActivity(String resource) {
        return getFitnessActivity(resource, null);
    }

    public WeightFeed getWeightFeed(String resource) {
        HttpGet get = createHttpGetRequest(resource, ContentTypes.WEIGHT);
        return execute(get, WeightFeed.class, null);
    }

    private HttpGet createHttpGetRequest(String resource, String contentType) {
        String fullUrl = String.format("%s%s", url, resource);
        HttpGet get = new HttpGet(fullUrl);
        get.addHeader("Accept", contentType);
        get.addHeader("Authorization", "Bearer " + accessToken);
        return get;
    }

    private <T> T execute(HttpGet get, Class<T> clazz, Callback<T> callback) {
        try {
            HttpResponse response = httpClient.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                String entity = EntityUtils.toString(response.getEntity());
                T parsedObject = gson.fromJson(entity, clazz);
                if (callback != null) {
                    callback.success(parsedObject, entity);
                }
                return parsedObject;
            } else if (statusCode == HttpStatus.SC_UNAUTHORIZED) {
                throw new SecurityException();
            } else {
                throw new ClientException("Unexpected statusCode " + statusCode);
            }
        } catch (IOException e) {
            throw new ClientException(e);
        }
    }
}
