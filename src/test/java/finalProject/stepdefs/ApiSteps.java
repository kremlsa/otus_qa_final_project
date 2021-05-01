package finalProject.stepdefs;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import wtf.actions.rest.RestApi;
import wtf.common.CTname;
import wtf.parser.JsonParse;
import java.util.List;

/**
 * Класс для описания шагов Cucumber для сценариев
 * связанных с проверками REST API
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class ApiSteps {

    RestApi restApi = new RestApi();
    JsonParse parser = new JsonParse();
    JSONObject json;
    List<JSONObject> jsons;
    String url;
    String api;
    Response response;
    ContentType contentType;

    @Дано("Ресурс {string}")
    public void setUrl(String url) {
        this.url = url;
    }

    @Дано("Адрес api {string}")
    public void setApi(String api) {
        this.api = api;
    }

    @И("Тип содержимого {string}")
    public void setContentType(String contentType) {
        this.contentType = CTname.getCT(contentType);
    }

    @Когда("Отправляется запрос Get")
    public void sendGetRequest() {
        restApi.createContext(url, contentType);
        this.response = restApi.sendGetRequest(api, "");
    }

    @То("Получен ответ")
    public void getResponse() {
        Assert.assertEquals(200, response.statusCode());
        jsons = parser.parseJsonArray(response.body().asString());
    }

    @И("В ответе присутствует объект Json с ключом {string} и значением {string}")
    public void findPairInJson(String key, String value) {
        json = parser.isObjectWithStringPairInList(jsons, key, value);
    }

    @И("В ответе присутствует объект Json с ключом {string} и числовым значением {string}")
    public void findPairInJsonInteger(String key, String value) {
        Assert.assertTrue(response.body().asString().contains("\"" + key + "\":" + value));
    }

    @И("В объекте есть ключ {string} со значением {string}")
    public void findPair(String key, String value) {
        Assert.assertTrue(parser.isStringPairFind(json, key, value));
    }
}
