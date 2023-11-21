package ar.com.avillucas.tpfinaltusi.dto;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.avillucas.tpfinaltusi.model.Artista;
import ar.com.avillucas.tpfinaltusi.model.Obra;

@Component
public class ArtistaMapper {

	@Autowired
	ObraMapper obraMapper;

	public Artista DTOToEntity(ArtistaDTO dto) {
		Artista entity = new Artista();
		entity.setId(dto.getId());
		entity.setNombre(dto.getNombre());
		entity.setApellido(dto.getApellido());
		entity.setDni(dto.getDni());
		entity.setObras(new ArrayList<Obra>());

		if (dto.getObras() != null) {
			for (ObraDTO obraDTO : dto.getObras()) {
				Obra obra = obraMapper.DTOToEntity(obraDTO);
				entity.getObras().add(obra);
			}
		}
		return entity;
	}

	public ArtistaDTO entityToDTO(Artista entity) {
		ArtistaDTO dto = new ArtistaDTO();
		dto.setId(entity.getId());
		dto.setNombre(entity.getNombre());
		dto.setApellido(entity.getApellido());
		dto.setDni(dto.getDni());
		dto.setObras(new ArrayList<ObraDTO>());

		if (entity.getObras() != null) {
			for (Obra obra : entity.getObras()) {
				ObraDTO obraDTO = obraMapper.entityToDTO(obra);
				dto.getObras().add(obraDTO);
			}
		}
		return dto;
	}

	public List<ArtistaDTO> entityToDTO(List<Artista> entities) {
		List<ArtistaDTO> dto = new ArrayList<ArtistaDTO>();
		for (Artista e : entities) {
			dto.add(this.entityToDTO(e));
		}
		return dto;
	}

	public List<Artista> DTOtoEntity(List<ArtistaDTO> dtos) {
		List<Artista> entities = new ArrayList<Artista>();
		for (ArtistaDTO d : dtos) {
			entities.add(this.DTOToEntity(d));
		}
		return entities;
	}

}
