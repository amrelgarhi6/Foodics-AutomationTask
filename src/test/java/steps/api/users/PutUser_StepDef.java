package steps.api.users;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import static pages.api.users.PutUser.assertResponsePayloadUpdatedCorrectly;
import static pages.api.users.PutUser.invokePutUserEndpointWithValidKey;

public class PutUser_StepDef {

    @Given("the User invokes the Put-user API with valid successfully.")
    public void theUserInvokesThePutUserAPIWithValidSuccessfully() {
        invokePutUserEndpointWithValidKey();
    }

    @And("API response payload should be updated with new objects [Name - job - id - createdDate]")
    public void apiResponsePayloadShouldBeUpdatedWithNewObjectsNameJobIdCreatedDate() {
        assertResponsePayloadUpdatedCorrectly();
    }
}