package api.bookstore;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.ReqHeader;

public class GetBookstore {

    private Response callAPI(String token) {
        return RestAssured.given()
                .spec(ReqHeader.withToken(token))
                .get("https://run.mocky.io/v3/dc86d6fc-a514-419a-8919-5baeb28add4d");
    }

    public Response get(String token) {
        return callAPI(token);
    }
}
