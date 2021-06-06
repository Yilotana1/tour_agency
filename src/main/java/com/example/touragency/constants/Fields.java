package com.example.touragency.constants;

public interface Fields {

    //Entity
    String ID = "id";

    //User
    String USER_FIRSTNAME = "firstname";
    String USER_LASTNAME = "lastname";
    String USER_PHONE = "phone";
    String USER_EMAIL = "email";
    String USER_LOGIN = "login";
    String USER_PASSWORD = "password";
    String USER_STATUS_ID = "status_id";
    String USER_ROLE_ID = "role_id";

    //Tour
    String TOUR_NAME = "name";
    String TOUR_COUNTRY = "country";
    String TOUR_PRICE = "price";
    String TOUR_MAX_PLACES = "max_places";
    String TOUR_MIN_PLACES = "min_places";
    String TOUR_TAKEN_PLACES = "taken_places";
    String TOUR_START_DATE = "start_date";
    String TOUR_END_DATE = "end_date";
    String TOUR_CATEGORY_ID = "category_id";
    String TOUR_STATUS_ID = "status_id";
    String TOUR_HOTEL_ID = "hotel_id";
    String TOUR_GUIDE_ID = "guide_id";
    String TOUR_DESCRIPTION = "description";
    String TOUR_CITY = "city";

    //Order
    String ORDER_DATE = "date";
    String ORDER_STATUS_ID = "status_id";
    String ORDER_CLIENT_ID = "client_id";
    String ORDER_PRICE = "price";

    //Hotel
    String HOTEL_NAME = "name";
    String HOTEL_CITY = "city";
    String HOTEL_ADDRESS = "address";
    String HOTEL_STARS = "stars";
    String HOTEL_ADMIN_PHONE = "admin_phone";



    //Discount
    String DISCOUNT_PERCENT = "percent";
    String DISCOUNT_MAX_PERCENT= "max_percent";


    //OrderClientMapper
    String BEAN_USER_ID = "user.id";
    String BEAN_USER_FIRSTNAME = "user.firstname";
    String BEAN_USER_LASTNAME = "user.lastname";
    String BEAN_USER_PHONE = "user.phone";
    String BEAN_USER_EMAIL = "user.email";
    String BEAN_USER_LOGIN = "user.login";
    String BEAN_USER_PASSWORD = "user.password";
    String BEAN_USER_STATUS_ID = "user.status_id";
    String BEAN_USER_ROLE_ID = "user.role_id";


    //TourHotelMapper
    String BEAN_HOTEL_ID = "hotel.id";
    String BEAN_HOTEL_NAME = "hotel.name";
    String BEAN_HOTEL_STARS = "hotel.stars";
    String BEAN_HOTEL_CITY = "hotel.city";
    String BEAN_HOTEL_ADDRESS = "hotel.address";
    String BEAN_HOTEL_ADMIN_PHONE = "hotel.admin_phone";

}
