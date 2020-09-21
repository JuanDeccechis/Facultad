package edu.isistan;


import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import edu.isistan.controller.CarreraJPAController;
import edu.isistan.controller.EstudianteJPAController;
import edu.isistan.controller.MatriculaJPAController;
import edu.isistan.entidad.Carrera;
import edu.isistan.entidad.Estudiante;
import edu.isistan.entidad.Matricula;

/** Esta clase ejecuta los diferentes incisos que tiene que ver con la insercion de elementos desde un csv
 * @author Belen Enemark
 * @author Juan Deccechis
 * @author Mateo Zarrabeitia*/
public class Insert {

	private static final String path = "src/datasets/";

	public static void main(String[] args) throws ParseException {
		//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
		//		EntityManager em = emf.createEntityManager();
		//		em.getTransaction().begin();


		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 23);
		cal.set(Calendar.MONTH, 3);
		cal.set(Calendar.YEAR, 2009);

		//CREACION DE ESTUDIANTES
		Estudiante e1 = new Estudiante(1,"Pedro", "Perez", 18, "M", 44222444, "Tandil");
		Estudiante e2 = new Estudiante(2,"Juan", "Lopez", 18, "M", 45555222, "Tandil");
		Estudiante e3 = new Estudiante(3,"Roberto", "Santamarina", 18, "M", 14522333, "Tandil");
		Estudiante e4 = new Estudiante(4,"Lucia", "Martinez", 33, "F", 15444225, "Tandil");
		Estudiante e5 = new Estudiante(5,"Lucia", "Martinez", 33, "F", 15444225, "Tandil");

		//PERSISTIR ESTUDIANTES
		EstudianteJPAController estudiantes = new EstudianteJPAController();
		//		estudiantes.insert(e1);
		//		estudiantes.insert(e2);
		//		estudiantes.insert(e3);
		//		estudiantes.insert(e4);
		//		estudiantes.insert(e5);
		cargarEstudiantes(estudiantes);


		//CREACION DE LAS CARRERAS
		Carrera c1 = new Carrera(1,"Tudai");
		Carrera c2 = new Carrera(2,"Sistemas");


		CarreraJPAController carreras = new CarreraJPAController();
		//		carreras.insertCarrera(c1);
		//		carreras.insertCarrera(c2);
		cargarCarreras(carreras);

		Matricula m1 = new Matricula(e1, c1, new Timestamp(cal.getTimeInMillis()), false, null);
		Matricula m2 = new Matricula(e2, c1, new Timestamp(cal.getTimeInMillis()), false, null);
		Matricula m3 = new Matricula(e3, c2, new Timestamp(cal.getTimeInMillis()), false, null);
		Matricula m4 = new Matricula(e4, c2, new Timestamp(cal.getTimeInMillis()), true,new Timestamp(cal.getTimeInMillis()));


		MatriculaJPAController matriculas = new MatriculaJPAController();
		//		matriculas.insert(m1);
		//		matriculas.insert(m2);
		//		matriculas.insert(m3);
		//		matriculas.insert(m4);
		cargarMatriculas(matriculas);



	}


	//CARGA ESTUDIANTES DESDE CSV
	public static void cargarEstudiantes(EstudianteJPAController estudiantes) {
		CSVParser parser;
		Estudiante e;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path + "estudiantes.csv"));
			for(CSVRecord row: parser) {
				e = new Estudiante(Integer.parseInt(row.get("lu")), row.get("nombre"), row.get("apellido"), Integer.parseInt(row.get("edad")),
						row.get("genero"), Integer.parseInt(row.get("dni")), row.get("ciudad_residencia"));
				estudiantes.insert(e);
			}
		} catch (FileNotFoundException e6) {
			e6.printStackTrace();
		} catch (IOException e6) {
			e6.printStackTrace();
		}
	}

	//CARGA ESTUDIANTES DESDE CSV
	public static void cargarCarreras(CarreraJPAController carreras) {
		CSVParser parser;
		Carrera c;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path + "carreras.csv"));
			for(CSVRecord row: parser) {
				c = new Carrera(Integer.parseInt(row.get("id")), row.get("nombre_carrera"));
				carreras.insertCarrera(c);
			}
		} catch (FileNotFoundException e6) {
			e6.printStackTrace();
		} catch (IOException e6) {
			e6.printStackTrace();
		}
	}

	//CARGA ESTUDIANTES DESDE CSV
	public static void cargarMatriculas(MatriculaJPAController matriculas) throws ParseException {
		CSVParser parser;
		Carrera c = new Carrera();
		Estudiante e = new Estudiante();
		Matricula m;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path + "matriculas.csv"));
			for(CSVRecord row: parser) {
				e.setLu(Integer.parseInt(row.get("id_estudiante")));
				c.setId(Integer.parseInt(row.get("id_carrera")));
				Date parsedDate = dateFormat.parse(row.get("fecha_ingreso"));
				Timestamp fecha_ingreso = new java.sql.Timestamp(parsedDate.getTime());
				Timestamp fecha_egreso = null;
				if (row.get("fecha_egreso").length() > 1) {
					parsedDate = dateFormat.parse(row.get("fecha_egreso"));
					fecha_egreso = new java.sql.Timestamp(parsedDate.getTime());
				}
				m = new Matricula(e, c, fecha_ingreso , Boolean.parseBoolean(row.get("egresado")),fecha_egreso);
				matriculas.insert(m);
			}
		} catch (FileNotFoundException e6) {
			e6.printStackTrace();
		} catch (IOException e6) {
			e6.printStackTrace();
		}
	}

}
