package com.projeto.crud_spring.controller;
import com.projeto.crud_spring.domain.Course;
import com.projeto.crud_spring.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/courses")
@AllArgsConstructor

public class CoursesController {

    private final CourseRepository courseRepository;

    @GetMapping
    public List<Course> list(){
        return courseRepository.findAll();
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Course course){
        return ResponseEntity.status(201).body(courseRepository.save(course));
//        courseRepository.save(course);
//        return ResponseEntity.status(201).body().build();
    }
    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Long id){
        var course = courseRepository.findById(id).orElse(null);
        return ResponseEntity.status(200).body(course);
    }
//    @PutMapping(value = "/{id}")
//    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Course course){
//        return ResponseEntity.status(200).body(courseRepository.save(course));
//    }
    @DeleteMapping(path = {"/delete/{id}"})
    public ResponseEntity delete(@PathVariable Long id){
        courseRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

}













    //    @Bean
//    CommandLineRunner initDatabase(){
//        return args -> {
//            courseRepository.deleteAll();
//            Course C = new Course();
//            C.setName("Java");
//            C.setCategoria("Back-end");
//
//            courseRepository.save(C);
//        };
//    }


