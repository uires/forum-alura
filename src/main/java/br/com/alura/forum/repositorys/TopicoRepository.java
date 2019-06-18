package br.com.alura.forum.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.models.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Integer>{

	List<Topico> findByCursoNome(String nomeCurso); 
}
