
package br.com.padaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.padaria.model.Cliente;

/**
 * @author marcio
 *
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
