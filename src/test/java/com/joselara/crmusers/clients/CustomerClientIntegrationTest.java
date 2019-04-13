package com.joselara.crmusers.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import com.joselara.crmusers.models.dto.CustomerDTO;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@ActiveProfiles("test")
public class CustomerClientIntegrationTest {

    private final String GATEWAY_URL = "http://localhost:8090/customer";
    private final String CUSTOMER_URL = "http://localhost:5060/customer";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(5060);

    @Before
    public void setUp() {
        wireMockRule.addStubMapping(setStubs(0, wireMockRule));
    }

    private StubMapping setStubs(int index, WireMockServer wireMockServer) {
        StubMapping stub;
        switch (index) {
            case 0:
                stub = wireMockServer.stubFor(get(urlEqualTo("/customer"))
                        .inScenario("CustomerCall")
                        .whenScenarioStateIs("Started")
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBodyFile("customer_response.json"))
                );
                break;
            default:
                stub = null;
                break;
        }

        return stub;
    }

    @Test
    public void callToCustomer() {
        ResponseEntity<CustomerDTO> customerDTO = restTemplate.exchange(CUSTOMER_URL, HttpMethod.GET, new HttpEntity(null), CustomerDTO.class);

        assertEquals(customerDTO.getBody().getName(), "tesCustomer");
    }
}
