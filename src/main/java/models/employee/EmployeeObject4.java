package models.employee;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class EmployeeObject4 {
    private INestObject data;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String string;
}
