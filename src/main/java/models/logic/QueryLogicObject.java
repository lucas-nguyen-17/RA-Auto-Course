package models.logic;

import lombok.Data;

@Data
public class QueryLogicObject {
    private DataObject data;

    public QueryLogicObject() {
        this.data = new DataObject();
    }
}
