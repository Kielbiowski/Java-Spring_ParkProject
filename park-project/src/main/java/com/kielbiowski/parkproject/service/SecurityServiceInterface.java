package com.kielbiowski.parkproject.service;

public interface SecurityServiceInterface {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
