package com.restful.booker.restfulsteps;

import com.restful.booker.constants.EndPoints;
import com.restful.booker.model.AuthPojo;
import com.restful.booker.model.BookingPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class BookingSteps {
    @Step("Creating Authentication token with userName : {0},password : {1} ")
    public String createAuthToken(String userName , String password) {
        AuthPojo authPojo = AuthPojo.getAuthPojo(userName, password);
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .when()
                .body(authPojo)
                .post("https://restful-booker.herokuapp.com/auth")
                .then().extract().path("token");


    }


        @Step("Creating booking with firstname : {0} ,lastname :{1} , totalprice : {2} , depositepaid : {3} ,bookingdates : {4},additionalneeds : {5}  ")
    public ValidatableResponse createBooking(String firstname, String lastname, int totalprice, boolean depositepaid, String additionalneeds, HashMap<String, String> bookingdates) {
        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstname, lastname, totalprice, depositepaid, bookingdates, additionalneeds);
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bookingPojo)
                .when()
                .post()
                .then();

    }

    @Step("Getting Single booking info by bookingID : {0}")
    public HashMap<String, Object> getBookingInfoByBookingID(int bookingID) {
        HashMap<String, Object> bookingMap = SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("bookingID", bookingID)
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then().statusCode(200)
                .extract()
                .path("");
        return bookingMap;
    }

    @Step("Gettng All booking info")
    public ValidatableResponse getAllBookingInfo() {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then();
    }

    @Step("Update booking with bookingId: {0}, firstName: {1}, lastName: {2}, totalPrice: {3}, depositPaid: {4}, bookingdates : {5} and additionalNeeds: {6}")
    public ValidatableResponse updateBooking(int bookingID, String firstname, String lastname, int totalprice, boolean depositepaid, HashMap<String, String> bookingdates, String additionalneeds, String token) {
        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstname, lastname, totalprice, depositepaid, bookingdates, additionalneeds);
        return SerenityRest.given().log().all()
                .header("Cookie","token="+token)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .pathParam("bookingID", bookingID)
                .body(bookingPojo)
                .when()
                .put(EndPoints.UPDATE_BOOKING_BY_ID)
                .then();
    }

    @Step("Delete bookings with BookingId: {0}")
    public ValidatableResponse deleteBookingWithBookingId(int bookingID, String token) {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Cookie","token="+token)
                .pathParam("bookingID", bookingID)
                .when()
                .delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then();
    }

}
