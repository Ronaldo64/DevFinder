package br.com.devfinder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.devfinder.model.DesenvolvedorAreaAtuacao;
import br.com.devfinder.model.ids.DesenvolvedorAreaAtuacaoId;

/**
 * @author Ronaldo Costa
 *
 */
public interface DesenvolvedorAreaAtuacaoRepository
		extends JpaRepository<DesenvolvedorAreaAtuacao, DesenvolvedorAreaAtuacaoId> {

	@Query(value = "SELECT * FROM DESENVOLVEDOR_AREA_ATUACAO WHERE EMAIL_DESENVOLVEDOR = ?1", nativeQuery = true)
	List<DesenvolvedorAreaAtuacao> findAllByDesenvolvedor(String emailDesenvolvedor);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM DESENVOLVEDOR_AREA_ATUACAO WHERE EMAIL_DESENVOLVEDOR = ?1", nativeQuery = true)
	void deleteAllByDesenvolvedor(String emailDesenvolvedor);
}
