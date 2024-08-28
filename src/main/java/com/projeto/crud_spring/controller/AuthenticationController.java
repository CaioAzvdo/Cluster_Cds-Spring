package com.projeto.crud_spring.controller;
import com.projeto.crud_spring.domain.user.AuthenticationDTO;
import com.projeto.crud_spring.domain.user.LoginResponseDTO;
import com.projeto.crud_spring.domain.user.RegisterDTO;
import com.projeto.crud_spring.domain.user.User;
import com.projeto.crud_spring.infra.security.TokenService;
import com.projeto.crud_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data) {
        var UsernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(UsernamePassword);

        var user = (User) auth.getPrincipal();
        var token = tokenService.generateToken(user);
        var role = user.getRole();
        var login = user.getLogin();
        //var token = tokenService.generateToken(User.class.cast(auth.getPrincipal()));

        return ResponseEntity.ok(new LoginResponseDTO(token, role, login));

    }
//    @GetMapping("/list")
//    public ResponseEntity<Page<DetailsUser>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable paginacao){
//        var page = userRepository.findAll(paginacao).map(DetailsUser::new);
//        return ResponseEntity.ok(page);
//    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data) {
        if(this.userRepository.findByLogin(data.login()) != null){return ResponseEntity.badRequest().body("User already exists");}

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newuser = new User(data.login(), encryptedPassword, data.email(), data.role());

        this.userRepository.save(newuser);
        return ResponseEntity.ok("User created");

    }

}
