/**
 * 
 */
package br.com.padaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.padaria.model.Endereco;

/**
 * @author marcio
 *
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
