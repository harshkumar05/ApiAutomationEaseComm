package org.ease.stepDefinitions;

import io.cucumber.java.en.Given;
import org.ease.module.BaseModule;

public class CommonStepDefs {

    BaseModule baseModule = new BaseModule();
    @Given("I hit the authentication request to get the access token")
    public void i_hit_the_authentication_request_to_get_the_access_token() {
        baseModule.getAccessToken();
    }


    @Given("I hit the authentication request with correct user details i get response code as {int}")
    public void i_hit_the_authentication_request_with_correct_user_details_i_get_response_code_as(Integer int1) {
        baseModule.validateStatusCode(int1);
    }
}
