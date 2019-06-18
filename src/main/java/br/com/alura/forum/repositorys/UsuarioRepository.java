package br.com.alura.forum.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{ }
