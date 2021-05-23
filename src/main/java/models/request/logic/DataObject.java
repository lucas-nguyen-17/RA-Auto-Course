package models.request.logic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataObject {
    @JsonProperty("logic")
    private DataLogicObject dataLogic;
    private String name;
    private int age;
    @JsonProperty("is_admin")
    private boolean isAdmin;

    public DataObject() {
        this.dataLogic = new DataLogicObject();
        this.name = "Giang";
        this.age = 20;
        this.isAdmin = true;
    }
}
