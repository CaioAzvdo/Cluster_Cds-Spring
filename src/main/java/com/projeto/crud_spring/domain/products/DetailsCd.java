package com.projeto.crud_spring.domain.products;

import com.projeto.crud_spring.domain.user.User;

public record DetailsCd(Long id,String userLogin ,String name, String genre, String author, String register_date) {
    public DetailsCd(CD cd) {
        this(cd.getId(),cd.getUser().getUsername(),cd.getName(), cd.getGenre(), cd.getAuthor(), cd.getRegister_date().toString());
    }

    public Long getId() {
        return id;
    }
}
