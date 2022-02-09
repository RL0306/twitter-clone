package com.example.twitterclone.security;

import com.example.twitterclone.util.JsonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthFailHandler implements AuthenticationFailureHandler {

    private final JsonResponse jsonResponse;

    public CustomAuthFailHandler(JsonResponse jsonResponse){
        this.jsonResponse = jsonResponse;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        jsonResponse.writeResponse(response,"authentication", "Credentials failed", 401);
    }
}
