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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mcardoso.biblioteca.domain.Livro;
import com.mcardoso.biblioteca.dtos.LivroDTO;
import com.mcardoso.biblioteca.service.LivroService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/livros")
public class LivroResource {

    @Autowired
    private LivroService lService;

    @GetMapping(value = "/{pId}")
    public ResponseEntity<Livro> findById(@PathVariable Integer pId) {
        Livro objLivro = lService.findById(pId);
        return ResponseEntity.ok().body(objLivro);
    }
//localhost:8080/livros?categoria=n
    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAll(@RequestParam(value = "categoria", defaultValue = "0")
    Integer pIdCategoria) {
        List<Livro> listLivros = lService.findAll(pIdCategoria);
        List<LivroDTO> listLivrosDTO = listLivros.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listLivrosDTO);
    }

    @PutMapping(value = "/{pId}")
    public ResponseEntity<Livro> update(@PathVariable Integer pId, @Valid @RequestBody Livro pLivroObj) {
        Livro newLivroObj = lService.update(pId, pLivroObj);
        return ResponseEntity.ok().body(newLivroObj);
    }

    @PatchMapping(value = "/{pId}")
    public ResponseEntity<Livro> updatePatch(@PathVariable Integer pId, @Valid @RequestBody Livro pLivroObj) {
        Livro newLivroObj = lService.update(pId, pLivroObj);
        return ResponseEntity.ok().body(newLivroObj);
    }
//localhost:8080/livros?categoria=n
    @PostMapping
    public ResponseEntity<Livro> create(@RequestParam(value="categoria", defaultValue = "0") Integer idCategoria, @Valid @RequestBody Livro objLivro) {
        Livro newLivroObj = lService.create(idCategoria,objLivro);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(newLivroObj.getId()).toUri();
        //Verificar nos Headers->Location a URI criada.
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{pId}")
    public ResponseEntity<Void> delete(@PathVariable Integer pId) {
        lService.delete(pId);
        return ResponseEntity.noContent().build();
    }
}
