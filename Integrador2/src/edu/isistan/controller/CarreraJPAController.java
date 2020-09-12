package edu.isistan.controller;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.isistan.entidad.Carrera;

public class CarreraJPAController implements Serializable{

	private static final long serialVersionUID = 902997133635722325L;
	private EntityManagerFactory emf = null;

	public CarreraJPAController() {
		this.emf = Persistence.createEntityManagerFactory("Example");
	}	
	public void insertCarrera(Carrera carrera) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			if (this.getCarreraNombre(carrera.getNombre_carrera()) != null ) {
				System.out.println("La carrera "+carrera.getNombre_carrera()+" ya se encuentra en la base de datos");
			} else {
				em.getTransaction().begin();
				em.persist(carrera);
				em.getTransaction().commit();
			}

		} catch (Exception e) {
			throw new RuntimeException("Error", e);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void updateCarrera(Carrera carrera) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			carrera = em.merge(carrera);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.find(carrera.getClass(), carrera.getId()) != null ) {
				System.out.println("La carrera "+carrera.getNombre_carrera()+" no existe en la base de datos");
			}
			throw e;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void deleteCarrera(int id) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Carrera carrera = null;
			try {
				carrera = em.getReference(Carrera.class, id);
				carrera.getId();
			} catch (Exception e) {
				System.out.println("Error al eliminar la carrera id: "+id);
			}
			em.remove(carrera);
			em.getTransaction().commit(); 
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
	
	public Carrera getCarreraId(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Carrera.class, id);
		} finally {
			em.close();
		}
	}
	
	public Carrera getCarreraNombre(String nombreCarrera) {
		EntityManager em = emf.createEntityManager();
		List<Carrera> listado = em.createQuery("SELECT C FROM Carrera C WHERE C.nombre_carrera =:nombre ", Carrera.class)
				.setParameter("nombre", nombreCarrera)
				.getResultList();

		if (listado.size() == 0) {
			return null;
		} else {
			return listado.get(0);		
		}
	
	}


}
