package br.com.padaria.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.padaria.model.Usuario;
import br.com.padaria.repository.UsuarioRepository;
import io.swagger.annotations.ApiOperation;
import junit.framework.TestCase;

@RestController
@RequestMapping("/usuarios/api/")
public class UsuarioController extends TestCase{// usei TestCase (JUnit) para testar aplicação
	@Autowired
	private UsuarioRepository usuarioRepository;
	@ApiOperation(
			value="Cadastrar um Usuario novo", 
			response=ResponseEntity.class, 
			notes="Salvando o registro do novo Usuario.")
	@PostMapping("/v1")
	public Usuario salvarV1(@RequestBody Usuario usuario) {
		this.usuarioRepository.save(usuario);
		return usuario;
	}
	@ApiOperation(
			value="Cadastrar um Usuario novo", 
			response=ResponseEntity.class, 
			notes="Salvando o registro do novo Usuario.")
	@PostMapping("/v2")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Usuario> salvarV2(@RequestBody Usuario usuario){
		return ResponseEntity.ok().body(this.usuarioRepository.save(usuario));
	}
	@ApiOperation(
			value="Listar um Usuario novo", 
			response=ResponseEntity.class, 
			notes="Listando o registro do novo Usuario.")
	@GetMapping("/v1")
	public List<Usuario> listarV1(){
		return this.usuarioRepository.findAll();
	}
	@ApiOperation(
			value="Listar um Usuario novo", 
			response=ResponseEntity.class, 
			notes="Listando o registro do novo Usuario.")
	@GetMapping("/v2")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Usuario>> listarV2(){
		return ResponseEntity.ok().body(this.usuarioRepository.findAll());
	}
	@ApiOperation(
			value="Editar um Usuario novo", 
			response=ResponseEntity.class, 
			notes="Editando o registro do novo Usuario.")
	@PutMapping("/v1/{id}")
	public Usuario editarUsuarioV1(@PathVariable("id") Long id,@RequestBody Usuario usuario) {
		Usuario usuarioDoBancoDeDados =  this.usuarioRepository.findById(id).get();
		BeanUtils.copyProperties(usuario, usuarioDoBancoDeDados, "id");
		this.usuarioRepository.save(usuarioDoBancoDeDados);
		return usuarioDoBancoDeDados;
	}
	@ApiOperation(
			value="Editar um Usuario novo", 
			response=ResponseEntity.class, 
			notes="Editando o registro do novo Usuario.")
	@PatchMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Usuario> editarUsuarioV2(@PathVariable("id") Long id, @RequestBody Usuario usuario){
		Usuario usuarioDoBancoDeDados =  this.usuarioRepository.findById(id).get();
		BeanUtils.copyProperties(usuario, usuarioDoBancoDeDados, "id");
		return ResponseEntity.ok().body(this.usuarioRepository.save(usuario));
	}
	@ApiOperation(
			value="Deletar um Usuario novo", 
			response=ResponseEntity.class, 
			notes="Deletando o registro do novo Usuario.")
	@DeleteMapping("/v1/{id}")
	public String deletarV1(@PathVariable("id") Long id) {
		this.usuarioRepository.deleteById(id);
		return "Usuario excluído com sucesso!";
	}
	@ApiOperation(
			value="Deletar um Usuario novo", 
			response=ResponseEntity.class, 
			notes="Deletando o registro do novo Usuario.")
	@DeleteMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deletarV2(@PathVariable("id") Long id) {
		this.usuarioRepository.deleteById(id);
		return "Usuario deletado!";
	}
	
}
