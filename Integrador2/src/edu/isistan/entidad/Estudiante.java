package edu.isistan.entidad;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Estudiante {
	
	@Id
	private int lu;
	@Column (nullable=false)
	private String nombre;
	@Column (nullable=false)
	private String apellido;
	@Column (nullable=false)
	private int edad;
	@Column (nullable=false)
	private String genero;
	@Column (nullable=false, unique=true)
	private int dni;
	@Column (nullable=false)
	private String ciudad_residencia;
	
	@OneToMany(mappedBy = "estudiante")
	private List<Matricula> carreras;
	
	
	public Estudiante() {
		super();
	}
	
	public Estudiante(int lu,String nombre, String apellido, int edad, String genero, int dni, String ciudad_residencia) {
		super();
		this.lu = lu;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.dni = dni;
		this.ciudad_residencia = ciudad_residencia;
	}


	public int getLu() {
		return lu;
	}


	public void setLu(int lu) {
		this.lu = lu;
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


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public int getDni() {
		return dni;
	}


	public void setDni(int dni) {
		this.dni = dni;
	}


	public String getCiudad_residencia() {
		return ciudad_residencia;
	}


	public void setCiudad_residencia(String ciudad_residencia) {
		this.ciudad_residencia = ciudad_residencia;
	}


	public List<Matricula> getCarreras() {
		return carreras;
	}


	public void setCarreras(List<Matricula> carreras) {
		this.carreras = carreras;
	}


	@Override
	public String toString() {
		return "Estudiante [lu=" + lu + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", genero="
				+ genero + ", dni=" + dni + ", ciudad_residencia=" + ciudad_residencia + ", carreras=" + carreras + "]";
	}




	
	
}
