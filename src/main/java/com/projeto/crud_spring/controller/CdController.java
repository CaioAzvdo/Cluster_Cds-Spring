package com.projeto.crud_spring.controller;
import com.projeto.crud_spring.domain.products.CD;
import com.projeto.crud_spring.domain.products.DetailsCd;
import com.projeto.crud_spring.repository.CdRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cd")
@AllArgsConstructor

public class CdController {

    private final CdRepository cdRepository;

    @GetMapping("/list")
    public List<CD> list(){
        return cdRepository.findAll();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CD CD){
        var agora = LocalDateTime.now();
        CD.setRegister_date(agora);
        return ResponseEntity.status(201).body(cdRepository.save(CD));
//        courseRepository.save(course);
//        return ResponseEntity.status(201).body().build();
    }
    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Long id){
        var course = cdRepository.findById(id).orElse(null);
        return ResponseEntity.status(200).body(course);
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

    @DeleteMapping(path = {"/delete/{id}"})
    public ResponseEntity delete(@PathVariable Long id){
        cdRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }
    @GetMapping("/details/{id}")
    public ResponseEntity Detalhar(@PathVariable Long id){
        var cd = cdRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetailsCd(cd));
    }

}



