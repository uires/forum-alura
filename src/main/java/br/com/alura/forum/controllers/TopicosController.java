package br.com.alura.forum.controllers;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controllers.dto.TopicoDTO;
import br.com.alura.forum.controllers.dto.TopicoDetalheDTO;
import br.com.alura.forum.controllers.forms.AtualizarTopicoForm;
import br.com.alura.forum.controllers.forms.TopicoForm;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repositorys.CursoRepository;
import br.com.alura.forum.repositorys.TopicoRepository;

@RestController
@RequestMapping(path = "/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	public List<TopicoDTO> lista(String nomeCurso) {
		if(nomeCurso == null) {
			return TopicoDTO.converter(topicoRepository.findAll());
		} else {
			return TopicoDTO.converter(topicoRepository.findByCursoNome(nomeCurso));
		}
	}
	
	@PostMapping
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriComponentsBuilder) {
		Topico topico = topicoRepository.save(form.converter(this.cursoRepository));
		URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}
	
	@GetMapping(path = "/{id}")
	public TopicoDetalheDTO detalhar(@PathVariable("id") Integer id) {
		Topico topico = this.topicoRepository.getOne(id);
		return new TopicoDetalheDTO(topico);
	}
	
	@PutMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<TopicoDTO> atualizar(@RequestBody @Valid AtualizarTopicoForm form, @PathVariable Integer id) {
		Topico topico = form.atualizar(id, topicoRepository);
		return ResponseEntity.ok(new TopicoDTO(topico));
	}
}
