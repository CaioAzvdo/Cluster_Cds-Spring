package com.projeto.crud_spring.domain;

import jakarta.persistence.*;
import lombok.Data;

//@Getter
//@Setter
@Data
@Entity
@Table(name = "cursos")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "categoria", length = 100, nullable = false)
    private String categoria;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
