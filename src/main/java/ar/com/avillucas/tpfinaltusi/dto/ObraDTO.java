package ar.com.avillucas.tpfinaltusi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ObraDTO {

	private Long id;

	@NotBlank
	private String nombre;

	private String descripcion;

	@Min(1)
	private Float precio;

	@NotNull
	public ArtistaDTO artista;

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public ArtistaDTO getArtista() {
		return artista;
	}

	public void setArtista(ArtistaDTO artista) {
		this.artista = artista;
	}

}
