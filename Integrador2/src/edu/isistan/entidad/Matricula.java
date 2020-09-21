package edu.isistan.entidad;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 * @author Belen Enemark
 * @author Juan Deccechis
 * @author Mateo Zarrabeitia
 * Esta clase contiene los metodos para generar una Matricula*/
@Entity
public class Matricula implements Serializable{

	private static final long serialVersionUID = -9039061713053107696L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "id_estudiante", referencedColumnName = "lu")
	private Estudiante estudiante;
	@Id
	@ManyToOne
	@JoinColumn(name = "id_carrera", referencedColumnName = "id")
	private Carrera carrera;
	
	
	@Column (nullable=false)
	private Timestamp fecha_inscripcion;
	
	@Column (nullable=true)
	private Timestamp fecha_egreso;
	
	@Column (nullable=false, name="graduado")
	private boolean isGraduado;
	/**Constructor vacio de matricula*/
	public Matricula() {
		super();
	}
	


	/**Constructor con los datos de estudiante con el campo de fecha_egreso estudiante. No todos los estudiantes del listado estan graduados
	 * @param estudiante objeto de tipo estudiante @see edu.isistan.entidad/Estudiante
	 * @param carrera objeto de tipo carrera @see edu.isistan.entidad/Carrera
	 * @param fecha_inscripcion fecha de inscripcion del estudiante
	 * @param isGraduado booleano para indicar si una persona esta graduada o no
	 * @param fecha_egreso fecha de egreso del estudiante
	 * */
	public Matricula(Estudiante estudiante, Carrera carrera, Timestamp fecha_inscripcion, boolean isGraduado, Timestamp fecha_egreso) {
		super();
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.fecha_inscripcion = fecha_inscripcion;
		this.isGraduado = isGraduado;
		this.fecha_egreso = fecha_egreso;
	}
	
	/**Constructor con los datos de estudiante con el campo isGraduado
	 * @param estudiante objeto de tipo estudiante @see edu.isistan.entidad/Estudiante
	 * @param carrera objeto de tipo carrera @see edu.isistan.entidad/Carrera
	 * @param fecha_inscripcion fecha de inscripcion del estudiante
	 * @param isGraduado booleano para indicar si una persona esta graduada o nos
	 * */
	public Matricula(Estudiante estudiante, Carrera carrera, Timestamp fecha_inscripcion, boolean isGraduado) {
		super();
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.fecha_inscripcion = fecha_inscripcion;
		this.isGraduado = isGraduado;
	}


	/**Obtener estudiante 
	 * @return estudiante retorna un estudiante relacionado con esta matricula*/
	public Estudiante getEstudiante() {
		return estudiante;
	}

	/**Modifica el estudiante
	 * @param estudiante se pasa el estudiante que se desea asignar a esta matricula*/
	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	/**Obtiene carrera
	 * @return carrera retorna la carrera que esta relacionada a esta matricula*/
	public Carrera getCarrera() {
		return carrera;
	}


	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	/**Obtiene fecha de inscripcion 
	 * @return devuelve la fecha de inscripcion del alumno*/
	public Timestamp getFecha_inscripcion() {
		return fecha_inscripcion;
	}


	public void setFecha_inscripcion(Timestamp fecha_inscripcion) {
		this.fecha_inscripcion = fecha_inscripcion;
	}


	public boolean isGraduado() {
		return isGraduado;
	}


	public void setGraduado(boolean isGraduado) {
		this.isGraduado = isGraduado;
	}


	public Timestamp getFecha_egreso() {
		return fecha_egreso;
	}

	/**Setea la fecha de egreso de un estudiante relacionado a esta matricula
	 * @param fecha_egreso fecha que se recibio el estudiante*/
	public void setFecha_egreso(Timestamp fecha_egreso) {
		this.fecha_egreso = fecha_egreso;
	}


	@Override
	public String toString() {
		return "Matricula [estudiante=" + estudiante + ", carrera=" + carrera + ", fecha_inscripcion="
				+ fecha_inscripcion + ", isGraduado=" + isGraduado + "]";
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carrera == null) ? 0 : carrera.hashCode());
		result = prime * result + ((estudiante == null) ? 0 : estudiante.hashCode());
		result = prime * result + ((fecha_inscripcion == null) ? 0 : fecha_inscripcion.hashCode());
		result = prime * result + (isGraduado ? 1231 : 1237);
		return result;
	}



	/**Redefinicion del equal para manipular carrera o estudiante o graduado
	 * @param obj va a cambiar de acuerdo a la evaluacion que se necesita ya sea optar por el objeto carrera o estudiante o graduado o fecha_inscripcion como posibles valores */	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matricula other = (Matricula) obj;
		if (carrera == null) {
			if (other.carrera != null)
				return false;
		} else if (!carrera.equals(other.carrera))
			return false;
		if (estudiante == null) {
			if (other.estudiante != null)
				return false;
		} else if (!estudiante.equals(other.estudiante))
			return false;
		if (fecha_inscripcion == null) {
			if (other.fecha_inscripcion != null)
				return false;
		} else if (!fecha_inscripcion.equals(other.fecha_inscripcion))
			return false;
		if (isGraduado != other.isGraduado)
			return false;
		return true;
	}
	
	
	
	
	

}
