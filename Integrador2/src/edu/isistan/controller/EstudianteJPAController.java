package edu.isistan.controller;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.isistan.entidad.Estudiante;
import edu.isistan.entidad.Matricula;


public class EstudianteJPAController implements Serializable {

	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf = null;


	public EstudianteJPAController() {
		this.emf = Persistence.createEntityManagerFactory("Example");
	}



	public void insert(Estudiante estudiante) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			if (this.getEstudianteDNI(estudiante.getDni()) != null ) {
				System.out.println("El estudiante con el DNI: "+estudiante.getDni()+" ya se encuentra registrado");
			} else {
				em.getTransaction().begin();
				em.persist(estudiante);
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

	public void update(Estudiante estudiante) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			estudiante = em.merge(estudiante);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.find(estudiante.getClass(), estudiante.getLu()) != null ) {
				System.out.println("La persona no existe en la bd");
			}
			throw e;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void delete(int lu) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Estudiante estudiante = null;
			try {
				estudiante = em.getReference(Estudiante.class, lu);
				estudiante.getLu();
			} catch (Exception e) {
				System.out.println("Error al eliminar el estuduiante LU: "+lu);
			}
			em.remove(estudiante);
			em.getTransaction().commit(); 
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public Estudiante getLU(int lu) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Estudiante.class, lu);
		} finally {
			em.close();
		}
	}

	public Estudiante getEstudianteDNI(int dni) {
		EntityManager em = emf.createEntityManager();
		List<Estudiante> listado = em.createQuery("SELECT E FROM Estudiante E WHERE E.dni =:dni ", Estudiante.class)
				.setParameter("dni", dni)
				.getResultList();

		if (listado.size() == 0) {
			return null;
		} else {
			return listado.get(0);		
		}
	
	}
	
	public List<Estudiante> getEstudiantesOrdenados() {
		EntityManager em = emf.createEntityManager();
		List<Estudiante> listado = em.createQuery("SELECT E FROM Estudiante E ORDER BY E.apellido ASC ", Estudiante.class)
				.getResultList();

		return listado;
	}
	
	public Estudiante getUltimoEstudiante() {
		EntityManager em = emf.createEntityManager();
		List<Estudiante> ultimoEstudiante = (List<Estudiante>) em.createQuery("SELECT E FROM Estudiante E ORDER BY E.lu ", Estudiante.class)
				.setMaxResults(1)
				.getResultList();
		return (Estudiante) ultimoEstudiante.get(0);
	}
	
	public List<Estudiante> getEstudiantesGenero(String genero) {
		EntityManager em = emf.createEntityManager();
		List<Estudiante> listado = em.createQuery("SELECT E FROM Estudiante E WHERE E.genero =:genero ", Estudiante.class)
				.setParameter("genero", genero)
				.getResultList();

		return listado;
	}
	
	public List<Estudiante> getEstudiantesCarreraCiudad(String carrera,String ciudad) {
		EntityManager em = emf.createEntityManager();
		Query q = em.createNativeQuery("select e.* from Estudiante e \r\n" + 
				"join Matricula m ON m.id_estudiante = e.lu\r\n" + 
				"join Carrera c ON m.id_carrera = c.id\r\n" + 
				"where c.nombre_carrera =:carrera AND e.ciudad_residencia =:ciudad ", Estudiante.class)
				.setParameter("carrera", carrera).
				setParameter("ciudad", ciudad);
		List<Estudiante> listado = q.getResultList();


		return listado;
	}




}
