package com.zunino.sport.web.handler;

import com.zunino.sport.persistence.exception.ForbiddenException;
import com.zunino.sport.persistence.exception.UnauthorizedException;
import com.zunino.sport.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtService jwtService;

    public JwtInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("No se proporcionó token de autenticación");
        }

        String token = authHeader.substring(7);

        Long jwtUserId;
        try {
            jwtUserId = jwtService.extractUserId(token);
        } catch (Exception e) {
            throw new UnauthorizedException("Token inválido o expirado");
        }

        // validar si hay path variable userId
        Map<String, String> pathVariables =
                (Map<String, String>) request.getAttribute(
                        HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE
                );

        if (pathVariables != null && pathVariables.containsKey("userId")) {

            Long pathUserId = Long.parseLong(pathVariables.get("userId"));

            if (!jwtUserId.equals(pathUserId)) {
                throw new ForbiddenException("No tienes permiso para acceder a este recurso");
            }
        }

        return true;
    }


    }

