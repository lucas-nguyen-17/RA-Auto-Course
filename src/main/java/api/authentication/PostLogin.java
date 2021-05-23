package api.authentication;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.request.authentication.UserObject;

public class PostLogin {

    public static final PostLogin LOGIN = getInstance();
    public static final String ADMIN_TOKEN = LOGIN.getToken(UserObject.admin());
    public static final String ADMIN_EDITOR = LOGIN.getToken(UserObject.editor());

    private PostLogin() {
    }

    public static PostLogin getInstance() {
        return new PostLogin();
    }

    private Response callAPI(UserObject user){
        return RestAssured.given().contentType(ContentType.JSON)
                .body(user)
                .get("https://run.mocky.io/v3/024a97bf-f8a9-47b2-b0ec-e4755a0d0e89");
    }

    public String getToken(UserObject user) {
        Response res = callAPI(user);
        return res.path("access_token");
    }
}
