package br.com.alura.forum.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.models.Resposta;

public interface RespostaRepository extends JpaRepository<Resposta, Integer> {
}
