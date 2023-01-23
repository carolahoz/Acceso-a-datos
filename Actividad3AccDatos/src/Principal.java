import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Principal {

	/*
	 * CLC 12122022 - Clase Principal que ejecuta los m�todos que actuan sobre la
	 * base de datos chatapp
	 */

	// creo mi m�todo main

	public static void main(String[] args) {

		// instancio mi conexi�n a la base de datos

		ConexionBD conexion = new ConexionBD();

		Connection conn = conexion.getConexion();

		// creo un objeto Estado para poder utilizarlo como par�metro de los objetos Mensaje

		Estado estado = null;

		OperacionesChat opChat = new OperacionesChat();

		opChat.crearTabla();

		opChat.crearTablaBackup();

		// creo mis 5 Mensajes y los meto en un ArrayList

		Mensaje mens1 = new Mensaje(1, 612345655, 645678996, "Hola qu� tal?", estado.PENDIENTE);
		Mensaje mens2 = new Mensaje(2, 645678996, 612345655, "Te espero a las 4", estado.PENDIENTE);
		Mensaje mens3 = new Mensaje(3, 645644411, 636521499, "El autob�s va tarde. No me esperes", estado.PENDIENTE);
		Mensaje mens4 = new Mensaje(4, 636521499, 645644411, "Ok. Hasta ma�ana entonces.", estado.PENDIENTE);
		Mensaje mens5 = new Mensaje(5, 636521499, 612345655, "Se anula la excursi�n.", estado.PENDIENTE);

		ArrayList<Mensaje> arrayMens = new ArrayList<Mensaje>(Arrays.asList(mens1, mens2, mens3, mens4, mens5));

		// creo un segundo ArrayList para guardar el ArrayList resultante de leerChat

		ArrayList<Mensaje> arrayMens2 = null;

		// llamo a los m�todos de OperacionesChat

		opChat.escribirChat(arrayMens);

		opChat.actualizaMensajes(636521499);

		arrayMens2 = opChat.leerChat(612345655);

		// con el ArrayList que devolvi� leerChat muestro por pantalla los Mensajes que contiene
	

		for (Mensaje men : arrayMens2) {

			System.out.println("El mensaje leido con idDestino 612345655 es :");
			System.out.println("IDMENSAJE " + men.getIdMensaje() + " IDORIGEN " + men.getIdOrigen() + " IDDESTINO "
					+ men.getIdDestino() + " MENSAJE " + men.getTexto() + " ESTADO " + men.getEstado());

		}

		opChat.realizarBackup(612345655, estado.LEIDO);

	}

}
