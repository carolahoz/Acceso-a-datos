package actividad;

/*
 *  CLC 19052022 - Clase GestionAsignatura que mete y cambia datos en un Hashtable y la base de datos
 * 
 */

import java.util.Hashtable;

import java.util.Scanner;

public class GestionAsignaturas {
	
	//se importa Scanner y ConexionBD

	private Scanner sc = new Scanner(System.in);
	
	private ConexionBD conex = new ConexionBD();
	
	//se crea un atributo HashTable que contendrá un diccionario de asignaturas

	private Hashtable<Integer, String> diccionarioAsignaturas = new Hashtable<Integer, String>();

/*
 * método que añade asignaturas al Hashtable según un código y nombre pasados por parámetro
 * Si el código ya existe se pregunta si se quiere actualizar el nombre
 *  que corresponde a ese código y se actualiza la tabla
 */

	public void anyadirAsignatura(int codigo, String nombre) {

		

		if (diccionarioAsignaturas.containsKey(codigo)) {

			System.out.println("Este código ya existe. Desea actualizar el nombre que corresponde al código? Escriba un"
					+ " 1 si es un sí, y un 2 si es un no");

			int opcion = sc.nextInt();

			if (opcion == 1) {

				System.out.println("Dime el nuevo nombre");

				String nuevoNom = sc.next();

				diccionarioAsignaturas.replace(codigo, nuevoNom);
				
				System.out.println(diccionarioAsignaturas.get(3));
				
				
			}

			
		} else {

			diccionarioAsignaturas.put(codigo, nombre);
			
		}
		
	}
	
	/*
	 * método que elimina una asignatura de la HashTable según un código pasado por parámetro
	 * si el código no existe en la HashTable se lanza excepción
	 */
	

	public void eliminarAsignatura(int codigo) throws Exception {

		if (diccionarioAsignaturas.containsKey(codigo) == false) {

			throw new Exception("No existe este código");
		} else {

			diccionarioAsignaturas.remove(codigo);

		}

	}
	
	/*
	 * método que guarda una asignatura en la base de datos 
	 * si su código en el HashTable coincide con el número de un array
	*/
	
	public void guardarAsignaturaBD(int [] codigos) {
		
		for (int cod: codigos) {
			
			if (diccionarioAsignaturas.containsKey(cod)) {
				
				String nom = diccionarioAsignaturas.get(cod);
				
				conex.guardarAsig(cod, nom);
				
				
				
				
			}
		}
	}
	
	/*
	 * método que borra una asignatura en la base de datos
	 * y en el HashTable según un código pasado por parámetro 
	 */
	
	public void borrarAsignaturaBD(int codigo) throws Exception{
		
		conex.borrarAsignatura(codigo);
		
		eliminarAsignatura(codigo);
	}
	
	
	
	//getters y setters
	
		protected Hashtable<Integer, String> getDiccionarioAsignaturas() {
		return diccionarioAsignaturas;
	}

	protected void setDiccionarioAsignaturas(Hashtable<Integer, String> diccionarioAsignaturas) {
		this.diccionarioAsignaturas = diccionarioAsignaturas;
	}

}
