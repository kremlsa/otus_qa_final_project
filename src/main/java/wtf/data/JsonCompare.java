package wtf.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static org.testng.Assert.assertTrue;

/**
 * Класс для сравнения json объектов
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class JsonCompare {

    public static final Logger logger = LogManager.getLogger();

    /**
     * Метод для сравнения двух строк в формате json
     * сравнивает по частичному вхождению значений первого json
     * в соответствующие поля второго json
     *
     * @param s1 первый json String
     * @param s2 второй json String
     */
    public static void jsonContainsCompare(String s1, String s2) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode actualObj1 = mapper.readTree(s1);
            JsonNode actualObj2 = mapper.readTree(s2);
            JsonContainsComparator cmp = new JsonContainsComparator();
            assertTrue(actualObj1.equals(cmp, actualObj2), s1 + " не содержит" + s2);
            logger.info("{} содержит {}", s1, s2);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Метод для нестрогого сравнения двух строк в формате json
     *
     * @param json1 первый json String
     * @param json2 второй json String
     */
    public static void jsonLenientCompare(String json1, String json2) {
        try {
            JSONAssert.assertEquals(json1, json2, JSONCompareMode.LENIENT);
        } catch (JSONException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Метод для строгого сравнения двух строк в формате json
     *
     * @param json1 первый json String
     * @param json2 второй json String
     */
    public static void jsonStrictCompare(String json1, String json2) {
        try {
            JSONAssert.assertEquals(json1, json2, JSONCompareMode.STRICT);
        } catch (JSONException e) {
            logger.error(e.getMessage());
        }
    }

}
