package com.mcardoso.biblioteca.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.mcardoso.biblioteca.domain.Categoria;

public class CategoriaDTO implements Serializable {
    private Integer id;
    @NotEmpty(message = "Campo NOME requerido!")
    @Length(min = 3, max = 100, message = "Campo NOME deve ter entre 3 e 100 caracteres!")
    private String nome;
    @NotEmpty(message = "Campo DESCRIÇÂO requerido!")
    @Length(min = 3, max = 200, message = "Campo NOME deve ter entre 3 e 200 caracteres!")
    private String descricao;

    public CategoriaDTO() {
        super();
    }
    public CategoriaDTO(Categoria pCategoria) {
        super();
        this.id = pCategoria.getId();
        this.nome = pCategoria.getNome();
        this.descricao = pCategoria.getDescricao();
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
}
