package com.upc.backendintellijgrupo4.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            CustomUserDetailsService userDetailsService,
            JwtAuthenticationFilter jwtAuthenticationFilter) {

        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration)
            throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                // API REST
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // =========================================
                        // SWAGGER
                        // =========================================
                        // Swagger puede abrirse sin JWT.
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()


                        // =========================================
                        // LOGIN
                        // =========================================
                        // El login es libre porque necesitamos
                        // enviar las credenciales para obtener
                        // el JWT.
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/auth/login"
                        ).permitAll()


                        // =========================================
                        // ADMIN - ROLES
                        // =========================================
                        .requestMatchers(
                                "/api/roles/**"
                        ).hasRole("ADMIN")


                        // =========================================
                        // ADMIN - USUARIOS
                        // =========================================
                        .requestMatchers(
                                "/api/usuarios/**"
                        ).hasRole("ADMIN")


                        // =========================================
                        // CONSULTAR CONTENIDO
                        // ADMIN + USER
                        // =========================================
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/idiomas/**",
                                "/api/niveles/**",
                                "/api/lecciones/**",
                                "/api/preguntas-evaluacion/**",
                                "/api/opcion-preguntas/**"
                        ).hasAnyRole("ADMIN", "USER")


                        // =========================================
                        // CREAR CONTENIDO
                        // SOLO ADMIN
                        // =========================================
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/idiomas/**",
                                "/api/niveles/**",
                                "/api/lecciones/**",
                                "/api/preguntas-evaluacion/**",
                                "/api/opcion-preguntas/**"
                        ).hasRole("ADMIN")


                        // =========================================
                        // MODIFICAR CONTENIDO
                        // SOLO ADMIN
                        // =========================================
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/idiomas/**",
                                "/api/niveles/**",
                                "/api/lecciones/**",
                                "/api/preguntas-evaluacion/**",
                                "/api/opcion-preguntas/**"
                        ).hasRole("ADMIN")


                        // =========================================
                        // ELIMINAR CONTENIDO
                        // SOLO ADMIN
                        // =========================================
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/idiomas/**",
                                "/api/niveles/**",
                                "/api/lecciones/**",
                                "/api/preguntas-evaluacion/**",
                                "/api/opcion-preguntas/**"
                        ).hasRole("ADMIN")


                        // =========================================
                        // PROGRESO DE LECCIONES
                        // ADMIN + USER
                        // =========================================
                        .requestMatchers(
                                "/api/progreso-lecciones/**"
                        ).hasAnyRole("ADMIN", "USER")


                        // =========================================
                        // RESULTADOS DE EVALUACIÓN
                        // ADMIN + USER
                        // =========================================
                        .requestMatchers(
                                "/api/resultados-evaluacion/**"
                        ).hasAnyRole("ADMIN", "USER")


                        // =========================================
                        // TODO LO DEMÁS
                        // =========================================
                        // Cualquier endpoint que no esté
                        // expresamente permitido necesita JWT.
                        .anyRequest().authenticated()
                )

                // No se utilizan sesiones HTTP.
                // Cada petición protegida debe enviar su JWT.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                // Proveedor de autenticación
                .authenticationProvider(authenticationProvider())

                // El filtro JWT revisa el token antes
                // del filtro estándar de autenticación.
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}