package edu.isistan;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import edu.isistan.controller.CarreraJPAController;
import edu.isistan.controller.EstudianteJPAController;
import edu.isistan.controller.MatriculaJPAController;
import edu.isistan.entidad.Carrera;
import edu.isistan.entidad.Estudiante;
import edu.isistan.entidad.Matricula;

public class Select {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Date parsedDate = dateFormat.parse("2015-10-24 01:02:55.000000");
		Timestamp fecha = new java.sql.Timestamp(parsedDate.getTime());

		//PUNTO 2
		//INCISO A) DAR DE ALTA UN ESTUDIANTE
		EstudianteJPAController ejpa = new EstudianteJPAController();
		Estudiante e1 = new Estudiante("Juan", "Perez", 18, "M", 12345678, "Tandil");
		ejpa.insert(e1);

		//INCISO B) MATRICULAR UN ESTUDIANTE
		CarreraJPAController cjpa = new CarreraJPAController();
		Carrera c1 = cjpa.getCarreraId(1);
		MatriculaJPAController mjpa = new MatriculaJPAController();
		Matricula m1 = new Matricula(e1, c1,fecha, false);
		mjpa.insert(m1);

		//INCISO C) LISTAR TODOS LOS ESTUDIANTES -( EN ESTE CASO ORDENADOS POR APELLIDO)
		System.out.println("------------------------------------------");
		System.out.println("ESTUDIANTES ORDENADOS POR APELLIDO");
		for (Estudiante estudiantes : ejpa.getEstudiantesOrdenados()) {
			System.out.println(estudiantes);
		}

		//INCISO D) RECUPERAR UN ESTUDIANTE POR NUMERO DE LIBRETA -( EN ESTE CASO EL NUMERO DE LU 1)
		System.out.println("------------------------------------------");
		System.out.println("ESTUDIANTES POR LU");
		System.out.println(ejpa.getLU(1));

		//INCISO E) RECUPERAR TODOS LOS ESTUDIANTES POR GENERO
		System.out.println("------------------------------------------");
		System.out.println("ESTUDIANTES POR GENERO");
		for (Estudiante estudiantes : ejpa.getEstudiantesGenero("F")) {
			System.out.println(estudiantes);
		}

		
		//INCISO F) RECUPERAR LAS CARRERAS CON ESTUDIANTES INSCRIPTOS Y ORDENAR POR CANTIDAD DE INSCRIPTOS
		
		System.out.println("------------------------------------------");
		System.out.println("CARRERAS ORDENADAS POR CANTIDAD DE INSCRIPTOS");
		for (Carrera carreras : cjpa.getCarrerasOrdCantEstudiantes()) {
			System.out.println(carreras);
		}
		
		//INCISO G) RECUPERAR TODOS LOS ESTUDIANTES DE UNA DETERMINADA CIUDAD Y CARRERA
		System.out.println("------------------------------------------");
		System.out.println("ESTUDIANTES DE TUDAI Y TANDIL");
		for (Estudiante estudiantes : ejpa.getEstudiantesCarreraCiudad("Tudai", "Tandil")) {
			System.out.println(estudiantes);
		}
		
		

		///////////////////////////////////////////////
		//ACA VA LA GENIALIDAD DE NUESTRA DBA BELEN////
		///////////////////////////////////////////////

	}

}
