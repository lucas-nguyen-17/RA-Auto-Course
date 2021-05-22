package api.echo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.employee.EmployeeObject;
import utilities.ReqHeader;

public class PostEcho {

    private Response callAPI(EmployeeObject employee, String token) {
        return RestAssured.given()
                .spec(ReqHeader.withToken(token))
                .body(employee)
                .post("https://postman-echo.com/post");
    }

    public Response get(EmployeeObject employee, String token) {
        return callAPI(employee, token);
    }
}
