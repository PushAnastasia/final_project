package shop.bugred.utils.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Specification {
    static String baseUri = "http://shop.bugred.ru";
    static String basePath = "/api/items/";

    public static RequestSpecification prepareRequest() {
        RestAssuredConfig config = RestAssuredConfig.newConfig().logConfig(new LogConfig().defaultStream(System.out));
        return given(new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(basePath)
                .setConfig(config)
                .setContentType(ContentType.JSON)
                .build().log().all());
    }

    public static ResponseSpecification responseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("status", equalTo("ok"))
                .build();
    }
}
