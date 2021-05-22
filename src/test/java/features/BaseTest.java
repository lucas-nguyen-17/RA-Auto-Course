package features;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;

import static io.restassured.config.EncoderConfig.encoderConfig;
import static utilities.AppProperty.BASE_URL;
import static utilities.LoadConfig.CONFIG;

public class BaseTest {

    @BeforeAll
    static void beforeAll() {
        new File("target/download").mkdir();
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.replaceFiltersWith(new AllureRestAssured());
        RestAssured.config = RestAssured.config()
                .encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
        RestAssured.baseURI = CONFIG.getProperty(BASE_URL);
    }
}
