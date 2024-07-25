package com.projeto.crud_spring.repository;

import com.projeto.crud_spring.domain.products.CD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CdRepository extends JpaRepository<CD, Long> {
    void deleteByName(String name);
    void deleteAllByAuthor(String author);

}
