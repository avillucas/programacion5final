package ar.com.avillucas.tpfinaltusi.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "artistas")
public class Artista implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank
	@Column(name = "nombre")
	private String nombre;

	@NotBlank
	@Column(name = "apellido")
	private String apellido;

	@Min(5)
	//TODO validar el unique  ya sea acá o mas atras
	@Column(name = "dni")
	private Long dni;

	@OneToMany(mappedBy = "artista", cascade = { CascadeType.ALL })
	private List<Obra> obras;

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

	public List<Obra> getObras() {
		return obras;
	}

	public void setObras(List<Obra> obras) {
		this.obras = obras;
	}

	@Override
	public String toString() {
		return "Artista [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni.toString()
				+ ", obras=" + obras.toString() + "]";
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
		Artista p = (Artista) obj;
		return this.dni.equals(p.dni);
	}

}
