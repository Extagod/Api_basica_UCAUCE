package com.api.supermercado.security;

import com.api.supermercado.entities.Person;
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
    private final JwtService jwtService;

    // --------------------- REGISTER ---------------------
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        if(personRepository.existsByUsername(request.username())) {
            return ResponseEntity.badRequest().body("El usuario ya existe.");
        }

        Person person = new Person();
        person.setUsername(request.username());
        person.setPassword(passwordEncoder.encode(request.password()));
        person.setRole(Role.USER); // ✅ Ahora correcto
        person.setIs_active(true);

        personRepository.save(person);

        return ResponseEntity.ok("Usuario registrado correctamente.");
    }

    // ------------------------ LOGIN ----------------------
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        var user = personRepository.findByUsername(request.username()).get();
        System.out.println(passwordEncoder.encode("12345"));
        System.out.println("🔑 Password en BD: " + user.getPassword());
        System.out.println("🔑 Coincide password? " + passwordEncoder.matches(request.password(), user.getPassword()));
        System.out.println("➡️ Raw password recibido: [" + request.password() + "]");
        System.out.println("➡️ Longitud recibida: " + request.password().length());
        System.out.println("➡️ Comparación directa: " + request.password().equals("12345"));

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


    // ------------------ DTOs internos --------------------
    public record RegisterRequest(String username, String password) {}
    public record LoginRequest(String username, String password) {}
    public record AuthResponse(String message, String token) {}
}
