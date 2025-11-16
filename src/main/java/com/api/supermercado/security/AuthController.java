package com.api.supermercado.security;

import com.api.supermercado.dtos.CustomerRegisterDto;
import com.api.supermercado.dtos.EmployeeRegisterDto;
import com.api.supermercado.enums.RoleEnum;
import com.api.supermercado.exceptions.PersonException;
import com.api.supermercado.exceptions.PersonExceptions;
import com.api.supermercado.repositories.PersonRepository;
import com.api.supermercado.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final AuthService authService;

    // --------------------- REGISTER ---------------------
    @PostMapping("/registerCustomer")
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerRegisterDto request) {

        if (request == null) {
            return ResponseEntity.badRequest()
                    .body(new PersonException(PersonExceptions.INVALID_PERSON_DATA));
        }

        authService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/registerEmployee")
    public ResponseEntity<?> registerEmployee(@RequestBody EmployeeRegisterDto requestEmployee) {

        if (requestEmployee == null) {
            return ResponseEntity.badRequest()
                    .body(new PersonException(PersonExceptions.INVALID_PERSON_DATA));
        }

        authService.registerEmployee(requestEmployee);
        return ResponseEntity.ok("Employee registered successfully");
    }

    // ------------------------ LOGIN ----------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        System.out.println("\n============================");
        System.out.println("🔐 LOGIN INIT");
        System.out.println("============================");
        System.out.println("➡️ Request recibido: " + request);

        // 1️⃣ Autenticar primero
        System.out.println("🔑 Autenticando credenciales...");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        System.out.println("🔑 Password válido");

        // 2️⃣ Obtener usuario desde BD
        var user = personRepository.findByUsername(request.username())
                .orElseThrow(() -> new PersonException(PersonExceptions.PERSON_NOT_FOUND));

        System.out.println("👤 Usuario encontrado en BD: " + user.getUsername());

        // 3️⃣ Convertir roles (BD → Enum)
        List<RoleEnum> roleEnums = user.getRoles().stream()
                .map(r -> RoleEnum.fromId(r.getRoleId()))
                .toList();

        System.out.println("🎭 Roles convertidos: " + roleEnums);

        // 4️⃣ Generar token
        String token = jwtService.generateToken(request.username(), roleEnums);
        System.out.println("🪪 TOKEN GENERADO: " + token);

        // 5️⃣ Respuesta dinamica por rol
        if (roleEnums.contains(RoleEnum.ADMIN)) {
            return ResponseEntity.ok(new AuthResponse("successful login, Welcome Admin User", token));
        }

        if (roleEnums.contains(RoleEnum.USER)) {
            return ResponseEntity.ok(new AuthResponse("successful login, Welcome User", token));
        }

        throw new PersonException(PersonExceptions.THE_USER_IS_NOT_AN_ADMIN);
    }

    // Records
    public record LoginRequest(String username, String password) {}
    public record AuthResponse(String message, String token) {}

}
