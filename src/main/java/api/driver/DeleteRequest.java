package api.driver;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteRequest extends RequestSender {
    @Override
    protected Response send(RequestSpecification requestSpecification) {
        return RestAssured.given().spec(requestSpecification).delete().then().extract().response();
    }

    @Override
    protected Response send(RequestSpecification requestSpecification, Integer httpStatus) {
        return RestAssured.given().spec(requestSpecification).delete().then().statusCode(httpStatus).extract().response();
    }
}