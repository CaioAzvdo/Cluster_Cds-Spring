package com.projeto.crud_spring.controller;

import com.projeto.crud_spring.repository.CdRepository;
import com.projeto.crud_spring.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor

public class AdminCdController {

    private final CdRepository cdRepository;
    private UserServiceImpl userService;


    @DeleteMapping(path = {"/delete/{id}"})
    public ResponseEntity delete(@PathVariable Long id){
        cdRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}
