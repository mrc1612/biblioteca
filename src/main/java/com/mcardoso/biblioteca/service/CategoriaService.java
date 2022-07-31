package com.mcardoso.biblioteca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcardoso.biblioteca.domain.Categoria;
import com.mcardoso.biblioteca.dtos.CategoriaDTO;
import com.mcardoso.biblioteca.repositories.CategoriaRepository;
import com.mcardoso.biblioteca.resources.exceptions.DataIntegrityViolationException;
import com.mcardoso.biblioteca.service.exceptions.ObjectNotFoundException;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository cRepository;
    
    public Categoria findById(Integer id) {
        Optional<Categoria> obj = cRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", Tipo: "+Categoria.class.getName()));
    }
    
    public List<Categoria> findAll() {
        return cRepository.findAll();
    }

    public Categoria create(Categoria pCategoria) {
        pCategoria.setId(null);
        return cRepository.save(pCategoria);
    }

    public Categoria update(Integer pId, CategoriaDTO pCatDTO) {
        Categoria oCategoria = findById(pId);
        oCategoria.setNome(pCatDTO.getNome());
        oCategoria.setDescricao(pCatDTO.getDescricao());
        return cRepository.save(oCategoria);
    }

    public void delete(Integer pId) {
        findById(pId);
        try {
            cRepository.deleteById(pId);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Categoria não pode ser deletada! Possui livros associados!");
        }
    }
}
