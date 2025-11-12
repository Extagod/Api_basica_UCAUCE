package com.api.supermercado.security;

import com.api.supermercado.dtos.PersonRequestRegistertDto;
import com.api.supermercado.entities.Person;
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

    // --------------------- REGISTER ---------------------
    // ------------------------ LOGIN ----------------------

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody PersonRequestRegistertDto request) {

        if (personRepository.existsByUsername(request.username())) {
            return ResponseEntity.badRequest().body(PersonExceptions.DUPLICATE_PERSON_USERNAME);
        }

        if (personRepository.findPersonByIdentificationNumber(request.identificationNumber()).isPresent()) {
            return ResponseEntity.badRequest().body(PersonExceptions.DUPLICATE_IDENTIFICATION);
        }

        if (personRepository.findPersonByEmail(request.email()).isPresent()) {
            return ResponseEntity.badRequest().body(PersonExceptions.DUPLICATE_PERSON_EMAIL);
        }

        Person person = personRequestMapper.toEntity(request);
        person.setIs_active(true);
        person.setPassword(passwordEncoder.encode(request.password()));
        person.setRole(Role.USER); // esto era papu
        personRepository.save(person);
        return ResponseEntity.ok("Successfully registered user\n");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        var user = personRepository.findByUsername(request.username()).get();
        System.out.println(passwordEncoder.encode("12345"));
        System.out.println("🔑 Password en BD: " + user.getPassword());
        System.out.println("🔑 Coincide password? " + passwordEncoder.matches(request.password(), user.getPassword()));


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        Role role = user.getRole();
        String token = jwtService.generateToken(request.username(), role.name());

        return ResponseEntity.ok(new AuthResponse("Login exitoso", token));
    }


    public record LoginRequest(String username, String password) {
    }
    public record AuthResponse(String message, String token) {
    }
}
