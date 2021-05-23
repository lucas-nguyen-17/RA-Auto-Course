package models.request.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountObject implements INestObject{
    private String name;
    private int age;
    @JsonProperty("is_admin")
    private boolean isAdmin;

    public AccountObject() {
        this.name = "Giang";
        this.age = 20;
        this.isAdmin = true;
    }
}
