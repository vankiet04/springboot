package net.enjoy.springboot.registrationlogin.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication)
            throws IOException, jakarta.servlet.ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/index");
            System.out.println("ROLE_ADMIN nè");
        } else if (roles.contains("ROLE_MEMBER")) {
            response.sendRedirect("/home");
            System.out.println("ROLE_MEMBER nè");
        } else {    
            response.sendRedirect("/default");
        }
    }
    // Removed redundant methods

}