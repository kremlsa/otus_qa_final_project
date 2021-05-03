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

    /**
     * Метод для нахождения объекта BrowserName по его названию
     *
     * @param name наименование браузера String
     * @return объект представляющий имя браузера BrowserName
     */
    public static BrowserName findByName(String name) {
        for (BrowserName value: values()) {
            if (value.browserName.equalsIgnoreCase(name)) {
                return value;
            }
        }

        // Возвращаем браузер по умолчанию Chrome если не нашлось подходящего кандидата
        return DEFAULT;
    }
}
