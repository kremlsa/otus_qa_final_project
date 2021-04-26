package wtf.common;

/**
 * Enum для парсинга имени браузера из параметров maven
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
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
