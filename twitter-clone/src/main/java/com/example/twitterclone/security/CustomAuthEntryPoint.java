package com.example.twitterclone.security;

import com.example.twitterclone.util.JsonResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private JsonResponse jsonResponse;


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        jsonResponse.writeResponse(response, "Access Denied", "Require Authentication", 403);
    }
}
