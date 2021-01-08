package com.kielbiowski.parkproject.service.security;

public interface SecurityServiceInterface {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
