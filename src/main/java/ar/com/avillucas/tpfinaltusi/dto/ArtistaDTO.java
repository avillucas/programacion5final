package ar.com.avillucas.tpfinaltusi.dto;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class ArtistaDTO {

	private Long id;

	@NotBlank
	private String nombre;

	@NotBlank
	private String apellido;

	@NotBlank
	@Column(name = "dni")
	private Long dni;

	private List<ObraDTO> obras;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public List<ObraDTO> getObras() {
		return obras;
	}

	public void setObras(List<ObraDTO> obras) {
		this.obras = obras;
	}
}
