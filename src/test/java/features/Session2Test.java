package features;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Person;
import org.junit.jupiter.api.Test;

public class Session2Test {

    @Test
    void test1() {
        Person person = Person.getInstance();

        RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(person).post("https://postman-echo.com/post")
                .prettyPrint();
    }
}
