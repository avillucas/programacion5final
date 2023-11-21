package ar.com.avillucas.tpfinaltusi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.com.avillucas.tpfinaltusi.dto.ArtistaDTO;
import ar.com.avillucas.tpfinaltusi.dto.ArtistaMapper;
import ar.com.avillucas.tpfinaltusi.model.Artista;
import ar.com.avillucas.tpfinaltusi.repository.ArtistaRepository;

@RestController
@RequestMapping("/api/artista")
public class ArtistaController {

	@Autowired
	ArtistaRepository artistaRepository;

	@Autowired
	ArtistaMapper artistaMapper;

	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<?> traerArtistaYHabilidadesPorNombre(@PathVariable String nombre) {
		try {
			List<Artista> entity = artistaRepository.findAllByNombre(nombre);
			List<ArtistaDTO> listDTO = artistaMapper.entityToDTO(entity);
			return new ResponseEntity<List<ArtistaDTO>>(listDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Artista no encontrado con nombre " + nombre, HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> traerArtista(@PathVariable Long id) {
		Optional<Artista> entityOpt = artistaRepository.findById(id);
		if (entityOpt.isPresent()) {
			ArtistaDTO dto = artistaMapper.entityToDTO(entityOpt.get());
			return new ResponseEntity<ArtistaDTO>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Artista no encontrado en id = " + id, HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/")
	public ResponseEntity<?> crearArtista(@RequestBody @Validated ArtistaDTO dto) {
		try {
			Artista p = artistaMapper.DTOToEntity(dto);

			artistaRepository.save(p);
			return new ResponseEntity<String>("Guardado correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("No se pudo guardar : " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/")
	public ResponseEntity<?> modificarArtista(@RequestBody @Validated ArtistaDTO dto) {
		Optional<Artista> entityOpt = artistaRepository.findById(dto.getId());

		if (entityOpt.isPresent()) {
			Artista p = artistaMapper.DTOToEntity(dto);
			artistaRepository.save(p);
			return new ResponseEntity<String>("Modificado correctamente", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Artista no encontrado con id " + dto.getId(), HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarArtista(@PathVariable Long id) {
		try {
			Optional<Artista> entityOpt = artistaRepository.findById(id);
			if (entityOpt.isPresent()) {
				artistaRepository.delete(entityOpt.get());
				return new ResponseEntity<String>("Borrado correctamente", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Artista no encontrado con id: " + id, HttpStatus.CONFLICT);
			}

		} catch (IllegalArgumentException e) {
			return new ResponseEntity<String>("No se pudo borrar", HttpStatus.CONFLICT);
		}
	}

}
