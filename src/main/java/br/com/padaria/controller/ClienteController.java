package br.com.padaria.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.padaria.model.Cliente;
import br.com.padaria.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import junit.framework.TestCase;

@RestController
@RequestMapping("/clientes/api/")
public class ClienteController extends TestCase {// usei TestCase (JUnit) para testar aplicação
	
	@Autowired
	private ClienteService  clienteService;
	
	@ApiOperation(
			value="Cadastrar um Cliente novo", 
			response=ResponseEntity.class, 
			notes="Salvando registro do novo cliente.")
	@PostMapping("/v1")
	public Cliente salvarV1(@RequestBody Cliente cliente) {
		return this.clienteService.salvar(cliente);
	}
	@ApiOperation(
			value="Cadastrar um Cliente novo", 
			response=ResponseEntity.class, 
			notes="Salvando registro do novo cliente.")
	@PostMapping("/v2")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Cliente> salvarV2(@RequestBody Cliente cliente){
		return ResponseEntity.ok().body(this.clienteService.salvar(cliente));
	}
	@ApiOperation(
			value="Listando um novo Cliente", 
			response=ResponseEntity.class, 
			notes="Listando registro do novo cliente.")
	@GetMapping("/v1")//versão das listas
	public List<Cliente> listarV1() {
		return this.clienteService.listaClientes();
	}
	@ApiOperation(
			value="Listando um novo Cliente", 
			response=ResponseEntity.class, 
			notes="Listando registro do novo cliente.")
	@GetMapping("/v2")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Cliente>> listarV2() {
		return ResponseEntity.ok().body(this.clienteService.listaClientes());
	}
	@ApiOperation(
			value="Editando um novo Cliente", 
			response=ResponseEntity.class, 
			notes="Editando registro do novo cliente.")
	@PutMapping("/v1/{id}")
	public Cliente editarClienteId1(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
		Cliente clienteDoBancoDeDados = this.clienteService.buscaPorIdCliente(id);
		BeanUtils.copyProperties(cliente, clienteDoBancoDeDados, "id");
		this.clienteService.salvar(clienteDoBancoDeDados);
		return clienteDoBancoDeDados;
	}
	@ApiOperation(
			value="Editando um novo Cliente", 
			response=ResponseEntity.class, 
			notes="Editando registro do novo cliente.")
	@PutMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Cliente> editarClienteId2(@PathVariable("id") Long id, @RequestBody Cliente cliente){
		Cliente clienteDoBancoDeDados = this.clienteService.buscaPorIdCliente(id);
		BeanUtils.copyProperties(cliente, clienteDoBancoDeDados, "id");
		return ResponseEntity.ok().body(this.clienteService.salvar(clienteDoBancoDeDados));
	}
	@ApiOperation(
			value="Deletando um novo Cliente", 
			response=ResponseEntity.class, 
			notes="Deletando registro do novo cliente.")
	@DeleteMapping("/v1/{id}")
	public String deletar1(@PathVariable("id") Long id) {
		this.clienteService.remover(this.clienteService.buscaPorIdCliente(id));
		return "Cliente informado excluído com sucesso!";
	}
	@ApiOperation(
			value="Deletando um novo Cliente", 
			response=ResponseEntity.class, 
			notes="Deletando registro do novo cliente.")
	@DeleteMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK )
	public String deletar2(@PathVariable("id") Long id) {
		this.clienteService.remover(this.clienteService.buscaPorIdCliente(id));
		return "Cliente informado excluído com sucesso!";
		
	}
	
}
