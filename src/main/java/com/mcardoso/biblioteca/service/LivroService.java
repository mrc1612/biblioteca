package com.mcardoso.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcardoso.biblioteca.domain.Categoria;
import com.mcardoso.biblioteca.domain.Livro;
import com.mcardoso.biblioteca.repositories.LivroRepository;
import com.mcardoso.biblioteca.service.exceptions.ObjectNotFoundException;

@Service
public class LivroService {
    
    @Autowired
    private LivroRepository lRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Livro findById(Integer pId) {
        Optional<Livro> objLivro = lRepository.findById(pId);
        return objLivro.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+pId+", Tipo: "+Livro.class.getName()));
    }

    public List<Livro> findAll(Integer pIdCategoria) {
        categoriaService.findById(pIdCategoria);
        return lRepository.findAllByCategoria(pIdCategoria);
    }

    public Livro update(Integer pId, Livro pLivroObj) {
        Livro newLivro = findById(pId);
        updateData(newLivro, pLivroObj);
        return lRepository.save(newLivro);
    }

    private void updateData(Livro newLivro, Livro pLivroObj) {
        newLivro.setTitulo(pLivroObj.getTitulo());
        newLivro.setNome_autor(pLivroObj.getNome_autor());
        newLivro.setTexto(pLivroObj.getTexto());
    }

    public Livro create(Integer idCategoria, Livro objLivro) {
        objLivro.setId(null);
        Categoria cat = categoriaService.findById(idCategoria);
        objLivro.setCategoria(cat);
        return lRepository.save(objLivro);
    }

    public void delete(Integer pId) {
        Livro objLivro = findById(pId);
        lRepository.delete(objLivro);
    }
}
