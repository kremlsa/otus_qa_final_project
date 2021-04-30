package wtf.common;

import io.restassured.http.ContentType;

/**
 * Enum для получения content Type из строкового значения
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public enum CTname {

    TEXT(ContentType.TEXT),
    HTML(ContentType.HTML),
    JSON(ContentType.JSON),
    DEFAULT(ContentType.HTML);

    ContentType contentType;

    CTname(ContentType contentType) {
        this.contentType = contentType;
    }

    public ContentType getContentType() {
        return contentType;
    }

    //Возвращаем подходящий тип содержимого, поиск регистронезависим
    public static ContentType getCT(String name) {
        for (CTname value: values()) {
            if (value.toString().equalsIgnoreCase(name)) {
                return value.getContentType();
            }
        }

        // Возвращаем браузер по умолчанию Chrome если не нашлось подходящего кандидата
        return DEFAULT.getContentType();
    }
}
