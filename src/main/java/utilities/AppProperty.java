package utilities;

public enum AppProperty {
    BASE_URL("baseUrl"),
    ADMIN_ACCOUNT("admin-account"),
    EDITOR_ACCOUNT("editor-account");

    private final String key;

    AppProperty(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
