package features;

import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("5")
public class Session5Test {

    static String response;

    @BeforeAll
    static void setUp() {
        RestAssured.filters(new AllureRestAssured());
        response = get("https://run.mocky.io/v3/060803e9-f05f-420e-97ad-49a1dba5cd5a")
                .asString();
    }

    @Test
    void test1() {
        String author = JsonPath.parse(response).read("$.store.book[0].author");
        String category = JsonPath.parse(response).read("$.store.book[0].category");
        double price = JsonPath.parse(response).read("$.store.book[0].price");
        String title = JsonPath.parse(response).read("$.store.book[0].title");

        assertThat(author).isEqualToIgnoringCase("nigel Rees");
        assertThat(category).as("category").isEqualToIgnoringCase("reference");
        assertThat(price).isEqualTo(8.95);
        assertThat(title).as("title").isEqualToIgnoringCase("Sayings of the Century");
    }

    @Test
    void test2() {
        List<String> authors = JsonPath.read(response, "$.store.book[*].author");
        assertThat(authors).as("all authors").contains("Nigel Rees", "Herman Melville");
    }

    @Test
    void test3() {
        Map<String, Object> firstBook = JsonPath.read(response, "$.store.book[0]");
        assertThat(firstBook.get("author")).isEqualTo("Nigel Rees");
        assertThat(firstBook.get("price")).isEqualTo(8.95);
    }

    @Test
    void test4() {
        List<Map<String, Object>> books = JsonPath.read(response, "$..book[*]");
        assertThat(books.get(3).get("isbn")).as("isbn").isEqualTo("0-395-19395-8");
    }

    @Test
    void test5() {
        List<Map<String, Object>> books = JsonPath.read(response, "$..book[-2:]");
        books.forEach(System.out::println);
    }

    @Test
    void test6() {
        List<String> titles = JsonPath.read(response, "$..book[?(@.price == 12.99)].title");
        assertThat(titles).contains("Sword of Honour");
    }

    @Test
    void test7() {
        String path = "$..book[?(@.price > 12.9 && @.isbn == '0-395-19395-8')].title";
        List<String> titles = JsonPath.read(response, path);
    }

    @Test
    void test8() {
        response = get("https://run.mocky.io/v3/dc86d6fc-a514-419a-8919-5baeb28add4d").asString();
        List<String> titles = JsonPath.read(response, "$..book[?(@.name.age == 15)].title");
    }

    @Test
    void test9() {
        List<String> titles = JsonPath.read(response, "$..book[*].title");

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(titles).as("contain 'a'").allMatch(title -> title.contains("a"));
        soft.assertThat(titles).as("contain 'o'").allMatch(title -> title.contains("o"));
        soft.assertThat(1).isEqualTo(2);
        soft.assertAll();
    }

    @Test
    void test10() {
        RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/books.json"))
                .post("https://postman-echo.com/post");
    }

    @Test
    void test11() {
        RestAssured.given().contentType(ContentType.JSON)
                .body(new File("src/test/resources/books.json"))
                .post("https://postman-echo.com/post")
                .then()
                .statusCode(201);
    }
}
