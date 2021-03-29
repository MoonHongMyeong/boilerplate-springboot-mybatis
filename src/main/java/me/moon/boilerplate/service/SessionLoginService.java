package me.moon.boilerplate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService{
    private static final String USER_EMAIL = "USER_EMAIL";
    private final HttpSession session;

    @Override
    public void loginUser(String email) {
        session.setAttribute(USER_EMAIL, email);
    }

    @Override
    public void logoutUser() {
        session.removeAttribute(USER_EMAIL);
    }

    @Override
    public String getCurrentUser() {
        return (String) session.getAttribute(USER_EMAIL);
    }


}
