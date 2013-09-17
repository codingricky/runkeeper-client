package com.github.codingricky.runkeeperclient;

import com.github.codingricky.runkeeperclient.model.FitnessActivityFeed;
import com.github.codingricky.runkeeperclient.model.TeamFeed;
import org.apache.commons.lang.StringUtils;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class ClientIntegrationTest {

    private Client client;

    @Before
    public void before() {
        String accessToken = System.getProperty("accessToken");
        Assume.assumeTrue(StringUtils.isNotBlank(accessToken));
        client = new Client(accessToken);
    }

    @Test
    public void getUser() {
        assertThat(client.getUser()).isNotNull();
    }

    @Test
    public void getWeightFeed() {
        assertThat(client.getWeightFeed()).isNotNull();
    }

    @Test
    public void getFitnessActivities() {
        FitnessActivityFeed fitnessActivities = client.getFitnessActivities();
        assertThat(fitnessActivities).isNotNull();
        assertThat(client.getFitnessActivity(fitnessActivities.getItems()[0].getUri())).isNotNull();
    }

    @Test
    public void getRecords() {
        assertThat(client.getRecords()).isNotNull();
    }

    @Test
    public void getTeamFeed() {
        TeamFeed teamFeed = client.getTeamFeed();
        assertThat(teamFeed).isNotNull();
    }
}
