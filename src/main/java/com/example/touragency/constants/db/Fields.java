package com.example.touragency.constants.db;


public interface Fields {

    //Entity
    String ID = "id";

    //User
    String USER_ID = Tables.USER + "." + Fields.ID;
    String USER_FIRSTNAME = Tables.USER + ".firstname";
    String USER_LASTNAME = Tables.USER + ".lastname";
    String USER_PHONE = Tables.USER + ".phone";
    String USER_EMAIL = Tables.USER + ".email";
    String USER_LOGIN = Tables.USER + ".login";
    String USER_PASSWORD = Tables.USER + ".password";
    String USER_STATUS_ID = Tables.USER + ".status_id";
    String USER_ROLE_ID = Tables.USER + ".role_id";

    //Tour
    String TOUR_ID = Tables.TOUR + "." + Fields.ID;
    String TOUR_NAME = Tables.TOUR + ".name";
    String TOUR_COUNTRY = Tables.TOUR + ".country";
    String TOUR_PRICE = Tables.TOUR + ".price";
    String TOUR_MAX_TICKETS = Tables.TOUR + ".max_tickets";
    String TOUR_MIN_TICKETS = Tables.TOUR + ".min_tickets";
    String TOUR_TAKEN_TICKETS = Tables.TOUR + ".taken_tickets";
    String TOUR_START_DATE = Tables.TOUR + ".start_date";
    String TOUR_END_DATE = Tables.TOUR + ".end_date";
    String TOUR_CATEGORY_ID = Tables.TOUR + ".category_id";
    String TOUR_STATUS_ID = Tables.TOUR + ".status_id";
    String TOUR_HOTEL_ID = Tables.TOUR + ".hotel_id";
    String TOUR_CITY = Tables.TOUR + ".city";

    //Order
    String ORDER_ID = Tables.ORDER + "." + Fields.ID;
    String ORDER_DATE = Tables.ORDER + ".date";
    String ORDER_STATUS_ID = Tables.ORDER + ".status_id";
    String ORDER_CLIENT_ID = Tables.ORDER + ".client_id";
    String ORDER_PRICE = Tables.ORDER + ".price";
    String ORDER_TOUR_ID = Tables.ORDER + ".id";

    //Hotel
    String HOTEL_ID = Tables.HOTEL + "." + Fields.ID;
    String HOTEL_NAME = Tables.HOTEL + ".name";
    String HOTEL_CITY = Tables.HOTEL + ".city";
    String HOTEL_ADDRESS = Tables.HOTEL + ".address";
    String HOTEL_STARS = Tables.HOTEL + ".stars";
    String HOTEL_ADMIN_PHONE = Tables.HOTEL + ".admin_phone";



    //Discount
    String DISCOUNT_ID = Tables.DISCOUNT + "." + Fields.ID;
    String DISCOUNT_PERCENT = Tables.DISCOUNT + ".percent";
    String DISCOUNT_MAX_PERCENT= Tables.DISCOUNT + ".max_percent";

    //Tour_has_order
//    String ORDER_HAS_TOUR_TOUR_ID = Tables.ORDER_HAS_TOUR + ".tour_id";
//    String ORDER_HAS_TOUR_ORDER_ID = Tables.ORDER_HAS_TOUR + ".order_id";

}
