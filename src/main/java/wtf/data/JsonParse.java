package wtf.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.datatable.DataTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Класс для обработки JSON элементов
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class JsonParse {
    public static final Logger logger = LogManager.getLogger();

    public static String objectToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper
                    .writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String dataTableToJson(DataTable data) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        List<List<String>> rows = data.asLists(String.class);
        for (List<String> columns : rows) {
            switch (columns.get(2)) {
                case "string":
                    objectNode.put(columns.get(0), columns.get(1));
                    break;
                case "integer":
                    objectNode.put(columns.get(0), Integer.valueOf(columns.get(1)));
                    break;
                case "boolean":
                    objectNode.put(columns.get(0), Boolean.valueOf(columns.get(1)));
                    break;
            }
        }
        try {
            return mapper.writeValueAsString(objectNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

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
