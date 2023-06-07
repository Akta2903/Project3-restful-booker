package com.restful.booker.restfulsteps;

import com.restful.booker.model.AuthPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class AuthSteps {

    @Step("Creating Authentication token with userName : {0},password : {1} ")
    public ValidatableResponse createAuthToken(String userName , String password)
    {
        AuthPojo authPojo = AuthPojo.getAuthPojo(userName,password);
       return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .when()
                .body(authPojo)
                .post("https://restful-booker.herokuapp.com/auth")
                .then().extract().path("token");




    }

}
