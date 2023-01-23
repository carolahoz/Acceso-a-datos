import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/* 
 * CLC 12122022 - Clase OperacionesChat que contiene los métodos que actuan sobre la base de datos
 */

public class OperacionesChat {

	// creo la conexión

	ConexionBD conexion = new ConexionBD();

	Connection conn = conexion.getConexion();

	// método crearTabla que crea la tabla CHAT

	public void crearTabla() {

		// creo el statement y la query

		Statement st = null;

		String query = "CREATE TABLE IF NOT EXISTS CHAT" + "( IDMENSAJE INT NOT NULL," + "IDORIGEN INT NOT NULL, "
				+ "IDDESTINO INT NOT NULL , " + "MENSAJE VARCHAR(100) NOT NULL , " + "ESTADO VARCHAR(9) NOT NULL,"
				+ "PRIMARY KEY (IDMENSAJE ) )";

		try {

			// ejecuto la query y compruebo si funciona o no

			st = conn.createStatement();

			int result = st.executeUpdate(query);

			if (result >= 0) {
				System.out.println("Tabla chat creada con éxito");
			} else {
				System.out.println("No se ha podido crear la tabla chat");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// método crearTablaBackup que crea la tabla CHATBACKUP

	public void crearTablaBackup() {

		// creo el Statement y la query

		Statement st = null;

		String query = "CREATE TABLE IF NOT EXISTS CHATBACKUP" + "( IDMENSAJE INT NOT NULL," + "IDORIGEN INT NOT NULL, "
				+ "IDDESTINO INT NOT NULL , " + "MENSAJE VARCHAR(100) NOT NULL , " + "ESTADO VARCHAR(9) NOT NULL,"
				+ "PRIMARY KEY (IDMENSAJE ) )";

		try {

			// ejecuto la query y compruebo si funciona o no

			st = conn.createStatement();

			int result = st.executeUpdate(query);

			if (result >= 0) {
				System.out.println("Tabla chatbackup creada con éxito");
			} else {
				System.out.println("No se ha podido crear la tabla chatbackup");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/*
	 * Método leerChat que lee los mensajes con estado pendiente y el idDestino
	 * indicado. Devuelve un ArrayList de los mensajes que cumplen estas
	 * condiciones.
	 */

	public ArrayList<Mensaje> leerChat(int idDestino) {

		// creo la query, el preparedstatement, resultset, los atributos de mensaje y el
		// arraylist

		String query = "SELECT * FROM CHAT WHERE " + " ESTADO = 'PENDIENTE' AND IDDESTINO = " + idDestino;

		Mensaje mens = null;

		ResultSet rs = null;

		PreparedStatement ps = null;

		int idMens, idOr = 0;

		String mensaje, estadoTemp;

		Estado estado = null;
		ArrayList<Mensaje> arrayMens = null;

		try {

			// ejecuto la query

			ps = conn.prepareStatement(query);
			arrayMens = new ArrayList<Mensaje>();

			rs = ps.executeQuery();

			// recojo los valores con el resultset (salvo el estado y idDestino)

			while (rs.next()) {

				idMens = rs.getInt("IDMENSAJE");
				idOr = rs.getInt("IDORIGEN");
				mensaje = rs.getString("MENSAJE");
				estado = estado.PENDIENTE;

				// guardo las variables en un objeto Mensaje

				mens = new Mensaje(idMens, idOr, idDestino, mensaje, estado);

				// añado el Mensaje al ArrayList

				arrayMens.add(mens);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// devuelvo el ArrayList

		return arrayMens;

	}

	// método escribirChat que mete los mensajes pendientes del ArrayList en la
	// tabla CHAT

	public void escribirChat(ArrayList<Mensaje> arrayMens) {

		Estado estado = null;

		PreparedStatement ps = null;

		try {

			// creo un bucle for

			for (Mensaje men : arrayMens) {

				// si el estado del Mensaje es pendiente hago un insert en la tabla chat

				if (men.getEstado() == estado.PENDIENTE) {

					String query = "INSERT INTO CHAT VALUES (?,?,?,?,?)";

					/// creo un preparedStatement

					ps = conn.prepareStatement(query);
					ps.setInt(1, men.getIdMensaje());
					ps.setInt(2, men.getIdOrigen());
					ps.setInt(3, men.getIdDestino());
					ps.setString(4, men.getTexto());
					ps.setString(5, "PENDIENTE");

					// ejecuto la query

					ps.executeUpdate();

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// método actualizaMensajes que cambia el estado de los Mensajes que tengan el
	// idOrigen indicado a leido

	public void actualizaMensajes(int idOrigen) {

		// creo el preparedstatement y la query

		PreparedStatement ps = null;

		int numFilas = 0;

		String query = "UPDATE CHAT SET ESTADO = 'LEIDO' WHERE IDORIGEN = " + idOrigen;

		try {

			// ejecuto la query y compruebo que funciona

			ps = conn.prepareStatement(query);

			numFilas = ps.executeUpdate();

			if (numFilas >= 0) {

				System.out.println("Se ha ejecutado correctamente la actualización");
			} else {
				System.out.println("No se ha ejecutado correctamente la actualización");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// método realizarBackup que mete en la tabla CHATBACKUP los Mensajes con el
	// idDestino y estado indicado

	public void realizarBackup(int idDestino, Estado estado) {

		// creo un String que refleja si el enumerado equivale estado.LEIDO o
		// estado.PENDIENTE

		String estadoString = null;

		if (estado == estado.LEIDO) {
			estadoString = "LEIDO";
		} else if (estado == estado.PENDIENTE) {
			estadoString = "PENDIENTE";
		}

		// meto el String en la query cuando la creo

		String query = "INSERT INTO CHATBACKUP(IDMENSAJE, IDORIGEN, IDDESTINO, MENSAJE, ESTADO)"
				+ " SELECT IDMENSAJE, IDORIGEN, IDDESTINO, MENSAJE, ESTADO" + " FROM CHAT WHERE IDDESTINO = "
				+ idDestino + " AND " + "ESTADO = '" + estadoString + "'";

		PreparedStatement ps = null;

		try {

			// ejecuto la query

			ps = conn.prepareStatement(query);

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
