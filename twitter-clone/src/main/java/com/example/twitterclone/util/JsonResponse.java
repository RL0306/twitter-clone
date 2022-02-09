package com.example.twitterclone.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JsonResponse {
    private final ObjectMapper mapper;

    public void writeResponse(HttpServletResponse response, String key, String value, int responseType) throws IOException {
        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put(key, value);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(responseType);
        response.getWriter().write(mapper.writeValueAsString(responseNode));
    }
}
