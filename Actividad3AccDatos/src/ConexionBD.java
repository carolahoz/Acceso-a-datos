import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/* 
 * CLC 12122022 - Clase ConexionBD que se conecta a la base de datos
 */

public class ConexionBD {
	
	Connection conn;
	
//creo método getConexion que devuelve un objeto Connection
	
	public Connection getConexion() {
		// Datos de conexion
		String url = "jdbc:mysql://localhost:3306/chatapp?serverTimezone=UTC";
		String usuario = "carolina";
		String contrasenya = "Meddameer003*";
		
		// creamos la conexion
		try {
			Connection conn = DriverManager.getConnection(url, usuario, contrasenya);
			if (conn != null) {
				System.out.println("He conectado!!");
				return conn;
			} else return null;
		} catch (SQLException e) {
			// si no se conecta nos da un mensaje de error
			System.err.println(e.toString());
			return null;
		}
		
	}

}
