package com.mcardoso.biblioteca.dtos;

import java.io.Serializable;

import com.mcardoso.biblioteca.domain.Livro;

public class LivroDTO implements Serializable {
    private Integer id;
    private String titulo;

    public LivroDTO() {
    }
    public LivroDTO(Livro objLivro) {
        this.id = objLivro.getId();
        this.titulo = objLivro.getTitulo();
    }
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
