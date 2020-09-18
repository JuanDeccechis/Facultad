package edu.isistan.controller;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.isistan.entidad.Carrera;
import edu.isistan.entidad.Estudiante;
import edu.isistan.entidad.Matricula;

public class MatriculaJPAController implements Serializable {

	private static final long serialVersionUID = -6284052128342094661L;
	private EntityManagerFactory emf = null;


	public MatriculaJPAController() {
		this.emf = Persistence.createEntityManagerFactory("Example");
	}

	public void insert(Matricula matricula) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			if(this.getMatricula(matricula.getCarrera(), matricula.getEstudiante()) != null) {
				System.out.println("El alumno ya se encuentra matriculado en la carrera");
			} else {
				em.getTransaction().begin();
				em.persist(matricula);
				em.getTransaction().commit();
			}


		} catch (Exception e) {
			throw new RuntimeException("Error parsing date", e);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void update(Matricula matricula) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			matricula = em.merge(matricula);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void delete(Matricula matricula) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.remove(matricula);
			em.getTransaction().commit(); 
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}


	public Matricula getMatricula(Carrera idCarrera, Estudiante idEstudiante) {
		EntityManager em = emf.createEntityManager();
		Query q = em.createNativeQuery("SELECT * FROM Matricula M WHERE M.id_estudiante =:es AND M.id_carrera =:ca ", Matricula.class)
				.setParameter("es", idEstudiante).
				setParameter("ca", idCarrera);
		List<Matricula> listado = q.getResultList();

		if (listado.size() == 0) {
			return null;
		} else {
			return listado.get(0);		
		}

	}
	
	public List<Object[]> getReporte() {
		EntityManager em = emf.createEntityManager();
		Query q = em.createNativeQuery("\r\n" + 
				"(SELECT c.nombre_carrera AS \"Carrera\",\r\n" + 
				"SUM(if(m.graduado = '1', 0, 0)) AS \"CantInscriptos\",\r\n" + 
				" count(c.id) AS \"CantEgresados\",\r\n" + 
				" extract(year from m.fecha_egreso) AS \"Año\"\r\n" + 
				" FROM Carrera c \r\n" + 
				"JOIN Matricula m ON m.id_carrera = c.id\r\n" + 
				"WHERE m.graduado = \"1\"\r\n" + 
				"GROUP BY extract(year from m.fecha_egreso), c.id \r\n" + 
				"ORDER BY c.nombre_carrera ASC, extract(year from m.fecha_egreso) ASC)\r\n" + 
				"UNION \r\n" + 
				"(SELECT c.nombre_carrera AS \"Carrera\",\r\n" + 
				" count(c.id) AS \"CantInscriptos\",\r\n" + 
				" SUM(if(m.graduado = '1', 0, 0)) AS \"CantEgresados\",\r\n" + 
				" extract(year from m.fecha_inscripcion) AS \"Año incripcion\"\r\n" + 
				" FROM Carrera c \r\n" + 
				"JOIN Matricula m ON m.id_carrera = c.id\r\n" + 
				"GROUP BY extract(year from m.fecha_inscripcion), c.id \r\n" + 
				"ORDER BY c.nombre_carrera ASC, extract(year from m.fecha_inscripcion) ASC\r\n" + 
				")\r\n" + 
				"ORDER BY Carrera ASC, Año ASC ");
		List<Object[]> listado = q.getResultList();
		return listado;
	}

}
