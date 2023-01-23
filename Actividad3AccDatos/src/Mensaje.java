/* 
 * CLC 12122022 - Clase Mensaje que nos permite guardar los datos de los mensajes en la base de datos
 */

public class Mensaje {

	// creo los atributos

	private int idMensaje, idOrigen, idDestino;

	private String texto;

	private Estado estado;

	//	creo el constructor 

	public Mensaje(int idMensaje, int idOrigen, int idDestino, String texto, Estado estado) {
		super();
		this.idMensaje = idMensaje;
		this.idOrigen = idOrigen;
		this.idDestino = idDestino;
		this.texto = texto;
		this.estado = estado;
	}

	// creo los getters y setters

	protected int getIdMensaje() {
		return idMensaje;
	}

	protected void setIdMensaje(int idMensaje) {
		this.idMensaje = idMensaje;
	}

	protected int getIdOrigen() {
		return idOrigen;
	}

	protected void setIdOrigen(int idOrigen) {
		this.idOrigen = idOrigen;
	}

	protected int getIdDestino() {
		return idDestino;
	}

	protected void setIdDestino(int idDestino) {
		this.idDestino = idDestino;
	}

	protected String getTexto() {
		return texto;
	}

	protected void setTexto(String texto) {
		this.texto = texto;
	}

	protected Estado getEstado() {
		return estado;
	}

	protected void setEstado(Estado estado) {
		this.estado = estado;
	}

}
