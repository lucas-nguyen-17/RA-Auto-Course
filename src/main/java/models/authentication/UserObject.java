package models.authentication;

import lombok.Data;

import static utilities.AppProperty.ADMIN_ACCOUNT;
import static utilities.AppProperty.EDITOR_ACCOUNT;
import static utilities.LoadConfig.CONFIG;

@Data
public class UserObject {
    private String username;
    private String password;

    public UserObject(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static UserObject admin() {
        String username = CONFIG.getProperty(ADMIN_ACCOUNT);
        return new UserObject(username, "1234");
    }

    public static UserObject editor() {
        String username = CONFIG.getProperty(EDITOR_ACCOUNT);
        return new UserObject("editor", "1234");
    }
}
