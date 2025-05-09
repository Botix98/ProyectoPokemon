package model;

public class Rival {
	private String nombre;
	private int idRival;
	private String fraseVictoria;
	private String fraseDerrota;
	private String fraseRendicion;
	
	public Rival() {
		
	}

	/**
	 * @param nombre
	 * @param idRival
	 * @param fraseVictoria
	 * @param fraseDerrota
	 * @param fraseRendicion
	 */
	public Rival(String nombre, int idRival, String fraseVictoria, String fraseDerrota, String fraseRendicion) {
		this.nombre = nombre;
		this.idRival = idRival;
		this.fraseVictoria = fraseVictoria;
		this.fraseDerrota = fraseDerrota;
		this.fraseRendicion = fraseRendicion;
	}



	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the idRival
	 */
	public int getIdRival() {
		return idRival;
	}

	/**
	 * @param idRival the idRival to set
	 */
	public void setIdRival(int idRival) {
		this.idRival = idRival;
	}

	/**
	 * @return the fraseVictoria
	 */
	public String getFraseVictoria() {
		return fraseVictoria;
	}

	/**
	 * @param fraseVictoria the fraseVictoria to set
	 */
	public void setFraseVictoria(String fraseVictoria) {
		this.fraseVictoria = fraseVictoria;
	}

	/**
	 * @return the fraseDerrota
	 */
	public String getFraseDerrota() {
		return fraseDerrota;
	}

	/**
	 * @param fraseDerrota the fraseDerrota to set
	 */
	public void setFraseDerrota(String fraseDerrota) {
		this.fraseDerrota = fraseDerrota;
	}

	/**
	 * @return the fraseRendicion
	 */
	public String getFraseRendicion() {
		return fraseRendicion;
	}

	/**
	 * @param fraseRendicion the fraseRendicion to set
	 */
	public void setFraseRendicion(String fraseRendicion) {
		this.fraseRendicion = fraseRendicion;
	}

	@Override
	public String toString() {
		return "Rival [nombre=" + nombre + ", idRival=" + idRival + ", fraseVictoria=" + fraseVictoria
				+ ", fraseDerrota=" + fraseDerrota + ", fraseRendicion=" + fraseRendicion + "]";
	}
}
