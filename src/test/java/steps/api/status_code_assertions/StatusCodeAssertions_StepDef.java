package steps.api.status_code_assertions;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import pages.api.status_code_assertions.StatusCodeAssertions;
import api.context.ScenarioContext;
import api.context.Context;

public class StatusCodeAssertions_StepDef {
    /**
     * SC_OK = 200
     **/
    @Then("API response status code should be SC_OK.")
    public void apiResponseStatusCodeShouldBeSC_OK() {
        StatusCodeAssertions.apiResponseStatusCodeShouldBeSC_OK((Response) ScenarioContext.getContext(Context.RESPONSE_PAYLOAD));
    }

    /**
     * SC_CREATED = 201
     **/
    @Then("API response status code should be SC_CREATED.")
    public void apiResponseStatusCodeShouldBeSC_CREATED() {
        StatusCodeAssertions.apiResponseStatusCodeShouldBeSC_CREATED((Response) ScenarioContext.getContext(Context.RESPONSE_PAYLOAD));
    }

    /**
     * SC_BAD_REQUEST = 400
     **/
    @Then("API response status code should be SC_BAD_REQUEST.")
    public void apiResponseStatusCodeShouldBeSC_BAD_REQUEST() {
        StatusCodeAssertions.apiResponseStatusCodeShouldBeSC_BAD_REQUEST((Response) ScenarioContext.getContext(Context.RESPONSE_PAYLOAD));
    }

    /**
     * SC_UNAUTHORIZED = 401
     **/
    @Then("API response status code should be SC_UNAUTHORIZED.")
    public void apiResponseStatusCodeShouldBeSC_UNAUTHORIZED() {
        StatusCodeAssertions.apiResponseStatusCodeShouldBeSC_UNAUTHORIZED((Response) ScenarioContext.getContext(Context.RESPONSE_PAYLOAD));
    }

    /**
     * SC_FORBIDDEN = 403
     **/
    @Then("API response status code should be SC_FORBIDDEN.")
    public void apiResponseStatusCodeShouldBeSC_FORBIDDEN() {
        StatusCodeAssertions.apiResponseStatusCodeShouldBeSC_FORBIDDEN((Response) ScenarioContext.getContext(Context.RESPONSE_PAYLOAD));
    }

    /**
     * SC_NOT_FOUND = 404
     **/
    @Then("API response status code should be SC_NOT_FOUND.")
    public void apiResponseStatusCodeShouldBeSC_NOT_FOUND() {
        StatusCodeAssertions.apiResponseStatusCodeShouldBeSC_NOT_FOUND((Response) ScenarioContext.getContext(Context.RESPONSE_PAYLOAD));
    }
}