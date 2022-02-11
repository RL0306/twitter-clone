package com.example.twitterclone.filter;

import com.example.twitterclone.controller.UserController;
import com.example.twitterclone.dto.LoginRequestDTO;
import com.example.twitterclone.service.EmailSendingService;
import com.example.twitterclone.service.IpService;
import com.example.twitterclone.util.JsonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper mapper;
    private final IpService ipService;
    private final JsonResponse jsonResponse;

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginRequestDTO loginRequestDTO = mapper.readValue(request.getInputStream(), LoginRequestDTO.class);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());

        return authenticationManager.authenticate(authenticationToken);
    }

    @SneakyThrows
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        boolean check = ipService.createIP(request.getRemoteAddr(), authResult.getName());
        if(!check){
            jsonResponse.writeResponse(response, "authentication", "Confirm new IP Address", 401);
            return;
        }
        super.successfulAuthentication(request, response, chain, authResult);
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
//        userController.auth();
    }
}
