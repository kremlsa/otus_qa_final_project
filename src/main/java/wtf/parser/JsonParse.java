package wtf.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для обработки JSON элементов
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class JsonParse {
    public static final Logger logger = LogManager.getLogger();

    /**
     * Метод нахождения json объектов в массиве
     *
     * @param jsonString строковое представление массива json String
     * @return список json объектов List<JSONObject>
     */
    public List<JSONObject> parseJsonArray(String jsonString) {
        List<JSONObject> results = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(jsonString.trim());
            for (int i = 0; i < array.length(); i++) {
                results.add((JSONObject) array.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * Метод для поиска пары ключ:значение в json объекте
     *
     * @param json объект для поиска JSON
     * @param key ключ String
     * @param value значение String
     * @return результат поиска boolean
     */
    public boolean isStringPairFind(JSONObject json, String key, String value) {
        try {
            return json.getString(key).equals(value);
        } catch (JSONException e) {
            return false;
        }
    }

    /**
     * Метод для поиска элемента JSON в списке по паре ключ:значение
     *
     * @param jsons список объектов для поиска JSON
     * @param key ключ String
     * @param value значение String
     * @return элемент содержащий искомую пару JSONObject
     */
    public JSONObject isObjectWithStringPairInList(List<JSONObject> jsons, String key, String value) {
        try {
            for (JSONObject json : jsons) {
                if (json.getString(key).equals(value)) {
                    logger.info("Найден json: {}", json.toString());
                    return json;
                }
            }
        } catch (JSONException e) {
            return null;
        }
        return null;
    }
}
