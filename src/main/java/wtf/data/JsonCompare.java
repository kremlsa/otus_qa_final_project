package wtf.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static org.testng.Assert.assertTrue;

public class JsonCompare {

    public static final Logger logger = LogManager.getLogger();

    public static void jsonContainsCompare(String s1, String s2) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode actualObj1 = mapper.readTree(s1);
            JsonNode actualObj2 = mapper.readTree(s2);
            JsonContainsComparator cmp = new JsonContainsComparator();
            assertTrue(actualObj1.equals(cmp, actualObj2), s1 + " не содержит" + s2);
            logger.info("{} содержит {}", s1, s2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void jsonLenientCompare(String json1, String json2) {
        try {
            JSONAssert.assertEquals(json1, json2, JSONCompareMode.LENIENT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void jsonStrictCompare(String json1, String json2) {
        try {
            JSONAssert.assertEquals(json1, json2, JSONCompareMode.STRICT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
