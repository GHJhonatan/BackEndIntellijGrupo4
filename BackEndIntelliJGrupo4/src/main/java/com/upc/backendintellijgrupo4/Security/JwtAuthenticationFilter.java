package com.upc.backendintellijgrupo4.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        if (requestURI != null && requestURI.contains("/api/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");


        if (authHeader == null || authHeader.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }


        if (!authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(
                    "{\"error\":\"Se requiere un token JWT Bearer\"}"
            );
            return;
        }


        String jwt = authHeader.substring(7).trim();

        if (jwt.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(
                    "{\"error\":\"El token JWT está vacío\"}"
            );
            return;
        }

        String[] partes = jwt.split("\\.");

        if (partes.length != 3) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(
                    "{\"error\":\"El valor enviado no es un JWT válido\"}"
            );
            return;
        }

        try {

            if (!jwtService.isTokenValid(jwt)) {
                SecurityContextHolder.clearContext();
                response.setStatus(
                        HttpServletResponse.SC_UNAUTHORIZED
                );
                response.setContentType("application/json");
                response.getWriter().write(
                        "{\"error\":\"JWT inválido o expirado\"}"
                );
                return;
            }


            String correo = jwtService.extractUsername(jwt);
            String rol = jwtService.extractRole(jwt);

            if (correo == null ||
                    correo.isBlank() ||
                    rol == null ||
                    rol.isBlank()) {
                SecurityContextHolder.clearContext();
                response.setStatus(
                        HttpServletResponse.SC_UNAUTHORIZED
                );
                response.setContentType("application/json");
                response.getWriter().write(
                        "{\"error\":\"El JWT no contiene los datos necesarios\"}"
                );
                return;
            }


            rol = rol.toUpperCase();

            if (!rol.equals("ADMIN") &&
                    !rol.equals("USER")) {
                SecurityContextHolder.clearContext();
                response.setStatus(
                        HttpServletResponse.SC_FORBIDDEN
                );
                response.setContentType("application/json");
                response.getWriter().write(
                        "{\"error\":\"Rol no válido\"}"
                );
                return;
            }


            String autoridad = "ROLE_" + rol;

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            correo,
                            null,
                            List.of(
                                    new SimpleGrantedAuthority(
                                            autoridad
                                    )
                            )
                    );

            authentication.setDetails(
                    new WebAuthenticationDetailsSource()
                            .buildDetails(request)
            );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

            filterChain.doFilter(request, response);

        } catch (Exception e) {

            SecurityContextHolder.clearContext();

            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );

            response.setContentType("application/json");

            response.getWriter().write(
                    "{\"error\":\"El valor enviado no es un JWT válido\"}"
            );
        }
    }
}