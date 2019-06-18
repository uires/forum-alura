package br.com.alura.forum.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.models.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer>{ }
