package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	public List<Produto> findAllByNomeMarcaContainingIgnoreCase(String titulo);
	public List<Produto> findAllByDescricaoContainingIgnoreCase(String autor);
}
