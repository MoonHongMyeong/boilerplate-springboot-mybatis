package me.moon.boilerplate.service;


public interface LoginService {
    void loginUser(String email);
    void logoutUser();
    String getCurrentUser();

}
