package steps.api.users;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import static pages.api.users.GetUser.assertResponsePayloadContainsCorrectParameters;
import static pages.api.users.GetUser.invokeGetUserEndpointWithValidKey;

public class GetUser_StepDef {



    @Given("the User invokes the Get-user API with valid successfully.")
    public void theUserInvokesTheGetUserAPIWithValidSuccessfully() {
        invokeGetUserEndpointWithValidKey();
    }


    @And("API response payload should contain a [Data - Support] objects and its main data")
    public void apiResponsePayloadShouldContainADataSupportObjectsAndItsMainData() {
        assertResponsePayloadContainsCorrectParameters();
    }

}