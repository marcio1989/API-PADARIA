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
import br.com.padaria.model.Produto;
import br.com.padaria.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import junit.framework.TestCase;


/**
 * @author marcio
 *
 */

@RestController
@RequestMapping("/produtos/api/")
public class ProdutoController extends TestCase{// usei TestCase (JUnit) para testar aplicação
	//@Autowired
	//private ProdutoRepository repository; //classe controller não precisa saber do repository
		
	@Autowired
	private ProdutoService produtoService; // classe controller referencia a interface service 
	@ApiOperation(
			value="Cadastrar um Produto novo", 
			response=ResponseEntity.class, 
			notes="Salvando registro do novo Produto.")
	@PostMapping("/v1")
	public Produto salvarV1(@RequestBody Produto produto) {
		return this.produtoService.salvar(produto);
	}
	@ApiOperation(
			value="Cadastrar um Produto novo", 
			response=ResponseEntity.class, 
			notes="Salvando o registro do novo Produto.")
	@PostMapping("/v2")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Produto> salvarV2(@RequestBody Produto produto){
		return ResponseEntity.ok().body(this.produtoService.salvar(produto));
	}
	@ApiOperation(
			value="Listar um Produto novo", 
			response=ResponseEntity.class, 
			notes="Listando registro do novo Produto.")
	@GetMapping("/v1")
	public List<Produto> listar(){
		return this.produtoService.listarProdutos();
	}
	@ApiOperation(
			value="Listar um Produto novo", 
			response=ResponseEntity.class, 
			notes="Listando registro do novo Produto.")
	@GetMapping("/v2")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Produto>> listarV2(){
		return ResponseEntity.ok().body(this.produtoService.listarProdutos());
	}
	@ApiOperation(
			value="Editar um Produto novo", 
			response=ResponseEntity.class, 
			notes="Editando registro do novo Produto.")
	@PutMapping("/v1/{id}")
	public Produto editarProdutoId1(@PathVariable("id") Long id, @RequestBody Produto produto) {
		Produto produtoDoBancoDeDados = this.produtoService.buscarPorIdProduto(id);
		BeanUtils.copyProperties(produto, produtoDoBancoDeDados, "id");
		this.produtoService.salvar(produtoDoBancoDeDados);
		return produtoDoBancoDeDados;
	}
	@ApiOperation(
			value="Editar um Produto novo", 
			response=ResponseEntity.class, 
			notes="Editando registro do novo Produto.")
	@PutMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Produto> editarProdutoId2(@PathVariable("id") Long id, @RequestBody Produto produto){
		Produto produtoDoBandoDeDados = this.produtoService.buscarPorIdProduto(id);
		BeanUtils.copyProperties(produto, produtoDoBandoDeDados, "id");
		return ResponseEntity.ok().body(this.produtoService.salvar(produtoDoBandoDeDados));
	}
	@ApiOperation(
			value="Deletar um Produto novo", 
			response=ResponseEntity.class, 
			notes="Deletando registro do novo Produto.")
	@DeleteMapping("/v1/{id}")
	public String Deletar1(@PathVariable("id") Long id) {
		this.produtoService.removerPorProduto(this.produtoService.buscarPorId(id));
		return "Produto excluído com sucesso!";
	}
	@ApiOperation(
			value="Deletar um Produto novo", 
			response=ResponseEntity.class, 
			notes="Deletando registro do novo Produto.")
	@DeleteMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deletar2(@PathVariable("id") Long id) {
		this.produtoService.remover(this.produtoService.buscarPorId(id));
		return "Produto foi deletado!";
	}
	
	public Produto buscarProdutoId(@PathVariable("id") Long id, @RequestBody Produto produto) {
		Produto produtoBancoDeDados = this.produtoService.buscarPorId(id);
		BeanUtils.copyProperties(produto, produtoBancoDeDados, "id");
		this.produtoService.salvar(produtoBancoDeDados);
		return produtoBancoDeDados;
	}
	
//	@GetMapping
//	public List<Produto> listar() {
//		return this.repository.findAll();
//	}
//	@PutMapping("/{id}")
//	public Produto editar(@PathVariable("id") Long id,@RequestBody Produto produto) {
//		Produto produtoDoBancoDeDados = this.repository.findById(id).get();
//		BeanUtils.copyProperties(produto, produtoDoBancoDeDados, "id");
//		this.repository.save(produtoDoBancoDeDados);
//		return produtoDoBancoDeDados;
//	}
//	@DeleteMapping("/{id}")
//	public void deletar(@PathVariable("id") Long id) {
//		this.repository.deleteById(id);
//	} 
//	@PostMapping
//	public Produto salvar(@RequestBody Produto produto) {
//		return this.repository.save(produto);
//	}
}
