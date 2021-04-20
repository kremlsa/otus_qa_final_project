package finalProject.factory;

public enum BrowserName {

    CHROME("chrome"),
    FIREFOX("firefox"),
    OPERA("opera"),
    DEFAULT("chrome");

    String browserName;

    BrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }

    //Возвращаем подходящий браузера, поиск регистронезависим
    public static BrowserName findByName(String name) {
        for (BrowserName value: values()) {
            if (value.browserName.equals(name.replace("'","").toLowerCase())) {
                return value;
            }
        }

        // Возвращаем браузер по умолчанию Chrome если не нашлось подходящего кандидата
        return DEFAULT;
    }
}
