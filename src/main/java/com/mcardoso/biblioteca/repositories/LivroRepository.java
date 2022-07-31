package com.mcardoso.biblioteca.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mcardoso.biblioteca.domain.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer>{

    @Query("SELECT obj FROM Livro obj WHERE obj.categoria.id = :pIdCategoria ORDER BY titulo")
    List<Livro> findAllByCategoria(@Param(value = "pIdCategoria") Integer pIdCategoria);
    
}
