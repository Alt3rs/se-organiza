package com.alt3rs.seorganiza.controller.auth;

import com.alt3rs.seorganiza.domain.user.User;
import com.alt3rs.seorganiza.dto.LoginRequestDTO;
import com.alt3rs.seorganiza.dto.LoginResponseDTO;
import com.alt3rs.seorganiza.dto.RegisterRequestDTO;
import com.alt3rs.seorganiza.dto.RegisterResponseDTO;
import com.alt3rs.seorganiza.exceptions.InvalidCredentialsException;
import com.alt3rs.seorganiza.exceptions.UserNotFoundException;
import com.alt3rs.seorganiza.infra.security.TokenService;
import com.alt3rs.seorganiza.repository.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new UserNotFoundException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(token, user.getId()));
        }
        throw new InvalidCredentialsException("Invalid password");
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid RegisterRequestDTO body){
        Optional<User> user = this.repository.findByEmail(body.email());

        if(user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            this.repository.save(newUser);
            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new RegisterResponseDTO(token, newUser.getId()));

        }

        return ResponseEntity.badRequest().build();
    }
}
