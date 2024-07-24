package com.projeto.crud_spring.domain.products;

public record DetailsCd(Long id, String name, String genre, String author, String register_date) {
    public DetailsCd(CD cd) {
        this(cd.getId(), cd.getName(), cd.getGenre(), cd.getAuthor(), cd.getRegister_date().toString());
    }
}
