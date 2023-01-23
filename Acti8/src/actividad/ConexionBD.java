package actividad;

/*
 *  CLC 19052022 - Clase ConexionBD que se conecta con la base de datos 
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class ConexionBD {

	// atributo conexion
	private Connection conn = null;
	
	//atributo PreparedStatement 
	
	private static PreparedStatement ps = null;
	
	//m�todo getConexion que se conecta a base de datos

	public Connection getConexion() {

		try {
			
			//si la conexi�n no es nula o no est� cerrada se devuelve la conexi�n

			if (conn != null && !conn.isClosed())
				return conn;
			else {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				// url de conexion con los datos de acceso
				String urlConexion = "jdbc:sqlserver://localhost:1433;databaseName=dam1;"
						+ "trustServerCertificate=true;" + "integratedSecurity=true";

				// creacion de la conexion
				conn = DriverManager.getConnection(urlConexion);

			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;

	}

	//m�todo liberarConexion que cierra la conexi�n y la convierte en null
	
	public Connection liberarConexion() {

		

		try {
			conn.close();
			conn = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}
	
	

	// m�todo para crear la tabla Asignaturas 
	
	public boolean crearTabla() {
		// sentencia a ejecutar
		String sql = "CREATE TABLE ASIGNATURAS (" + " codigo int not null primary key,"
				+ " nombre varchar (50) not null)";
	

		Statement st;
		try {
			// creo el statment
			st = getConexion().createStatement();

			// pasamos la query y ejecutamos
			st.execute(sql);

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	
	/*
	 * m�todo que guarda el c�digo y nombre de la asignatura en la base de datos.
	 * las dos variables se pasan por par�metro 
	 */
	

	public void guardarAsig(int codigo, String nombre) {

		String preparedQuery = "INSERT INTO ASIGNATURAS VALUES (?,?)";
		try {
			

				ps = getConexion().prepareStatement(preparedQuery);

				ps.setInt(1, codigo);
				ps.setString(2, nombre);
				
				ps.executeUpdate();
		
				

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			

		}
	}
	
	//m�todo borrarAsignatura que borra la asignatura de la base de datos seg�n el c�digo paasado por par�metro
	
	public void borrarAsignatura(int codigo) {
		String query = "delete from DBO.ASIGNATURAS where codigo = " + codigo;
		Statement st = null;
		
		try {
			 st = conn.createStatement();
			st.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	//m�todo que borra todos los datos de la tabla asignaturas

	public void limpiaDatosTablaBD () {
		String query = "delete from DBO.ASIGNATURAS";
		Statement st = null;
		
		try {
			 st = conn.createStatement();
			st.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	}

