package api.driver;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutRequest extends RequestSender {
    @Override
    protected Response send(RequestSpecification requestSpecification) {
        return RestAssured.given().spec(requestSpecification).put().then().extract().response();
    }

    @Override
    protected Response send(RequestSpecification requestSpecification, Integer httpStatus) {
        return RestAssured.given().spec(requestSpecification).put().then().statusCode(httpStatus).extract().response();
    }
}