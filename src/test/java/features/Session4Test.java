package features;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Session4Test {

    @Test
    void test1() {
        RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/lotto.json"))
                .post("https://postman-echo.com/post")
                .then()
                .rootPath("json")
                .body("lotto.lottoId", equalTo(5));
    }

    @Test
    void test2() {
        RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/lotto.json"))
                .post("https://postman-echo.com/post")
                .then()
                .rootPath("json")
                .body("lotto.winners[0].winnerId", equalTo(23));
    }

    @Test
    void test3() {
        RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/lotto.json"))
                .post("https://postman-echo.com/post")
                .then()
                .rootPath("json")
                .body("lotto.winners[-1].winnerId", equalTo(54));
    }

    @Test
    void test4() {
        RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/books.json"))
                .post("https://postman-echo.com/post")
                .then()
                .rootPath("json")
                .body("store.book[1].author", equalTo("Evelyn Waugh"));
    }

    @Test
    void test5() {
        RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/lotto.json"))
                .post("https://postman-echo.com/post")
                .then()
                .rootPath("json")
                .body("lotto.winning-numbers", hasItems(2, 3, 5));
    }

    @Test
    void test6() {
        RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/lotto.json"))
                .post("https://postman-echo.com/post")
                .then()
                .rootPath("json")
                .body("lotto.winning-numbers", containsInAnyOrder(45, 34, 23, 7, 5, 3, 2));
    }

    @Test
    void test7() {
        int lottoId = RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/lotto.json"))
                .post("https://postman-echo.com/post")
                .path("json.lotto.lottoId");

        assertThat(lottoId, equalTo(5));
    }

    @Test
    void test8() {
        List<Integer> winningNumbers = RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/lotto.json"))
                .post("https://postman-echo.com/post")
                .path("json.lotto.winning-numbers");

        assertThat(winningNumbers, containsInAnyOrder(45, 34, 23, 7, 5, 3, 2));
    }

    @Test
    void test9() {
        String author = RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/books.json"))
                .post("https://postman-echo.com/post")
                .path("json.store.book[1].author");

        assertThat(author, equalToIgnoringCase("evelyn WauGh"));
    }

    @Test
    void test10() {
        float price = RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/books.json"))
                .post("https://postman-echo.com/post")
                .path("json.store.book[0].price");

        assertThat(price, equalTo(8.95F));
    }

    @Test
    void test11() {
        String res = RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/books.json"))
                .post("https://postman-echo.com/post")
                .asString();
        List<String> authors = JsonPath.from(res).getList("json.store.book.findAll {it}.author");
        authors.forEach(System.out::println);
        assertThat(authors, hasItems("Herman Melville", "J. R. R. Tolkien"));
    }

    @Test
    void test12() {
        String res = RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/books.json"))
                .post("https://postman-echo.com/post")
                .asString();

        List<String> authors = JsonPath.from(res)
                .getList("json.store.book.findAll {it.price > 10 && it.category==\"fiction\"}.author");
        authors.forEach(System.out::println);
        assertThat(authors, hasItems("Evelyn Waugh"));
    }

    @Test
    void test13() {
        String res = RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/books.json"))
                .post("https://postman-echo.com/post")
                .asString();

        double price = JsonPath.from(res)
                .getDouble("json.store.book[0].price");
        System.out.println("price = " + price);
        assertThat(price, equalTo(8.95));
    }

    @Test
    void test14() {
        Response res = RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/books.json"))
                .post("https://postman-echo.com/post");

        Headers headers = res.getHeaders();
        System.out.println("headers = " + headers.get("Content-Type"));

        System.out.println("res.statusCode() = " + res.statusCode());
    }

    @Test
    void test15() {
        Response res = RestAssured.given()
                .get("https://run.mocky.io/v3/060803e9-f05f-420e-97ad-49a1dba5cd5a");
        res.then().body(matchesJsonSchemaInClasspath("bookstore-schema.json"));
    }
}
