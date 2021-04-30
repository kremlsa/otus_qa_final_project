package wtf.actions.rest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class RestApi {

    RequestSpecification spec;

    public void createContext(String url, ContentType contentType) {
        spec = given()
                .contentType(contentType)
                .baseUri(url);
    }

    public Response sendPostRequest(String body, String api) {
        return given()
                .spec(spec)
                .with()
                .body(body)
                .when()
                .log().all()
                .post(api);
    }

    public Response sendGetRequest(String api, String parameter) {
        return given()
                .spec(spec)
                .with()
                .pathParam("parameter", parameter)
                .when()
                .log().all()
                .get(api + "{parameter}");
    }
}
