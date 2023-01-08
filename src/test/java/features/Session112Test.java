package features;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class Session112Test {

    WireMockServer wm;

    @BeforeEach
    public void setup() {
        wm = new WireMockServer(wireMockConfig().dynamicPort());
        wm.start();
        initStub();
        RestAssured.port = wm.port();
    }

    @AfterEach
    public void teardown() {
        wm.stop();
    }

    public void initStub() {
        wm.stubFor(get(urlEqualTo("/test1")).willReturn(
                        aResponse().withHeader("Content-Type", "application/json")
                                .withStatus(200)
                                .withBody("{\"number\": 20}")
                )
        );
        wm.stubFor(get(urlEqualTo("/test2")).willReturn(
                        aResponse().withHeader("Content-Type", "application/json")
                                .withStatus(200)
                                .withBody("[ \"12\", \"13\", \"14\", \"15\", \"16\", \"17\", \"18\" ]")
                )
        );
    }

    @Test
    void get_object() {
        RestAssured.get("/test1").prettyPrint();
    }

    @Test
    void return_array() {
        RestAssured.get("/test2").prettyPrint();
    }
}
