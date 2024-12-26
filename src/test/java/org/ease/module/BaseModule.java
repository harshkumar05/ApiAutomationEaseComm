package org.ease.module;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ease.utils.YamlUtils;
import org.junit.Assert;

import java.util.HashMap;

public class BaseModule {

    private static final Logger logger = LogManager.getLogger(BaseModule.class);
    YamlUtils yamlUtils=new YamlUtils();
    public static JsonPath authenticationResponse;

    public String getAccessToken(){
        return authenticationResponse.getString("token");
    }

    public String getWrongAccessToken(){
        return yamlUtils.fetchStringFromYaml("wrongaccesstoken");
    }

    public void validateStatusCode(int statusCode){
        HashMap<String,String> requestBody= new HashMap<>();
        requestBody.put("username",yamlUtils.fetchStringFromYaml("username"));
        requestBody.put("password",yamlUtils.fetchStringFromYaml("password"));
        Response responsebody=RestAssured.given().relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(yamlUtils.fetchStringFromYaml("hostname")+"/api/v2/login");
        logger.info("Response Code For Authentication Request: "+responsebody.getStatusCode());
        authenticationResponse=new JsonPath(responsebody.asString());
        Assert.assertEquals(statusCode,responsebody.getStatusCode());
    }

}
