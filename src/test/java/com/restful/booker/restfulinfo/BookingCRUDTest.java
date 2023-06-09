package com.restful.booker.restfulinfo;

import com.restful.booker.restfulsteps.BookingSteps;
import com.restful.booker.testbase.BookingTestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class BookingCRUDTest extends BookingTestBase {
    static String firstname = "Prime" + TestUtils.getRandomValue();
    static String lastname = "Admin" + TestUtils.getRandomValue();
    static int totalprice = 200;
    static Boolean depositpaid = true;
    static String additionalneeds = "Lunch";
    static int bookingID;
    static String token;
    static String username ="admin";
    static String password ="password123";

    @Steps
    BookingSteps bookingSteps;


    @Title("This test will generate token")
    @Test
    public void test001(){

        token = bookingSteps.createAuthToken(username,password);
        System.out.println("Token" + token);
    }
    @Title("This will create new Booking")
    @Test
    public void test002() {

        HashMap<String,String> bookingdate = new HashMap<>();
        bookingdate.put("checkin", "2023-03-29");
        bookingdate.put("checkout", "2023-04-10");
        ValidatableResponse response = bookingSteps.createBooking(firstname,lastname,totalprice,depositpaid,additionalneeds,bookingdate);
        response.log().all().statusCode(200);
        bookingID = response.extract().path("bookingid");
        System.out.println("BookingID : " + bookingID);
    }

    @Title("Verify if Bookind is done or not")
    @Test
    public void test003()
    {
        HashMap<String,Object> bookingMap = bookingSteps.getBookingInfoByBookingID(bookingID);
        Assert.assertThat(bookingMap,hasValue(firstname));
        // productId=(int) productMap.get("id");
        System.out.println("Booking Id " + bookingID);

    }
    @Title("Update the product information and verify the updated information")
    @Test
    public void test004()
    {

        HashMap<String,String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2022-03-29");
        bookingdates.put("checkout", "2022-04-10");
        firstname = "Sally" + TestUtils.getRandomValue() + "Prime";
        AuthCreateTest auth = new AuthCreateTest();

        bookingSteps.updateBooking( bookingID,  firstname,  lastname,  totalprice, depositpaid,  bookingdates,  additionalneeds,  token).statusCode(200);

        HashMap<String , Object> bookingMap = bookingSteps.getBookingInfoByBookingID(bookingID);
        System.out.println(bookingMap);
        Assert.assertThat(bookingMap,hasValue(firstname));
    }
    @Title("Delete the Booking and verify if the booking is cancelled")
    @Test
    public void test005()
    {
        bookingSteps.deleteBookingWithBookingId(bookingID,token)
                .statusCode(201);





    }

    @Title("Get booking")
    @Test
    public void test006() {
        ValidatableResponse response = bookingSteps.getAllBookingInfo().log().all();
        response.statusCode(200);
    }


}
