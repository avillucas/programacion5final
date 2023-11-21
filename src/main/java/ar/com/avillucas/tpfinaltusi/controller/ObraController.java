package ar.com.avillucas.tpfinaltusi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import ar.com.avillucas.tpfinaltusi.dto.ObraDTO;
import ar.com.avillucas.tpfinaltusi.dto.ObraMapper;
import ar.com.avillucas.tpfinaltusi.model.Obra;
import ar.com.avillucas.tpfinaltusi.repository.ObraRepository;

@RestController
@RequestMapping("/api/obra")
public class ObraController {

	@Autowired
	ObraRepository obraRepository;

	@Autowired
	ObraMapper obraMapper;

	
	@GetMapping("/")
	public ResponseEntity<?> traerObras() {
		List<Obra> entities = obraRepository.findAll();
		if (entities.size() > 0) {
			List<ObraDTO> listDTO = obraMapper.entityToDTO(entities);
			return new ResponseEntity<List<ObraDTO>>(listDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No se encontraron obras", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/artista/{nombre}")
	public ResponseEntity<?> traerObraYHabilidadesPorNombre(@PathVariable String nombre) {
		try {
			List<Obra> entity = obraRepository.buscarObrasPorNombreArtista(nombre);
			List<ObraDTO> listDTO = obraMapper.entityToDTO(entity);
			return new ResponseEntity<List<ObraDTO>>(listDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Obra no encontrado con nombre " + nombre, HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/precio/{precio}")
	public ResponseEntity<?> traerObraYHabilidadesPorNombre(@PathVariable Float precio) {
		try {
			List<Obra> entity = obraRepository.buscarObrasPorPrecioMaximo(precio);
			List<ObraDTO> listDTO = obraMapper.entityToDTO(entity);
			return new ResponseEntity<List<ObraDTO>>(listDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Obra no encontrado con un precio igual o inferior a  " + precio,
					HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> traerObra(@PathVariable Long id) {
		Optional<Obra> entityOpt = obraRepository.findById(id);
		if (entityOpt.isPresent()) {
			ObraDTO dto = obraMapper.entityToDTO(entityOpt.get());
			return new ResponseEntity<ObraDTO>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Obra no encontrado en id = " + id, HttpStatus.CONFLICT);
		}
	}

	@PostMapping(
			path = "/", 
			consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = {MediaType.APPLICATION_JSON_VALUE }
			)
	public ResponseEntity<?> crearObra(@RequestBody @Validated ObraDTO dto) {
		try {
			Obra p = obraMapper.DTOToEntity(dto);

			obraRepository.save(p);
			return new ResponseEntity<String>("Guardado correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("No se pudo guardar : " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(
			path = "/", 
			consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = {MediaType.APPLICATION_JSON_VALUE }
			)
	public ResponseEntity<?> modificarObra(@RequestBody @Validated ObraDTO dto) {
		Optional<Obra> entityOpt = obraRepository.findById(dto.getId());

		if (entityOpt.isPresent()) {
			Obra p = obraMapper.DTOToEntity(dto);
			obraRepository.save(p);
			return new ResponseEntity<String>("Modificado correctamente", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Obra no encontrado con id " + dto.getId(), HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarObra(@PathVariable Long id) {
		try {
			Optional<Obra> entityOpt = obraRepository.findById(id);
			if (entityOpt.isPresent()) {
				obraRepository.delete(entityOpt.get());
				return new ResponseEntity<String>("Borrado correctamente", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Obra no encontrado con id: " + id, HttpStatus.CONFLICT);
			}

		} catch (IllegalArgumentException e) {
			return new ResponseEntity<String>("No se pudo borrar", HttpStatus.CONFLICT);
		}
	}
}
