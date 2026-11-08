package com.upc.backendintellijgrupo4.Controllers;

import com.upc.backendintellijgrupo4.DTOs.LoginDTO;
import com.upc.backendintellijgrupo4.DTOs.LoginResponseDTO;
import com.upc.backendintellijgrupo4.Security.JwtService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtService jwtService) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        System.out.println("=== INTENTO DE LOGIN RECIBIDO ===");
        System.out.println("Correo recibido: " + loginDTO.getCorreo());

        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginDTO.getCorreo(),
                                    loginDTO.getPassword()
                            )
                    );

            System.out.println("Autenticación exitosa en el AuthenticationManager para: " + loginDTO.getCorreo());

            UserDetails userDetails =
                    (UserDetails) authentication.getPrincipal();

            String token = jwtService.generateToken(userDetails);

            String rol = userDetails.getAuthorities()
                    .iterator()
                    .next()
                    .getAuthority()
                    .replace("ROLE_", "");

            LoginResponseDTO response =
                    new LoginResponseDTO(
                            token,
                            userDetails.getUsername(),
                            rol
                    );

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            System.err.println(">>> ERROR: Credenciales inválidas para el correo: " + loginDTO.getCorreo());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales inválidas: verifique su correo y contraseña.");
        } catch (Exception e) {
            System.err.println(">>> ERROR CRÍTICO NO ESPERADO EN LOGIN:");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en el servidor durante la autenticación: " + e.getMessage());
        }
    }
}