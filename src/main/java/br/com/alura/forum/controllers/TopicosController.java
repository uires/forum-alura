package br.com.alura.forum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controllers.dto.TopicoDTO;
import br.com.alura.forum.repositorys.TopicoRepository;

@RestController
public class TopicosController {

	@Autowired
	private TopicoRepository topicorepository;

	@RequestMapping(path = "/topicos")
	public List<TopicoDTO> lista() {
		return TopicoDTO.converter(topicorepository.findAll());
	}
}
