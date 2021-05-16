package features;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class Session1Test extends BaseTest{

    @Test
    void test1() {
        RestAssured.given()
                .get("https://postman-echo.com/get?foo1=bar1&foo2=bar2")
                .prettyPrint();
    }

    @Test
    void test2() {
        RestAssured.given()
                .get("https://restful-booker.herokuapp.com/booking")
                .prettyPrint();
    }

    @Test
    void test3() {
        RestAssured.given()
                .get("https://restful-booker.herokuapp.com/booking/1")
                .prettyPrint();
    }

    @Test
    void test4() {
        RestAssured.given()
                .get("https://restful-booker.herokuapp.com/booking?firstname=Sally&lastname=Brown")
                .prettyPrint();
        System.out.println("--------------------------------");

        RestAssured.given()
                .get("https://restful-booker.herokuapp.com/booking?checkin=2015-07-13&checkout=2019-08-10")
                .prettyPrint();
    }
}
