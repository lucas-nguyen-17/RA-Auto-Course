package models.logic;

import lombok.Data;

@Data
public class DataLogicObject {
    private String operation;

    public DataLogicObject() {
        this.operation = ">";
    }
}
