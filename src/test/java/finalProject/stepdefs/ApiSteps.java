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
import wtf.data.JsonParse;
import java.util.List;
import java.util.stream.Collectors;

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
        System.out.println(json);
        BaseStep.setJsonResult(json.toString());
    }

    @И("В ответе присутствует объект Json с ключом {string} и числовым значением {string}")
    public void findPairInJsonInteger(String key, String value) {
        Assert.assertTrue(response.body().asString().contains("\"" + key + "\":" + value));
    }

    @И("В объекте есть ключ {string} со значением {string}")
    public void findPair(String key, String value) {
        Assert.assertTrue(parser.isStringPairFind(json, key, value));
    }

    @И("параметр Get запроса {string} со значением {string}")
    public void setGetParameter(String parameter, String value) {
        restApi.setGetParameters(parameter, value);
    }

    @И("Устанавливается контекст")
    public void setContext() {
        restApi.createContext(url, contentType);
    }

    @Когда("Отправляется запрос Get с параметрами")
    public void sendParametrizedGet() {
        this.response = restApi.sendGetRequestWithParams(api);
    }
}
