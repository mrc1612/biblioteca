package com.mcardoso.biblioteca.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcardoso.biblioteca.domain.Categoria;
import com.mcardoso.biblioteca.domain.Livro;
import com.mcardoso.biblioteca.repositories.CategoriaRepository;
import com.mcardoso.biblioteca.repositories.LivroRepository;

@Service
public class DBService {

    @Autowired
	private CategoriaRepository cRepository;
	@Autowired
	private LivroRepository lRepository;

    public void instanciaBD() {
        Categoria cat1 = new Categoria(null, "Informática", "Livro de TI");
		Categoria cat2 = new Categoria(null, "Ciências", "Livro de Ciências");
		Categoria cat3 = new Categoria(null, "Direito", "Livro de Direito");

		Livro l1 = new Livro(null, "Clean Code", "Marcos Cardoso", "Lorem ipsum", cat1);
		Livro l2 = new Livro(null, "Ciências 1", "José da Silva", "Lorem ipsum", cat2);
		Livro l3 = new Livro(null, "Ciências vol. 2", "José da Silva", "Lorem ipsum", cat2);
		Livro l4 = new Livro(null, "Código Penal", "Rui Barbosa", "Lorem ipsum", cat3);
		Livro l5 = new Livro(null, "Código Tributário", "José de Alencar", "Lorem ipsum", cat3);
		Livro l6 = new Livro(null, "Código Civil", "Marcos Cardoso", "Lorem ipsum", cat3);
		
		cat1.getLivros().addAll(Arrays.asList(l1));
		cat2.getLivros().addAll(Arrays.asList(l2, l3));
		cat3.getLivros().addAll(Arrays.asList(l4, l5, l6));

		this.cRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		this.lRepository.saveAll(Arrays.asList(l1,l2,l3,l4,l5,l6));
    }
    
}
