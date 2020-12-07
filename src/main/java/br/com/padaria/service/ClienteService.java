package br.com.padaria.service;

import java.util.List;

import br.com.padaria.model.Cliente;

public interface ClienteService {
	
	Cliente salvar(Cliente cliente);
	
	void remover(Cliente cliente);
	
	List<Cliente> listaClientes();
	
	Cliente buscaPorIdCliente(Long idCliente);//método assinado
}
