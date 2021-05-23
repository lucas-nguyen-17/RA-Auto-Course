package models.response.mocky;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchConfigObject {
//    private String description;
//    private int direction;
//    private String id;
    private String ip;
    private int port;
//    private int protocol;
//    private long time_created;

    public SearchConfigObject(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public SearchConfigObject() {
    }
}
