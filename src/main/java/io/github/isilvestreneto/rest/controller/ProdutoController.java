package io.github.isilvestreneto.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.isilvestreneto.model.Produto;
import io.github.isilvestreneto.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<Produto> find(Produto filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);

		Example<Produto> example = Example.of(filtro, matcher);
		return produtoRepository.findAll(example);
	}

	@GetMapping("/{id}")
	public Produto getProdutoById(@PathVariable Integer id) {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Produto não encontrado"));
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public Produto save(@RequestBody @Valid Produto produto) {
		return produtoRepository.save(produto);
	}

	@PutMapping("/{id}")
	public void update(@PathVariable Integer id, @RequestBody @Valid Produto produto) {
		produtoRepository.findById(id).map(produtoExistente -> {
			produto.setId(produtoExistente.getId());

			if (produto.getDescricao() != null && !produto.getDescricao().isEmpty()) {
				produtoExistente.setDescricao(produto.getDescricao());
			}

			if (produto.getPreco() != null && produto.getPreco().compareTo(BigDecimal.ZERO) > 0) {
				produtoExistente.setPreco(produto.getPreco());
			}

			return produtoRepository.save(produtoExistente);

		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		produtoRepository.delete(produtoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado")));
	}

}
