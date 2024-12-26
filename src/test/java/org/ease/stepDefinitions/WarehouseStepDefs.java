package org.ease.stepDefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.ease.module.Warehouse;

public class WarehouseStepDefs {
    Warehouse warehouseModule=new Warehouse();
    @Given("I hit the url of get products api endpoint")
    public void i_hit_the_url_of_get_products_api_endpoint() {
        warehouseModule.getAccessToken();
    }

    @When("I pass the url of products in the request")
    public void i_pass_the_url_of_products_in_the_request() {
        warehouseModule.validateSchemaAndMandatoryFields();;
    }

    @Then("I receive the response code as {int}")
    public void i_receive_the_response_code_as(Integer statusCode) {
        warehouseModule.iValidateStatusCodeOfApiResponse(statusCode);
    }

    @And("I validate schema and mandatory fields")
    public void iValidateSchemaAndMandatoryFields() {
        warehouseModule.validateSchemaAndMandatoryFields();
    }

    @When("I hit default warehouse api with the generated access token form token api")
    public void iHitDefaultWarehouseApiWithTheGeneratedAccessTokenFormTokenApi() {
        warehouseModule.iHitDefaultGroupApiWithCorrectAuthenticationToken();
    }

    @When("I hit default warehouse api with the incorrect access token form token api")
    public void iHitDefaultWarehouseApiWithTheIncorrectAccessTokenFormTokenApi() {
        warehouseModule.iHitDefaultGroupApiWithInCorrectAuthenticationToken();
    }

    @When("I hit default warehouse api with the generated access token form token api but with incorrect endpoint")
    public void iHitDefaultWarehouseApiWithTheGeneratedAccessTokenFormTokenApiButWithIncorrectEndpoint() {
        warehouseModule.iHitDefaultGroupApiWithCorrectAuthenticationTokenAndWrongEndPoint();
    }
}
