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

import br.com.padaria.model.Endereco;
import br.com.padaria.repository.EnderecoRepository;
import io.swagger.annotations.ApiOperation;
import junit.framework.TestCase;

/**
 * @author marcio
 *
 */
@RestController
@RequestMapping("/enderecos/api/")
public class EnderecoController extends TestCase {// usei TestCase (JUnit) para testar aplicação
	@Autowired
	private EnderecoRepository enderecoRepository;
	@ApiOperation(
			value="Cadastrar um Endereço novo", 
			response=ResponseEntity.class, 
			notes="Salvando registro do novo Endereço.")
	@PostMapping("/v1")
	public Endereco salvarV1(@RequestBody Endereco endereco) {
		this.enderecoRepository.save(endereco);
		return endereco;
	}
	@ApiOperation(
			value="Cadastrar um Endereço novo", 
			response=ResponseEntity.class, 
			notes="Salvando o registro do novo Endereço.")
	@PostMapping("/v2")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Endereco> salvarV2(@RequestBody Endereco endereco){
		return ResponseEntity.ok().body(this.enderecoRepository.save(endereco));
	}
	@ApiOperation(
			value="Listar Endereço novo", 
			response=ResponseEntity.class, 
			notes="Listando o registro do novo Endereço.")
	@GetMapping("/v1")
	public List<Endereco> listarV1(){
		return this.enderecoRepository.findAll();
	}
	@ApiOperation(
			value="Listar Endereço novo", 
			response=ResponseEntity.class, 
			notes="Listando o registro do novo Endereço.")
	@GetMapping("/v2") 
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Endereco>> listarV2() {
		return ResponseEntity.ok().body(this.enderecoRepository.findAll());
	}
	@ApiOperation(
			value="Editar Endereço novo", 
			response=ResponseEntity.class, 
			notes="Editando registro do novo Endereço.")
	@PutMapping("/v1/{id}")
	public Endereco editarEnderecoV1(@PathVariable("id") Long id, @RequestBody Endereco endereco) {
		Endereco enderecoDoBancoDeDados = this.enderecoRepository.findById(id).get();
		BeanUtils.copyProperties(endereco, enderecoDoBancoDeDados, "id");
		this.enderecoRepository.save(enderecoDoBancoDeDados);
		return enderecoDoBancoDeDados;
	}
	@ApiOperation(
			value="Editar Endereço novo", 
			response=ResponseEntity.class, 
			notes="Editando registro do novo Endereço.")
	@PutMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Endereco> editarEnderecoV2(@PathVariable("id") Long id, @RequestBody Endereco endereco){
		Endereco enderecoDoBancoDeDados = this.enderecoRepository.findById(id).get();
		BeanUtils.copyProperties(endereco, enderecoDoBancoDeDados, "id");
		return ResponseEntity.ok().body(this.enderecoRepository.save(enderecoDoBancoDeDados));
	}
	@ApiOperation(
			value="Deletar Endereço novo", 
			response=ResponseEntity.class, 
			notes="Deletando registro do novo Endereço.")
	@DeleteMapping("/v1/{id}")
	public String deletarV1(@PathVariable("id") Long id) {
		this.enderecoRepository.deleteById(id);
		return "Endereço informado deletado com sucesso!";
	}
	@ApiOperation(
			value="Deletar Endereço novo", 
			response=ResponseEntity.class, 
			notes="Deletando registro do novo Endereço.")
	@DeleteMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deletarV2(@PathVariable("id") Long id) {
		this.enderecoRepository.deleteById(id);
		return "Endereço foi excluído com sucesso!";
	}
	
}
