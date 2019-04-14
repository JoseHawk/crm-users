package com.joselara.crmusers.services;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@ActiveProfiles("test")
@Ignore
public class AuthenticationServiceIntegrationTest {

    @Value("${security.oauth2.client.client-id}")
    private String CLIENT_ID;

    @Value("${security.oauth2.client.client-secret}")
    private String CLIENT_SECRET;
    
    @Test
    public void testTokenRequest() {
        given().auth().preemptive().basic(CLIENT_ID, CLIENT_SECRET)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("username", "admin@joselara.com")
                .formParam("password", "admin")
                .formParam("grant_type", "password")
        .when()
                .post("/oauth/token")
        .then().log()
                .all()
                .statusCode(401);
    }
}
