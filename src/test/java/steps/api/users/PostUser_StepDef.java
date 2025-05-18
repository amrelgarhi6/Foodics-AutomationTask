package steps.api.users;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import static pages.api.users.PostUser.assertResponsePayloadContainsCorrectParameters;
import static pages.api.users.PostUser.invokePostUserEndpointWithValidKey;

public class PostUser_StepDef {


    @Given("the User invokes the Post-user API with valid successfully.")
    public void theUserInvokesThePostUserAPIWithValidSuccessfully() {
        invokePostUserEndpointWithValidKey();
    }

    @And("API response payload should contain a [Name - job - id - createdDate] objects")
    public void apiResponsePayloadShouldContainANameJobIdCreatedDateObjects() {
        assertResponsePayloadContainsCorrectParameters();
    }
}