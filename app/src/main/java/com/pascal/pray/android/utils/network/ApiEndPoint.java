package com.pascal.pray.android.utils.network;


public interface ApiEndPoint {
    // BASE_URL_OneDNA
    String STEP_UPLOAD_URL_V3               =  "/v3/CustomerDailyData";
    String STEP_GET_URL_V3                  =  "/v3/CustomerDailyData";
    String POST_TEST_PHONE_BOOK_V2          =  "/v2/PostTestPhoneBook";
    String POST_TEST_PHONE_BOOK_V3          =  "/v3/PostTestPhoneBook";
    String WEIGHT_HISTORY_URL_V3            =  "/v3/CustomerDailyData";
    String SESSION_URL_V1                   =  "/v1/Session";
    String PROFILE_URL_V1                   =  "/v1/Profile";
    String FOOD_LOG_URL_V1                  =  "/v1/FoodLog";
    String REMOTE_CONTROL_COMMANDS_V1       =  "/v1/RemoteControlCommands";

    // BASE_URL_IDENTITY
    String LOGIN_URL_V2                     =  "/v2/Login";
    String LOGIN_URL_V4                     =  "/v4/Login";
    String LOGIN_MTL_URL_V1                 =  "/mtl/v1/Login";
    String ACCOUNT_URL_V2                   =  "/v2/Account";
    String CUSTOMER_URL_V1                  =  "/v1/Customer";
    String LAB_URL_V2                       =  "/v2/Lab";
    String VALIDATE_DATA_V4                 =  "/v4/ValidateData";
}
