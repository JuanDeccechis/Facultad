package edu.isistan.entidad;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Carrera {
	
	@Id
	private int id;
	@Column (nullable=false)
	private String nombre_carrera;
	
	@OneToMany(mappedBy = "carrera")
	private List<Matricula> estudiantes;

	public Carrera() {
		super();
	}

	public Carrera(int id,String nombre_carrera) {
		super();
		this.id = id;
		this.nombre_carrera = nombre_carrera;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_carrera() {
		return nombre_carrera;
	}

	public void setNombre_carrera(String nombre_carrera) {
		this.nombre_carrera = nombre_carrera;
	}

	public List<Matricula> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Matricula> estudiantes) {
		this.estudiantes = estudiantes;
	}

	@Override
	public String toString() {
		return "Carrera [id=" + id + ", nombre_carrera=" + nombre_carrera + "]";
	}
	
	
	
	
	
}
