package com.restful.booker.restfulinfo;

import com.restful.booker.model.AuthPojo;
import com.restful.booker.restfulsteps.AuthSteps;
import com.restful.booker.testbase.AuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class AuthCreateTest extends AuthTestBase {

    static String userName = "admin";
    static String password = "password123";
    static String token;
     @Steps
     AuthSteps authSteps;

    @Title("Create an Authentication Token")
    @Test
    public  void  createAuthToken()
    {
        AuthPojo authPojo = AuthPojo.getAuthPojo(userName,password);
        ValidatableResponse response = SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(authPojo)
                .when()
                .post("https://restful-booker.herokuapp.com/auth")
                .then()
                .extract().path("token");
        token = response.toString();




    }



}
