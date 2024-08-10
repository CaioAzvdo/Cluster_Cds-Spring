package com.projeto.crud_spring.domain.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projeto.crud_spring.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Entity
@Table(name = "cds")

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class CD {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "genre", length = 100, nullable = false)
    private String genre;

    @Column(name = "author", length = 100, nullable = false)
    private String author;

    @Column(name = "register_date", length = 100, nullable = false)
    private LocalDateTime register_date;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
