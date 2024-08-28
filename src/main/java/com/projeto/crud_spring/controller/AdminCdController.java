package com.projeto.crud_spring.controller;

import com.projeto.crud_spring.domain.products.CD;
import com.projeto.crud_spring.domain.products.DetailsCd;
import com.projeto.crud_spring.repository.CdRepository;
import com.projeto.crud_spring.services.UserServiceImpl;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    @PostMapping("/register")
    public ResponseEntity create(@RequestBody CD CD){
        CdController cdController = new CdController(cdRepository, userService);
        return cdController.create(CD);
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
    @PutMapping(value = "/edit/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody CD cd){
        return cdRepository.findById(id)
                .map(record -> {
                    record.setName(cd.getName());
                    record.setGenre(cd.getGenre());
                    record.setAuthor(cd.getAuthor());
                    CD updated = cdRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
}
