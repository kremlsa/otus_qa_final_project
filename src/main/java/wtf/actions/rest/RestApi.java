package wtf.actions.rest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Класс для взаимодействия с REST API
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
public class RestApi {

    RequestSpecification spec;
    Map<String, Object > parameters;

    /**
     * Метод для создания контекста RestAssured
     * и очистки списка параметров
     *
     * @param url адрес ресурса String
     * @param contentType тип содержимого ContentType
     */
    public void createContext(String url, ContentType contentType) {
        parameters = new LinkedHashMap<>();
        spec = given()
                .contentType(contentType)
                .baseUri(url);
    }

    /**
     * Метод для отправки POST запросов
     *
     * @param body содержимое запроса String
     * @param api адрес API String
     * @return ответ от ресурса Response
     */
    public Response sendPostRequest(String body, String api) {
        return given()
                .spec(spec)
                .with()
                .body(body)
                .when()
                .log().all()
                .post(api);
    }

    /**
     * Метод для отправки GET запросов
     *
     * @param api адрес API String
     * @param parameter параметр String
     * @return ответ от ресурса Response
     */
    public Response sendGetRequest(String api, String parameter) {
        return given()
                .spec(spec)
                .with()
                .pathParam("parameter", parameter)
                .when()
                .log().all()
                .get(api + "{parameter}");
    }

    /**
     * Метод для параметризации GET запросов
     *
     *
     */
    public void setGetParameters(String parameter, Object value) {
        this.parameters.putIfAbsent(parameter, value);
    }

    /**
     * Метод для отправки GET запросов
     * со списком параметров
     *
     * @return ответ от ресурса Response
     */
    public Response sendGetRequestWithParams(String api) {
        return given()
                .params(this.parameters)
                .spec(spec)
                .with()
                .when()
                .log().all()
                .get(api);
    }
}