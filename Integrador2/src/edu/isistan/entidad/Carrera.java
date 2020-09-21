package edu.isistan.entidad;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/**
 * Clase Carrera 
 * @author Belen Enemark
 * @author Juan Deccechis
 * @author Mateo Zarrabeitia
 * Esta clase contiene el nombre de carrera y una lista de estudiantes*/
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
/**Constructor de carrera con id y nombre carrera
 * @param id identificador de carrera
 * @param nombre_carrera nombre de la carrera */
	public Carrera(int id,String nombre_carrera) {
		super();
		this.id = id;
		this.nombre_carrera = nombre_carrera;
	}
	/**Obtener id 
	 * @return id retorna identificador de carrera*/
	public int getId() {
		return id;
	}
	/** Setea el id de carrera 
	 * @param id identificador de carrera*/
	public void setId(int id) {
		this.id = id;
	}
	/**Obtener nombre de carrera
	 * @return nombre_carrera nombre de la carrera
	 * */
	public String getNombre_carrera() {
		return nombre_carrera;
	}
	/** Setear el nombre de la carrera
	 * @param nombre_carrera el nombre de la carrera que queremos setear
	 * */
	public void setNombre_carrera(String nombre_carrera) {
		this.nombre_carrera = nombre_carrera;
	}
	/** Listado de los estudiantes asociado a esa carrera
	 * @return estudiantes retorna el listado de estudiantes
	 * */
	public List<Matricula> getEstudiantes() {
		return estudiantes;
	}
	/**Cargar las matriculaciones de los estudiantes
	 * @param estudiantes referencia a los estudiantes cargados en una matricula relacionada a la carrera
	 * */
	public void setEstudiantes(List<Matricula> estudiantes) {
		this.estudiantes = estudiantes;
	}

	@Override
	/**Metodo ToString
	 * @return String retorna la carrera con su id y nombre de carrera*/
	public String toString() {
		return "Carrera [id=" + id + ", nombre_carrera=" + nombre_carrera + "]";
	}
	
	
	
	
	
}
