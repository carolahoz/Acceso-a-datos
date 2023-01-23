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
	
	//se crea un atributo HashTable que contendr� un diccionario de asignaturas

	private Hashtable<Integer, String> diccionarioAsignaturas = new Hashtable<Integer, String>();

/*
 * m�todo que a�ade asignaturas al Hashtable seg�n un c�digo y nombre pasados por par�metro
 * Si el c�digo ya existe se pregunta si se quiere actualizar el nombre
 *  que corresponde a ese c�digo y se actualiza la tabla
 */

	public void anyadirAsignatura(int codigo, String nombre) {

		

		if (diccionarioAsignaturas.containsKey(codigo)) {

			System.out.println("Este c�digo ya existe. Desea actualizar el nombre que corresponde al c�digo? Escriba un"
					+ " 1 si es un s�, y un 2 si es un no");

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
	 * m�todo que elimina una asignatura de la HashTable seg�n un c�digo pasado por par�metro
	 * si el c�digo no existe en la HashTable se lanza excepci�n
	 */
	

	public void eliminarAsignatura(int codigo) throws Exception {

		if (diccionarioAsignaturas.containsKey(codigo) == false) {

			throw new Exception("No existe este c�digo");
		} else {

			diccionarioAsignaturas.remove(codigo);

		}

	}
	
	/*
	 * m�todo que guarda una asignatura en la base de datos 
	 * si su c�digo en el HashTable coincide con el n�mero de un array
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
	 * m�todo que borra una asignatura en la base de datos
	 * y en el HashTable seg�n un c�digo pasado por par�metro 
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
