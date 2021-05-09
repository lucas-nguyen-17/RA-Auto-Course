package models.logic;

import lombok.Data;

@Data
public class TargetObject {
    private QueryObject query;
    private String string;

    public TargetObject() {
        this.query = new QueryObject();
        this.string = "Hi";
    }
}
