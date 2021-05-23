package models.request.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MachineObject implements INestObject{
    private String machine;
    @JsonProperty("created_time")
    private long createdTime;

    public MachineObject(){
        this.machine = "BMW";
        this.createdTime = System.currentTimeMillis();
    }
}
