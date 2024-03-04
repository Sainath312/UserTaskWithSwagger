package com.example.UserTask.constants;

public class ApiConstants {

    //Admin
    public static final String ADMIN = "/api/admin";
    public static final String REGISTER ="/register";
    public static final String ADMIN_LOGIN ="/adminLogin";
    public static final String LOGOUT ="/adminLogout";


    //User
    public static final String USER= "/api/user";
    public static final String SAVE_USER = "/saveNewUser";
    public static final String GET_ALL_USER ="/allUsers";
    public static final String SEARCH_BY_USERID="/search/{id}";
    public static final String SEARCH_BY_USERNAME="/searchByName";
    public static final String UPDATE_USER="/updateUser/UserName";
    public static final String DELETE_USER="/deleteUser/{id}";
    public static final String SEARCH_KEYWORD="/searchKeyword";



}
