package com.example.bmenudemo.constantsClass;

public class Constants {
    private static final String ROOT_URL = "http://192.168.1.6/travel/";

    //url for registering new user
    public static final String URL_REGISTER = ROOT_URL + "register.php";

    //url for loging user in
    public static final String URL_LOGIN = ROOT_URL + "login_try.php";

    //url for adding data of the place
    public static final String URL_INSERT_PLACES = ROOT_URL + "uploadimage.php";

    //url for displaying data of the place in the activity
    public static final String URL_RETRIEVE = ROOT_URL + "retrieveplace.php";

    //url for displaying data of the category in the activity
    public static final String URL_RETRIEVE_CATEGORY = ROOT_URL + "retrieve.php";

    //url for displaying users data in the activity
    public static final String URL_USERS = ROOT_URL + "users.php";

    //url for glide image in placeadapter
    public static final String URL_GLIDE_IMAGE = ROOT_URL + "images/";

    //===================================================== Shared-Pref Key Values ===============================//
    public static String IS_LOGGED = "il";
    public static String TOKEN_KEY = "tk";
}
