package com.projeto.crud_spring.controller;
import com.projeto.crud_spring.domain.products.CD;
import com.projeto.crud_spring.domain.products.DetailsCd;
import com.projeto.crud_spring.repository.CdRepository;
import com.projeto.crud_spring.services.AuthorizationService;
import com.projeto.crud_spring.services.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cd")
@AllArgsConstructor

public class CdController {

    private final CdRepository cdRepository;
    private UserServiceImpl userService;

    @GetMapping("/list")
    public ResponseEntity<Page<DetailsCd>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable paginacao){
        var page = cdRepository.findAll(paginacao).map(DetailsCd::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody CD CD){
        var agora = LocalDateTime.now();
        CD.setRegister_date(agora);
        CD.setUser(userService.getAuthenticatedUser());
        cdRepository.save(CD);   // userService.getAuthenticatedUser() é um método hipotético que retorna o usuário autenticado
        return ResponseEntity.status(201).body(new DetailsCd(CD) );
}
    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Long id){
        var course = cdRepository.findById(id).orElse(null);
        return ResponseEntity.status(200).body(course);
    }
    @PutMapping(value = "/edit/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody CD cd){
        var username = userService.getAuthenticatedUser().getLogin();
        return cdRepository.findById(id)
                .map(record -> {
                    if (!record.getUser().getLogin().equals(username)) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                    }
                    record.setName(cd.getName());
                    record.setGenre(cd.getGenre());
                    record.setAuthor(cd.getAuthor());
                    CD updated = cdRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping(path = {"/delete/{id}"})
    public ResponseEntity<?> delete(@PathVariable Long id){
        var username = userService.getAuthenticatedUser().getLogin();

        return cdRepository.findById(id)
                .map(record -> {
                    if (!record.getUser().getLogin().equals(username)) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                    }
                    cdRepository.deleteById(id);
                    return ResponseEntity.status(204).build();
                }).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/details/{id}")
    public ResponseEntity Detalhar(@PathVariable Long id){
        var cd = cdRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetailsCd(cd));
    }

    //Metodos antigos sem verificação:

//    @PutMapping(value = "/edit/{id}")
//    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody CD cd){
//        return cdRepository.findById(id)
//                .map(record -> {
//                    record.setName(cd.getName());
//                    record.setGenre(cd.getGenre());
//                    record.setAuthor(cd.getAuthor());
//                    CD updated = cdRepository.save(record);
//                    return ResponseEntity.ok().body(updated);
//                }).orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping(path = {"/delete/{id}"})
//    public ResponseEntity delete(@PathVariable Long id){
//        cdRepository.deleteById(id);
//        return ResponseEntity.status(204).build();
//    }
    //    @PostMapping
//    public ResponseEntity create(@RequestBody CD CD){
//        var agora = LocalDateTime.now();
//        CD.setRegister_date(agora);
//        return ResponseEntity.status(201).body(cdRepository.save(CD));
////        courseRepository.save(course);
////        return ResponseEntity.status(201).body().build();
//    }

}



