/**
 * 
 */
package br.com.padaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.padaria.model.Fornecedor;

/**
 * @author marcio
 *
 */
@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{

}
