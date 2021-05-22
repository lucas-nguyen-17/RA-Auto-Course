package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.http.ContentType.JSON;

public class ReqHeader {

    public static RequestSpecification withToken(String token) {
        return new RequestSpecBuilder().setContentType(JSON)
                .addHeader("Authrozation", "Bearer " + token)
                .build();
    }
    
}
