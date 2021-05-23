package api.mocky;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.response.mocky.ConfigListObject;
import models.response.mocky.SearchConfigObject;

import java.util.ArrayList;
import java.util.List;

public class GetConfig {

    private Response res = callAPI();

    private Response callAPI() {
        return RestAssured.given()
                .get("https://run.mocky.io/v3/812f6263-4ae8-4c0f-b98e-0d7ca765dd9b");
    }

    public List<SearchConfigObject> get() {
        List<String> ips = JsonPath.read(res.asString(), "$.config_list[*].ip");
        List<Integer> ports = JsonPath.read(res.asString(), "$.config_list[*].port");

        List<SearchConfigObject> configObjects = new ArrayList<>();

        for (int i = 0; i < ips.size(); i++) {
            SearchConfigObject configObject = new SearchConfigObject(ips.get(i), ports.get(i));
            configObjects.add(configObject);
        }
        return configObjects;
    }

    public SearchConfigObject getFirstConfig(){
        return get().get(0);
    }

    public ConfigListObject getAll(){
        return res.as(ConfigListObject.class);
    }

    public int getStatusCode() {
        return res.getStatusCode();
    }
}
