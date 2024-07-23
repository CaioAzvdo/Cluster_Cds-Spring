package com.projeto.crud_spring.controller;

import com.projeto.crud_spring.domain.Course;
import com.projeto.crud_spring.domain.user.AuthenticationDTO;
import com.projeto.crud_spring.domain.user.RegisterDTO;
import com.projeto.crud_spring.domain.user.User;
import com.projeto.crud_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data) {
        var UsernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(UsernamePassword);
        return ResponseEntity.ok(auth);
    }
    @GetMapping("/list")
    public List<User> list(){
        return userRepository.findAll();
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data) {
        if(this.userRepository.findByLogin(data.login()) != null){return ResponseEntity.badRequest().body("User already exists");}

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newuser = new User(data.login(), encryptedPassword, data.role());

        this.userRepository.save(newuser);
        return ResponseEntity.ok("User created");

    }
}
