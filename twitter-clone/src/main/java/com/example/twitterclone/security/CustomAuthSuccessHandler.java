package com.example.twitterclone.security;

import com.example.twitterclone.util.JsonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JsonResponse jsonResponse;

    public CustomAuthSuccessHandler(JsonResponse jsonResponse ){
        this.jsonResponse = jsonResponse;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        jsonResponse.writeResponse(response, "authentication", "Success", 200);
    }


}
