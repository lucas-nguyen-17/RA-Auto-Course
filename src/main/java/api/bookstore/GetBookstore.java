package api.bookstore;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetBookstore {

    private Response callAPI(String token) {
        return RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .get("https://run.mocky.io/v3/dc86d6fc-a514-419a-8919-5baeb28add4d");
    }

    public Response get(String token) {
        return callAPI(token);
    }
}
