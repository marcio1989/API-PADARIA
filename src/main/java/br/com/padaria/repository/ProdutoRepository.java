/**
 * 
 */
package br.com.padaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.padaria.model.Produto;

/**
 * @author marcio
 *
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
