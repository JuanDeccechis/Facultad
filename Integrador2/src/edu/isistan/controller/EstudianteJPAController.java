package edu.isistan.controller;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.isistan.entidad.Estudiante;
import edu.isistan.entidad.Matricula;

/**
 * @author Belen Enemark
 * @author Juan Deccechis
 * @author Mateo Zarrabeitia
 * Esta clase se ocupa  de insertar, actualizar y eliminar estudiante*/
public class EstudianteJPAController implements Serializable {
	/**{@value #emf } emf creacion del entity manager para el controller*/
	private static final long serialVersionUID = 1L;
	private EntityManagerFactory emf = null;

	/**Crea el constructor */
	public EstudianteJPAController() {
		this.emf = Persistence.createEntityManagerFactory("Example");
	}


	/**Carga el estudiante, pide el dni para corroborar si el estudiante existe y lo persiste
	 * @param estudiante se ingresa un objeto estudiante*/
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
	/**Actualiza el estudiante, siempre que el estudiante exista
	 * @param estudiante se ingresa un estudiante*/
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
	/**Borra el estudiante
		@param  lu, se pasa la libreta universitaria
	 * Se guarda el dato y se controla en el try que exista el estudiante*/
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
	/**Obtener Libreta universitaria
	 * @param lu es la libreta universitaria
	 * @return em.find, retorna un estudiante encontrado por libreta universitaria*/
	public Estudiante getLU(int lu) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Estudiante.class, lu);
		} finally {
			em.close();
		}
	}
/**Obtener estudiante por dni
 * @param dni documento nacional de identidad
 * @return listado.get(0) un estudiante con dni */
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
	/**Ordena los estudiantes por apellido
	 * @return listado listado estudiantes ordenado por apellido*/
	public List<Estudiante> getEstudiantesOrdenados() {
		EntityManager em = emf.createEntityManager();
		List<Estudiante> listado = em.createQuery("SELECT E FROM Estudiante E ORDER BY E.apellido ASC ", Estudiante.class)
				.getResultList();

		return listado;
	}
	/** Obtiene el ultimo estudiante cargado
	 * @return ultimoEstudiante el ultimo estudiante dado de alta*/
	public Estudiante getUltimoEstudiante() {
		EntityManager em = emf.createEntityManager();
		List<Estudiante> ultimoEstudiante = (List<Estudiante>) em.createQuery("SELECT E FROM Estudiante E ORDER BY E.lu ", Estudiante.class)
				.setMaxResults(1)
				.getResultList();
		return (Estudiante) ultimoEstudiante.get(0);
	}
	/**Ordena los estudiantes por genero
	 * @param genero genero del estudiante
	 * @return listado estudiantes ordenados por genero*/
	public List<Estudiante> getEstudiantesGenero(String genero) {
		EntityManager em = emf.createEntityManager();
		List<Estudiante> listado = em.createQuery("SELECT E FROM Estudiante E WHERE E.genero =:genero ", Estudiante.class)
				.setParameter("genero", genero)
				.getResultList();

		return listado;
	}
	/**Ordena los estudiantes por carrera y ciudad
	 * @param carrera carrera que cursa
	 * @param ciudad ciudad en la que vive
	 * @return listado estudiantes por genero*/
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
