package br.com.alura.forum.controllers;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	/*
	@GetMapping
	public Page<TopicoDTO> lista(@RequestParam(name = "nomeCurso", required = false) String nomeCurso, 
				@RequestParam(name = "pagina" , required = false, defaultValue = "0") int pagina, 
				@RequestParam(name = "quantidade" , required = false, defaultValue = "10" ) int quantidade,
				@RequestParam(name = "ordenacao", defaultValue = "ASC", required = false) String ordenacao,
				@RequestParam(name = "filtroPalavra", defaultValue = " ", required = false) String filtroPalavra) {
		
		Pageable pageable = PageRequest.of(pagina, quantidade, Direction.fromString(ordenacao), filtroPalavra);
		if (nomeCurso == null) {
			return TopicoDTO.converter(topicoRepository.findAll(pageable));
		} else {
			return TopicoDTO.converter(topicoRepository.findByCursoNome(nomeCurso, pageable));
		}
	}
	*/
	
	@GetMapping
	public Page<TopicoDTO> lista(@RequestParam(name = "nomeCurso", required = false) String nomeCurso,
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable) {
		if (nomeCurso == null) {
			return TopicoDTO.converter(topicoRepository.findAll(pageable));
		} else {
			return TopicoDTO.converter(topicoRepository.findByCursoNome(nomeCurso, pageable));
		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriComponentsBuilder) {
		Topico topico = topicoRepository.save(form.converter(this.cursoRepository));
		URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<TopicoDetalheDTO> detalhar(@PathVariable("id") Integer id) {
		Optional<Topico> topico = this.topicoRepository.findById(id);
		if (topico.isPresent()) {
			return ResponseEntity.ok(new TopicoDetalheDTO(topico.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<TopicoDTO> atualizar(@RequestBody @Valid AtualizarTopicoForm form, @PathVariable Integer id) {
		Optional<Topico> optional = this.topicoRepository.findById(id);
		if (optional.isPresent()) {
			Topico topico = form.atualizar(id, topicoRepository);
			return ResponseEntity.ok(new TopicoDTO(topico));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable("id") Integer id) {
		Optional<Topico> optional = this.topicoRepository.findById(id);
		if (optional.isPresent()) {
			this.topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
