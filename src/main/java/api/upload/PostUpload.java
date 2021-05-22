package api.upload;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;

public class PostUpload {

    private Response callAPI(String token, String pathToFile) {
        return RestAssured.given()
                .multiPart("file-key", new File(pathToFile))
                .post("http://localhost:8000/upload");
    }

    public Response upload(String token, String pathToFile) {
        return callAPI(token, pathToFile);
    }
}
