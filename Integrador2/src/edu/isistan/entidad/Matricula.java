package edu.isistan.entidad;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	@Column (nullable=false, name="graduado")
	private boolean isGraduado;
	
	public Matricula() {
		super();
	}
	



	public Matricula(Estudiante estudiante, Carrera carrera, Timestamp fecha_inscripcion, boolean isGraduado) {
		super();
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.fecha_inscripcion = fecha_inscripcion;
		this.isGraduado = isGraduado;
	}



	public Estudiante getEstudiante() {
		return estudiante;
	}


	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}


	public Carrera getCarrera() {
		return carrera;
	}


	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}


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
