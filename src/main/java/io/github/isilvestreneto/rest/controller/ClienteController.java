package io.github.isilvestreneto.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
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

import io.github.isilvestreneto.model.Cliente;
import io.github.isilvestreneto.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping("/{id}")
	public Cliente getClienteById(@PathVariable Integer id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado"));
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public Cliente save(@RequestBody @Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		clienteRepository.delete(clienteRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado")));
	}

	@PutMapping("/{id}")
	public void update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {
		clienteRepository.findById(id).map(clienteExistente -> {
			cliente.setId(clienteExistente.getId());

			if (cliente.getNome() != null && !cliente.getNome().isEmpty()) {
				clienteExistente.setNome(cliente.getNome());
			}
			if (cliente.getCpf() != null && !cliente.getCpf().isEmpty()) {
				clienteExistente.setCpf(cliente.getCpf());
			}

			return clienteRepository.save(clienteExistente);
		}).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Cliente não encontrado"));
	}

	@GetMapping
	public List<Cliente> find(Cliente filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);

		Example<Cliente> example = Example.of(filtro, matcher);
		return clienteRepository.findAll(example);

	}

}
