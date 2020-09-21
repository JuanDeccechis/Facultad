# Facultad
Repo para el segundo entregable de Arquitecturas Web

Para esta entrega del trabajo realizamos un proyecto maven, dentro del cual utilizamos JPA, realizamos una conexión con una base de datos MySQL.

## Versiones
Java: 1.8
MySQL Driver: 8.0.21


## Conexion
Para realizar exitosamente la conexion a la BD, es requisito configurar "persistence.xml" (que en el servidor de desarrollo utiliza el esquema "integrador2")

## Ejecución
El orden de ejecución apropiada es: 
	- Primero ejecutar Insert.java (crea las tablas en caso que no existan, agrega datasets de prueba de los archivos con extensión ".csv").
	- Luego ejecutar Select.java (resuelve los puntos del enunciado)
