package com.restful.booker.constants;

public class EndPoints {
    //Endpoints for booking
    public static final String GET_ALL_BOOKING = "/booking";

    public static final String GET_SINGLE_BOOKING_BY_ID = "/{bookingID}";
    public static final String UPDATE_BOOKING_BY_ID = "/{bookingID}";
    public static final String DELETE_BOOKING_BY_ID = "/{bookingID}";

    //EndPoints for Auth

    public static String CREATE_AUTH_TOKEN="/auth";
}
