package org.ease.module;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.ease.utils.YamlUtils;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Warehouse extends BaseModule {
    private static final Logger logger = LogManager.getLogger(Warehouse.class);
    JsonPath wareHouseApiResponse;
    Response responsebody;

    YamlUtils yamlUtils=new YamlUtils();

    public void iHitDefaultGroupApiWithCorrectAuthenticationToken(){
        String accessToken=getAccessToken();
        hitDefaultGroupWareHouseApiWithAccessToken(accessToken);
    }

    public void iHitDefaultGroupApiWithCorrectAuthenticationTokenAndWrongEndPoint(){
        String accessToken=getAccessToken();
        hitDefaultGroupWareHouseApiWithAccessTokenAndWrongEndPoint(accessToken);
    }

    public void iHitDefaultGroupApiWithInCorrectAuthenticationToken(){
        String accessToken=getWrongAccessToken();
        hitDefaultGroupWareHouseApiWithAccessToken(accessToken);
    }

    public void validateSchemaAndMandatoryFields(){
        InputStream schema = getClass().getClassLoader().getResourceAsStream("warehouse-schema.json");
        if (schema == null) {
            throw new IllegalArgumentException("Schema file not found in resources folder.");
        }
        logger.info(schema);
        responsebody.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schema));
    }

    public void hitDefaultGroupWareHouseApiWithAccessToken(String accessToken){
        responsebody= (Response) RestAssured.given().relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer "+accessToken)
                .get(yamlUtils.fetchStringFromYaml("hostname")+"/api/v2/manage/warehouse/master/all/default");
        logger.info("Response Code For Get All Default WareHouse Request: "+responsebody.getStatusCode());
        wareHouseApiResponse = new JsonPath(responsebody.asString());
    }

    public void hitDefaultGroupWareHouseApiWithAccessTokenAndWrongEndPoint(String accessToken){
        responsebody=RestAssured.given().relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer "+accessToken)
                .get(yamlUtils.fetchStringFromYaml("hostname")+"/api/v2/manage/warehouse/master/all/default/test");
        logger.info("Response Code For Get All Default WareHouse Request (Wrong Endpoint) : "+responsebody.getStatusCode());
        wareHouseApiResponse = new JsonPath(responsebody.asString());
    }

    public void iValidateStatusCodeOfApiResponse(int statusCode){
       Assert.assertEquals(responsebody.getStatusCode(),statusCode);
       logger.info("Status Message: "+responsebody.getStatusLine());
    }
}

