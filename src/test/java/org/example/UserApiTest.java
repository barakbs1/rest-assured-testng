package org.example;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;


public class UserApiTest {
    @Test
    public void givenUrl_whenSuccessOnGetsResponseAndJsonHasRequiredKV_thenCorrect() {
        get("https://google.com").then().statusCode(200);
//                .assertThat().body("data.leagueId", equalTo(35));
    }
}