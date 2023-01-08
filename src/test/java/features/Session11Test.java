package features;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@WireMockTest(httpPort = 8089)
public class Session11Test {

    @Test
    void get_object(WireMockRuntimeInfo wm) {
        WireMock.stubFor(get(urlEqualTo("/test1")).willReturn(
                        aResponse().withHeader("Content-Type", "application/json")
                                .withStatus(200)
                                .withBody("{\"number\": 20}")
                )
        );

        RestAssured.get("http://localhost:8089/test1").prettyPrint();
    }

    @Test
    void return_array(WireMockRuntimeInfo wm) {
        WireMock.stubFor(get(urlEqualTo("/test2")).willReturn(
                        aResponse().withHeader("Content-Type", "application/json")
                                .withStatus(200)
                                .withBody("[ \"12\", \"13\", \"14\", \"15\", \"16\", \"17\", \"18\" ]")
                )
        );

        RestAssured.get("http://localhost:8089/test2").prettyPrint();
    }

    @Test
    void get_wiremock(WireMockRuntimeInfo wm) {
        System.out.println("wmRuntimeInfo.getHttpBaseUrl() = " + wm.getHttpBaseUrl());
        System.out.println("wmRuntimeInfo.getHttpPort() = " + wm.getHttpPort());
        WireMock.stubFor(get(urlEqualTo("/test3")).willReturn(
                        aResponse().withHeader("Content-Type", "application/json")
                                .withStatus(200)
                                .withBody("{\"number\": 20}")
                )
        );

        RestAssured.get(wm.getHttpBaseUrl() + "/test3").prettyPrint();
    }
}
