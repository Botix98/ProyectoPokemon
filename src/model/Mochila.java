package model;

public class Mochila {
	private int  idEntrenador;
	private int  idObjeto;
	private int  cantidad;
	
	/**
	 * @param idEntrenador
	 * @param idObjeto
	 * @param cantidad
	 */
	public Mochila(int idEntrenador, int idObjeto, int cantidad) {
		super();
		this.idEntrenador = idEntrenador;
		this.idObjeto = idObjeto;
		this.cantidad = cantidad;
	}

	public Mochila() {
		super();
		this.idEntrenador = 0;
		this.idObjeto = 0;
		this.cantidad = 0;
	}

	/**
	 * @return the idEntrenador
	 */
	public int getIdEntrenador() {
		return idEntrenador;
	}

	/**
	 * @param idEntrenador the idEntrenador to set
	 */
	public void setIdEntrenador(int idEntrenador) {
		this.idEntrenador = idEntrenador;
	}

	/**
	 * @return the idObjeto
	 */
	public int getIdObjeto() {
		return idObjeto;
	}

	/**
	 * @param idObjeto the idObjeto to set
	 */
	public void setIdObjeto(int idObjeto) {
		this.idObjeto = idObjeto;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Mochila [idEntrenador=" + idEntrenador + ", idObjeto=" + idObjeto + ", cantidad=" + cantidad + "]";
	}
	
}
