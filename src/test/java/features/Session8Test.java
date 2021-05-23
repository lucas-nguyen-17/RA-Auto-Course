package features;

import api.upload.PostUpload;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.request.download.BookCsvObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.FileHelper;

import java.nio.file.Path;
import java.util.List;

import static api.authentication.PostLogin.ADMIN_TOKEN;
import static org.assertj.core.api.Assertions.assertThat;

public class Session8Test extends BaseTest {
    PostUpload postUpload;

    @BeforeEach
    void setUp() {
        postUpload = new PostUpload();
    }

    @Test
    void upload_file() {
        Response res = postUpload.upload(ADMIN_TOKEN, "src/test/resources/bookstore-schema.json");
        res.prettyPrint();
    }

    @Test
    void download_file() {
        Response res = RestAssured.given()
                .get("https://run.mocky.io/v3/79ef35b8-4113-41d5-9f40-33d617408cad");

        Path path = FileHelper.createFile(res);
        List<BookCsvObject> books = FileHelper.convertCsvToObject(path, BookCsvObject.class);

        assertThat(books.get(0).getTitle()).isEqualToIgnoringCase("Fundamentals of Wavelets");
    }
}