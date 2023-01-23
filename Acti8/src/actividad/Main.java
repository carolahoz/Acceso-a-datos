package actividad;

/*
 * CLC 190522 - Método main 
 * 
 */


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class Main {
	
	//Se crea el método main

	public static void main(String[] args) throws Exception {
		
		// se crea el objeto bd de tipo ConexionBD
		
		ConexionBD cbd = new ConexionBD();
		
		//se crea conexion de tipo Connection
		
		Connection conexion = cbd.getConexion();
		
		//se crea el objeto ga de tipo GestionAsignaturas
		
		GestionAsignaturas ga = new GestionAsignaturas();

		//si la conexion a base de datos no es nula se ejecutan mis métodos
		
		try {
			if (conexion != null) {
				
				//se avisa al usuario que se ha conectado a la base de datos
				
				System.out.println("Conectado!! " + conexion.getMetaData().getDatabaseProductName());
				
				//se crea la tabla

				cbd.crearTabla();
				
				//se añaden las asignaturas

				ga.anyadirAsignatura(1, "Mates");

				ga.anyadirAsignatura(2, "Lengua");

				ga.anyadirAsignatura(3, "Ciencias");

				ga.anyadirAsignatura(4, "CCSS");
				
				//se imprime las asignaturas en el HashTable para comprobar que se han metido

				System.out.println(ga.getDiccionarioAsignaturas());
				
				//se añade una asignatura nueva con un codigo que ya existe

				ga.anyadirAsignatura(3, "Ingles");
				
				//se elimina la asignatura 4


				ga.eliminarAsignatura(4);
				
				//se intenta eliminar la asignatura 5


				try {
					ga.eliminarAsignatura(5);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//se limpia los datos de la tabla

				cbd.limpiaDatosTablaBD();
				
				//se crea un array con numeros enteros

				int[] keys = { 1, 2, 3 };
				
				//se guarda las asignaturas en la base de datos cuyo código coincide con los numeros del array

				ga.guardarAsignaturaBD(keys);
				
				//se borra la asignatura 2 de la base de datos

				ga.borrarAsignaturaBD(2);
				
				//se libera la conexión (se cierra y se pone a null)

				cbd.liberarConexion();
				
			}
			
			//se recoge la excepción 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
