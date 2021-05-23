package features;

import api.mocky.GetConfig;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import models.response.mocky.ConfigListObject;
import models.response.mocky.SearchConfigObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Instant;
import java.util.List;

import static io.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;

public class Session10Test extends BaseTest{
    GetConfig getConfig;

    @BeforeEach
    void setUp() {
        getConfig = new GetConfig();
    }

    @Test
    void compare_file() {
        File file1 = new File("src/test/resources/books.json");
        File file2 = new File("src/test/resources/books-simple.json");

        assertThat(contentOf(file1)).isEqualToNormalizingNewlines(contentOf(file2));
    }

    @Test
    void timestamp() {
        long milli = Instant.now().toEpochMilli();
        System.out.println("milli = " + milli);

        long timeMillis = System.currentTimeMillis();
        System.out.println("timeMillis = " + timeMillis);
    }

    @Test
    void exam_1() {
        Response res = get("https://run.mocky.io/v3/812f6263-4ae8-4c0f-b98e-0d7ca765dd9b");
        List<String> ips = JsonPath.read(res.asString(), "$.config_list[*].ip");
        List<Integer> ports = JsonPath.read(res.asString(), "$.config_list[*].port");

        for (int i = 0; i < ips.size(); i++) {
            boolean checkIp = ips.get(i).contains("0");
            boolean checkPort = String.valueOf(ports.get(i)).contains("0");
            boolean result = checkIp || checkPort;
            assertThat(result).isEqualTo(true);
        }
    }

    @Test
    void exam_2() {
        List<SearchConfigObject> configObjects = getConfig.get();

        assertThat(configObjects).allMatch(
                config -> config.getIp().contains("0") ||
                        String.valueOf(config.getPort()).contains("0")
        );
    }

    @Test
    void exam_3() {
        ConfigListObject configListObject = getConfig.getAll();

        assertThat(configListObject.getConfigList()).allMatch(
                config -> config.getIp().contains("0") ||
                        String.valueOf(config.getPort()).contains("0")
        );

        assertThat(configListObject.getTotal()).isEqualTo(14);
        assertThat(configListObject.isSuccess()).isEqualTo(true);
        assertThat(getConfig.getStatusCode()).isEqualTo(200);
    }


}
