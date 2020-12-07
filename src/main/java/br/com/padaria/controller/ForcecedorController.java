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

import br.com.padaria.model.Fornecedor;
import br.com.padaria.repository.FornecedorRepository;
import io.swagger.annotations.ApiOperation;
import junit.framework.TestCase;

/**
 * @author marcio
 *
 */
@RestController
@RequestMapping("/fornecedores/api/")
public class ForcecedorController extends TestCase {// usei TestCase (JUnit) para testar aplicação
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	@ApiOperation(
			value="Cadastrar um Fornecedor novo", 
			response=ResponseEntity.class, 
			notes="Salvando registro do novo Fornecedor.")
	@PostMapping("/v1")
	public Fornecedor salvarV1(@RequestBody Fornecedor fornecedor) {
		 this.fornecedorRepository.save(fornecedor);
		 return fornecedor;
	}
	@ApiOperation(
			value="Cadastrar um Fornecedor novo", 
			response=ResponseEntity.class, 
			notes="Salvando o registro do novo Fornecedor.")
	@PostMapping("/v2")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Fornecedor> salvarV2(@RequestBody Fornecedor fornecedor){
		return ResponseEntity.ok().body(this.fornecedorRepository.save(fornecedor));
	}
	@ApiOperation(
			value="Listar Fornecedor novo", 
			response=ResponseEntity.class, 
			notes="Listando registro do novo Fornecedor.")
	@GetMapping("/v1")
	public List<Fornecedor> listarV1() {
		return this.fornecedorRepository.findAll();
	}
	@ApiOperation(
			value="Listar Fornecedor novo", 
			response=ResponseEntity.class, 
			notes="Listando registro do novo Fornecedor.")
	@GetMapping("/v2")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Fornecedor>> listarV2(){
		return ResponseEntity.ok().body(this.fornecedorRepository.findAll());
	}
	@ApiOperation(
			value="Editar Fornecedor novo", 
			response=ResponseEntity.class, 
			notes="Editando registro do novo Fornecedor.")
	@PutMapping("/v1/{id}")
	public Fornecedor editarFornecedorV1(@PathVariable("id") Long id, @RequestBody Fornecedor fornecedor) {
		Fornecedor fornecedorDoBancoDeDados = this.fornecedorRepository.findById(id).get();
		BeanUtils.copyProperties(fornecedor, fornecedorDoBancoDeDados, "id");
		this.fornecedorRepository.save(fornecedorDoBancoDeDados);
		return fornecedorDoBancoDeDados;
	}
	@ApiOperation(
			value="Editar Fornecedor novo", 
			response=ResponseEntity.class, 
			notes="Editando registro do novo Fornecedor.")
	@PutMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Fornecedor> editarFornecedorV2(@PathVariable("id") Long id, @RequestBody Fornecedor fornecedor){
		Fornecedor fornecedorDoBancoDeDados = this.fornecedorRepository.findById(id).get();
		BeanUtils.copyProperties(fornecedor, fornecedorDoBancoDeDados, "id");
		return ResponseEntity.ok().body(this.fornecedorRepository.save(fornecedorDoBancoDeDados));
	}
	@ApiOperation(
			value="Deletar Fornecedor novo", 
			response=ResponseEntity.class, 
			notes="Deletando registro do novo Fornecedor.")
	@DeleteMapping("/v1/{id}")
	public String deletarV1(@PathVariable("id") Long id) {
		this.fornecedorRepository.deleteById(id);
		return "Fornecedor excluído com sucesso!";
	}
	@ApiOperation(
			value="Deletar Fornecedor novo", 
			response=ResponseEntity.class, 
			notes="Deletando registro do novo Fornecedor.")
	@DeleteMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deletarV2(@PathVariable("id") Long id) {
		this.fornecedorRepository.deleteById(id);
		return "Fornecedor foi excluído";
	}
}
