package br.com.padaria.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

/**
 * @author marcio
 *
 */
@Data 
@Entity
@Table(name="tb_enderecos")
public class Endereco implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rua;
	private String bairro;
	private String cidade;
	
	@JsonBackReference(value = "cliente")
	@ManyToOne
	private Cliente cliente;
	
	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	
	  @JsonManagedReference
	  @JoinColumn(name = "id_fornecedor")
	  @OneToOne 
	  private Fornecedor fornecedor;
	 
	
}
