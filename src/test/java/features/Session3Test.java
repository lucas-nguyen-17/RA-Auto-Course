package features;

import api.echo.PostEcho;
import builder.EmployeeObjectBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.request.employee.*;
import models.request.logic.TargetObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static api.authentication.PostLogin.ADMIN_TOKEN;

public class Session3Test {
    PostEcho postEcho;

    @BeforeEach
    void setUp() {
        postEcho = new PostEcho();
    }

    @Test
    void test1() {
        EmployeeObject employee = EmployeeObjectBuilder.createDefault();
        Response res = postEcho.get(employee, ADMIN_TOKEN);
        res.prettyPrint();
    }

    @Test
    void test2() {
        Map<String, Object> account = new HashMap<>();
        account.put("name", "Giang");
        account.put("age", 20);
        account.put("is_admin", true);

        EmployeeObject2 employee = new EmployeeObject2();
        employee.setAccount(account);
        employee.setColors(Arrays.asList("red", "yellow", "pink"));
        employee.setStar(4.5);

        RestAssured.given()
                .log().body(true)
                .contentType(ContentType.JSON)
                .body(employee)
                .post("https://postman-echo.com/post");
    }

    @Test
    void test3() {
        Map<String, Object> account = new HashMap<>();
//        account.put("name", "Giang");
//        account.put("age", 20);
//        account.put("is_admin", true);
        account.put("machine", "BMW");
        account.put("created_time", 2423543534L);

        EmployeeObject3 employee = new EmployeeObject3();
        employee.setData(account);
        employee.setString("hi");

        RestAssured.given()
                .log().body(true)
                .contentType(ContentType.JSON)
                .body(employee)
                .post("https://postman-echo.com/post");
    }

    @Test
    void test4() {
        EmployeeObject4 employee = new EmployeeObject4();
        employee.setData(new MachineObject());
        employee.setString("hihi");

        RestAssured.given()
                .log().body(true)
                .contentType(ContentType.JSON)
                .body(employee)
                .post("https://postman-echo.com/post");
    }

    @Test
    void test5() {
        TargetObject target = new TargetObject();

        RestAssured.given()
                .log().body(true)
                .contentType(ContentType.JSON)
                .body(target)
                .post("https://postman-echo.com/post");
    }
}
