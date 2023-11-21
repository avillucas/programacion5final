package ar.com.avillucas.tpfinaltusi.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "obras")
public class Obra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank
	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

	@NotBlank
	@Min(1)
	@Column(name = "precio")
	private Float precio;

	@ManyToOne(fetch = FetchType.LAZY) // --> Need to add this
	@JoinColumn(name = "artista_id", nullable = false)
	public Artista artista;

	@Override
	public String toString() {
		return "Obra [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio="
				+ precio.toString() + ", artista=" + artista.getNombre() + ' ' + artista.getApellido() + "]";
	}

	@Override
	public int hashCode() {
		return this.nombre.hashCode() * 15;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
		Obra p = (Obra) obj;
		return this.nombre.equals(p.nombre) && this.artista.getDni().equals(p.artista.getDni());
	}

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

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

}
