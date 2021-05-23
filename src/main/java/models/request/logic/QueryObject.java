package models.request.logic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QueryObject {
    @JsonProperty("logic")
    private QueryLogicObject queryLogic;

    public QueryObject() {
        this.queryLogic = new QueryLogicObject();
    }
}
