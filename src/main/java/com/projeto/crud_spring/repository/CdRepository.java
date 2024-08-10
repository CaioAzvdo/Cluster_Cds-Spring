package com.projeto.crud_spring.repository;

import com.projeto.crud_spring.domain.products.CD;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CdRepository extends JpaRepository<CD, Long> {
    void deleteByName(String name);

    Page<CD> findAllByAuthor(String author, Pageable pageable);

//    void deleteAllByAuthor(String author);

    @Modifying
    @Transactional
    @Query("delete from CD c where c.author = ?1")
    void deleteAllByAuthorCustomQuery(String author);

}
