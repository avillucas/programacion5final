package ar.com.avillucas.tpfinaltusi.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import ar.com.avillucas.tpfinaltusi.model.Obra;

@Component
public class ObraMapper {


	public ObraDTO entityToDTO(Obra entity) {
		ObraDTO dto = new ObraDTO();
		dto.setNombre(entity.getNombre());
		dto.setPrecio(entity.getPrecio());
		dto.setDescripcion(entity.getDescripcion());
		dto.setId(entity.getId());
		return dto;
	}

	public Obra DTOToEntity(ObraDTO dto) {
		Obra entity = new Obra();
		entity.setNombre(dto.getNombre());
		entity.setPrecio(dto.getPrecio());
		entity.setDescripcion(dto.getDescripcion());
		entity.setId(dto.getId());
		return entity;
	}

	public List<ObraDTO> entityToDTO(List<Obra> entities) {
		List<ObraDTO> dto = new ArrayList<ObraDTO>();
		for (Obra e : entities) {
			dto.add(this.entityToDTO(e));
		}
		return dto;
	}

	public List<Obra> DTOtoEntity(List<ObraDTO> dtos) {
		List<Obra> entities = new ArrayList<Obra>();
		for (ObraDTO d : dtos) {
			entities.add(this.DTOToEntity(d));
		}
		return entities;
	}
}
