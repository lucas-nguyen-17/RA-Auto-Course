package features;

import api.bookstore.GetBookstore;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static api.authentication.PostLogin.ADMIN_TOKEN;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("7")
public class Session7Test extends BaseTest {

    GetBookstore getBookstore = new GetBookstore();

    @Test
    void test1() {
        System.out.println(ADMIN_TOKEN);
    }

    @Test
    void test2() {
        Response bookstore = getBookstore.get(ADMIN_TOKEN);
        List<String> authors = JsonPath.read(bookstore.asString(), "$..book[?(@.title == 'Moby Dick')].author");

        assertThat(bookstore.statusCode()).isEqualTo(200);
        assertThat(authors).isEqualTo(Collections.singletonList("Herman Melville"));
//        assertThat(authors).isEqualTo(Arrays.asList("Herman Melville"));
    }
}
