package finalProject.stepdefs;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import wtf.actions.rest.RestApi;
import wtf.common.CTname;


public class ApiSteps {

    RestApi restApi = new RestApi();
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
    }

    @И("В ответе присутствует объект Json с ключом {string} и значением {string}")
    public void findPairInJson(String key, String value) {
        Assert.assertTrue(response.body().asString().contains("\"" + key + "\":" + "\"" + value + "\""));
    }

    @И("В ответе присутствует объект Json с ключом {string} и числовым значением {string}")
    public void findPairInJsonInteger(String key, String value) {
        Assert.assertTrue(response.body().asString().contains("\"" + key + "\":" + value));
    }
}
