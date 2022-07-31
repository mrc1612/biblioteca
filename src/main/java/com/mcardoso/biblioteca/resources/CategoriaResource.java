package com.mcardoso.biblioteca.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mcardoso.biblioteca.domain.Categoria;
import com.mcardoso.biblioteca.dtos.CategoriaDTO;
import com.mcardoso.biblioteca.service.CategoriaService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService cService;
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
        Categoria oCategoria = cService.findById(id);
        return ResponseEntity.ok().body(oCategoria);
    }
    
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<Categoria> list = cService.findAll();
        List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria oCategoria) {
        oCategoria = cService.create(oCategoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(oCategoria.getId()).toUri();
        return ResponseEntity.created(uri).body(oCategoria);
    }

    @PutMapping(value = "/{pId}")
    public ResponseEntity<CategoriaDTO> update(@Valid @PathVariable Integer pId, @RequestBody CategoriaDTO pCatDTO) {
        Categoria newCategoria = cService.update(pId, pCatDTO);
        return ResponseEntity.ok().body(new CategoriaDTO(newCategoria));
    }

    @DeleteMapping(value = "/{pId}")
    public ResponseEntity<Void> delete(@PathVariable Integer pId) {
        cService.delete(pId);
        return ResponseEntity.noContent().build();
    }
}
