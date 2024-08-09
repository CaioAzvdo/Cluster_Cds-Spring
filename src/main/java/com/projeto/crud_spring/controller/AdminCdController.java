package com.projeto.crud_spring.controller;

import com.projeto.crud_spring.domain.products.CD;
import com.projeto.crud_spring.repository.CdRepository;
import com.projeto.crud_spring.services.UserServiceImpl;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor

public class AdminCdController {

    private final CdRepository cdRepository;
    private UserServiceImpl userService;


    @GetMapping("/list")
    public ResponseEntity<List<CD>> list(){
        List<CD> cds = cdRepository.findAll();
        return ResponseEntity.ok(cds);
    }

    @DeleteMapping(path = {"/delete/{id}"})
    public ResponseEntity delete(@PathVariable Long id){
        cdRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }
    @DeleteMapping(path="/deleteByName/{name}")
    @Transactional
    public ResponseEntity deleteByName(@PathVariable String name){
        cdRepository.deleteByName(name);
        return ResponseEntity.status(204).build();
    }
    @DeleteMapping(path="/deleteAllByAuthor/{author}")
    @Transactional
    public ResponseEntity deleteAllByAuthor(@PathVariable String author){
        cdRepository.deleteAllByAuthorCustomQuery(author);
        return ResponseEntity.status(204).build();
    }
}
