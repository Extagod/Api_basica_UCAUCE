package com.api.supermercado.security;

import com.api.supermercado.dtos.PersonRequestRegistertDto;
import com.api.supermercado.exceptions.PersonException;
import com.api.supermercado.exceptions.PersonExceptions;
import com.api.supermercado.mappers.PersonRequestMapper;
import com.api.supermercado.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final PersonRequestMapper personRequestMapper;
    private final JwtService jwtService;
    private final SecurityService securityService;

    // --------------------- REGISTER ---------------------
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody PersonRequestRegistertDto request) {

        if (request == null) {
            return ResponseEntity.badRequest()
                    .body(new PersonException(PersonExceptions.INVALID_PERSON_DATA));
        }

        securityService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
    }

    // ------------------------ LOGIN ----------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        var user = personRepository.findByUsername(request.username())
                .orElseThrow(() -> new PersonException(PersonExceptions.PERSON_NOT_FOUND));

        System.out.println("🔑 Password en BD: " + user.getPassword());
        System.out.println("🔑 Password enviado: " + request.password());
        System.out.println("🔑 Coincide password? " + passwordEncoder.matches(request.password(), user.getPassword()));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        Role role = user.getRoleId();
        String token = jwtService.generateToken(request.username(), role.name());

        if (role == Role.ADMIN) {
            return ResponseEntity.ok(new AuthResponse("successful login, Welcome Admin User", token));
        }

        if (role == Role.USER) {
            return ResponseEntity.ok(new AuthResponse("successful login, Welcome User", token));
        }

        throw new PersonException(PersonExceptions.THE_USER_IS_NOT_AN_ADMIN);
    }

    // Records
    public record LoginRequest(String username, String password) {}
    public record AuthResponse(String message, String token) {}

}
