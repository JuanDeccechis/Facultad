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
		//		Query q = em.createNativeQuery("SELECT m FROM Matricula m WHERE m.id_estudiante =:es AND m.id_carrera =:ca");
		//		List<Object[]> authors = q.getResultList();
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

}
