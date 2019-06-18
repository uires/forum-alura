package br.com.alura.forum.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controllers.dto.TopicoDTO;
import br.com.alura.forum.models.Curso;
import br.com.alura.forum.models.Topico;

@RestController
public class TopicosController {

	@RequestMapping(path = "/topicos")
	public List<TopicoDTO> lista() {
		TopicoDTO topico = new TopicoDTO(
				new Topico("Duvida", "Dúvida com Spring", new Curso("Spring: RESTFull app", "asd")));
		return Arrays.asList(topico, topico, topico);
	}
}
