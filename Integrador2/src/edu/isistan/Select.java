package edu.isistan;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.isistan.entidad.Estudiante;

public class Select {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Estudiante j = em.find(Estudiante.class, 1);
		System.out.println(j);
		
//		CONSULTA JPQA
		@SuppressWarnings("unchecked")
		List<Estudiante> personas = em.createQuery("SELECT p FROM Persona p").getResultList();
		personas.forEach(p -> System.out.println(p));
		em.getTransaction().commit();
		em.close();
		emf.close();

	}

}
