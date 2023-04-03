package io.github.isilvestreneto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.isilvestreneto.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}