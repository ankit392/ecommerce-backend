package com.mybasket.app.security;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        String message=authException.getMessage();

        var message1 = Map.of(
                "message", message,
                "success", false
        );

        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("success", false);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(message1);
        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().write(s);



    }
}
