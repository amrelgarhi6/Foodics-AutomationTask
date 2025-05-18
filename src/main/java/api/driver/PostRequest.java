package api.driver;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest extends RequestSender {
    @Override
    protected Response send(RequestSpecification requestSpecification) {
        return RestAssured.given().spec(requestSpecification).post().then().extract().response();
    }

    @Override
    protected Response send(RequestSpecification requestSpecification, Integer httpStatus) {
        return RestAssured.given().spec(requestSpecification).post().then().statusCode(httpStatus).extract().response();
    }
}