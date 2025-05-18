package api.driver;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PatchRequest extends RequestSender {
    @Override
    protected Response send(RequestSpecification requestSpecification) {
        return RestAssured.given().spec(requestSpecification).patch().then().extract().response();
    }

    @Override
    protected Response send(RequestSpecification requestSpecification, Integer httpStatus) {
        return RestAssured.given().spec(requestSpecification).patch().then().statusCode(httpStatus).extract().response();
    }
}