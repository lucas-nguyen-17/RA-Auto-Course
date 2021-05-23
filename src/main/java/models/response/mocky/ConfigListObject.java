package models.response.mocky;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ConfigListObject {
    @JsonProperty("config_list")
    private List<SearchConfigObject> configList;
    private boolean success;
    private int total;
}
