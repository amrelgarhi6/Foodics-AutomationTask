package api.driver;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

abstract class RequestSender {
    protected abstract Response send(RequestSpecification requestSpecification);

    protected abstract Response send(RequestSpecification requestSpecification, Integer httpStatus);
}