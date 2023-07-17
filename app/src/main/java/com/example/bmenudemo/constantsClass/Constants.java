package com.example.bmenudemo.constantsClass;

public class Constants {
    private static final String ROOT_URL = "http://192.168.254.191/travel/";

    //url for registering new user
    public static final String URL_REGISTER = ROOT_URL + "register.php";

    //url for loging user in
    public static final String URL_LOGIN = ROOT_URL + "login_practice.php";

    //url for displaying data of the place in the activity
    public static final String URL_RETRIEVE = ROOT_URL + "retrieveplace.php";

    //url for displaying data of the place in the activity
    public static final String URL_RETRIEVE_FEATURED = ROOT_URL + "retrievefeaturedplace.php";

    //url for displaying data of the place in the activity
    public static final String URL_RETRIEVE_SUB_CAT_PLACE = ROOT_URL + "retrievePlaceOnClickingCategory.php";

    //url for displaying data of the place in the activity
    public static final String URL_RETRIEVE_SUB_STATE_PLACE = ROOT_URL + "retrievePlaceOnClickingState.php";

    //url for displaying data of the place in the activity
    public static final String URL_RETRIEVE_RECENT_PLACES = ROOT_URL + "showrecentplaces.php";

    //url for displaying data of the place in the activity
    public static final String URL_RETRIEVE_NEARBY_RECENT_PLACES = ROOT_URL + "retrievenearbyplace.php";

    //url for displaying data of the category in the activity
    public static final String URL_RETRIEVE_CATEGORY = ROOT_URL + "retrieve.php";

    //url for displaying data of the state in the activity
    public static final String URL_RETRIEVE_STATES = ROOT_URL + "retrieveState.php";

    //url to add notification
    public static final String URL_RETRIEVE_NOTIFICATION = ROOT_URL + "retrieveNotification.php";

    //url for updating users data in the activity
    public static final String URL_UPDATE_USERS = ROOT_URL + "updatedata.php";

    //url for glide image in placeadapter
    public static final String URL_GLIDE_IMAGE = ROOT_URL + "images/";

    //===================================================== Shared-Pref Key Values ===============================//
    public static String IS_LOGGED = "il";
    public static String TOKEN_KEY = "tk";
}
