package wtf.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.util.Comparator;

/**
 * Компаратор для сравнения частичного совпадения полей json
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class JsonContainsComparator implements Comparator<JsonNode>
{
    @Override
    public int compare(JsonNode o1, JsonNode o2) {
        if (o1.equals(o2)) {
            return 0;
        }
        if ((o1 instanceof TextNode) && (o2 instanceof TextNode)) {
            String s1 = ((TextNode) o1).asText().toLowerCase();
            String s2 = ((TextNode) o2).asText().toLowerCase();
            if (s1.contains(s2)) {
                return 0;
            }
        }
        return 1;
    }
}