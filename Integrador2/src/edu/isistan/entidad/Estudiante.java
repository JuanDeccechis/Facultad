package edu.isistan.entidad;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author Belen Enemark
 * @author Juan Deccechis
 * @author Mateo Zarrabeitia
 * Esta clase contiene los metodos para generar un estudiante*/
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
	
	/**Constructor vacio de estudiante*/
	public Estudiante() {
		super();
	}
	/**Constructor con dato de libreta universitaria por si se tiene que cargar usuarios de prueba manualmente para probar la clase
	 * @param lu libreta universitaria
	 * @param nombre nombre de estudiante
	 * @param apellido apellido de estudiante
	 * @param edad edad del estudiante
	 * @param genero genero del estudiante
	 * @param dni dni del estudiante
	 * @param  ciudad_residencia lugar donde vive el estudiante
	 * */
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
	/**Constructor con dato de libreta universitaria por si se tiene que cargar usuarios de prueba desde csv
	 * @param nombre nombre de estudiante
	 * @param apellido apellido de estudiante
	 * @param edad edad del estudiante
	 * @param genero genero del estudiante
	 * @param dni dni del estudiante
	 * @param  ciudad_residencia lugar donde vive el estudiante
	 * */
	
	public Estudiante(String nombre, String apellido, int edad, String genero, int dni, String ciudad_residencia) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.dni = dni;
		this.ciudad_residencia = ciudad_residencia;
	}

	/**Obtener libreta universitaria
	 * @return lu retorna el numero de libreta universitaria*/
	public int getLu() {
		return lu;
	}

	/**Modifica la libreta universitaria
	 * @param lu libreta universitaria
	 * */
	public void setLu(int lu) {
		this.lu = lu;
	}
	
	/**Obtiene nombre
	 * @return nombre nombre del estudiante*/
	public String getNombre() {
		return nombre;
	}

	/**Modifica nombre de estudiante
	 * @param nombre nombre de estudiante*/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**Obtiene apellido
	 * @return apellido  apellido del estudiante*/
	public String getApellido() {
		return apellido;
	}

	/**Modifica apellido
	 * @param apellido apellido del estudiante*/
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**Obtiene edad
	 * @return edad edad del estudiante*/
	public int getEdad() {
		return edad;
	}

	/**Modifica la edad
	 * @param edad edad del estudiante*/
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**Obtiene genero
	 * @return genero genero del estudiante ya sea masculino/femenino/no sabe*/
	public String getGenero() {
		return genero;
	}

	/**Modifica genero
	 * @param genero genero del estudiante*/
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**Obtiene estudiante por dni
	 * @return dni dni del estudiante */
	public int getDni() {
		return dni;
	}

	/**Modifica dni del estudiante
	 * @param dni dni del estudiante*/
	public void setDni(int dni) {
		this.dni = dni;
	}

	/**Obtener ciudad de residencia del estudiante
	 * @return ciudad_residencia ciudad de residencia como por ejemplo Tandil, Loberia, Tres Arroyos */
	public String getCiudad_residencia() {
		return ciudad_residencia;
	}

	/**Modifica ciudad de residencia del estudiante
	 * @param ciudad_residencia ciudad de residencia del estudiante */
	public void setCiudad_residencia(String ciudad_residencia) {
		this.ciudad_residencia = ciudad_residencia;
	}

/**Obtiene un listado de las carreras en las que esta el estudiante
 * @return carreras las carreras en las que esta el estudiante */
	public List<Matricula> getCarreras() {
		return carreras;
	}

/**Modifica las carreras en las que esta inscripto un alumno
 * @param carreras lista de las carreras que esta ese alumno*/
	public void setCarreras(List<Matricula> carreras) {
		this.carreras = carreras;
	}


	@Override
	public String toString() {
		return "Estudiante [lu=" + lu + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", genero="
				+ genero + ", dni=" + dni + ", ciudad_residencia=" + ciudad_residencia+"]";
	}




	
	
}
