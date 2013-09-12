package com.github.codingricky.runkeeperclient;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import com.github.codingricky.runkeeperclient.model.FitnessActivity;
import com.github.codingricky.runkeeperclient.model.FitnessActivityFeed;
import com.github.codingricky.runkeeperclient.model.FitnessActivityFeedItem;
import com.github.codingricky.runkeeperclient.model.User;
import com.github.codingricky.runkeeperclient.model.WeightFeed;
import com.github.codingricky.runkeeperclient.server.FakeRunkeeperServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import static org.junit.Assert.fail;

public class ClientTest {

    private static final String TEST_URL = String.format("http://localhost:%s", FakeRunkeeperServer.PORT);
    private static final String ACCESS_TOKEN = "1234567890";

    private Client client;
    private Gson gson;

    @BeforeClass
    public static void beforeClass() {
        FakeRunkeeperServer.start(ACCESS_TOKEN);
    }

    @AfterClass
    public static void afterClass() {
        FakeRunkeeperServer.stop();
    }

    @Before
    public void before() {
        client = new Client(TEST_URL, ACCESS_TOKEN);
        gson = new Gson();
    }

    @Test(expected = SecurityException.class)
    public void invalidAccessTokenShouldThrowSecurityException() {
        new Client(TEST_URL, "some other token").getUser();
    }

    @Test
    public void weightFeedShouldBeReturned() {
        User user = client.getUser();
        WeightFeed weightFeed = client.getWeightFeed(user.getWeight());
        assertObjectEqualsExpectedJson(weightFeed, "responses/weight.json");
    }

    @Test
    public void userShouldBeReturned() throws IOException, JSONException {
        User user = client.getUser();
        assertObjectEqualsExpectedJson(user, "responses/user.json");
    }

    @Test
    public void fitnessActivityFeedShouldBeReturned() throws IOException, JSONException {
        User user = client.getUser();
        FitnessActivityFeed fitnessActivities = client.getFitnessActivities(user);
        assertObjectEqualsExpectedJson(fitnessActivities, "responses/fitnessActivityFeed.json");
    }

    @Test
    public void fitnessActivityShouldBeSet() {
        User user = client.getUser();
        FitnessActivityFeed fitnessActivities = client.getFitnessActivities(user);
        for (FitnessActivityFeedItem feed : fitnessActivities.getItems()) {
            FitnessActivity fitnessActivity = client.getFitnessActivity(feed.getUri());
            assertObjectEqualsExpectedJson(fitnessActivity, "responses" + feed.getUri() + ".json");
        }
    }

    private <T> void assertObjectEqualsExpectedJson(T actual, String filePath) {
        try {
            String actualJson = gson.toJson(actual);
            InputStream resourceAsStream = ClientTest.class.getClassLoader().getResourceAsStream(filePath);
            if (resourceAsStream == null) {
                fail("file path " + filePath + " could not be found");
                return;
            }
            StringWriter expectedJson = new StringWriter();
            IOUtils.copy(resourceAsStream, expectedJson);
            JSONAssert.assertEquals(expectedJson.toString(), actualJson, true);
        } catch (Exception e) {
            fail("Unexpected exception " + e.getMessage());
        }

    }
}
