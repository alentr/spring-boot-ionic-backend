package com.alexandre.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alexandre.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	/**
	 * Ao fazer um método nesse padrão findBy... e o nome do atributo o Spring Data vai implementar o método
	 * @param email
	 * @return
	 */
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);
}
