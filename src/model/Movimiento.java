package model;

public class Movimiento {
	private String nombre;
	private int ppMax;
	private int ppActuales;
	private String tipoMov;
	private int potencia;
	private String tipo;
	private int turnos;
	private int probabilidad;
	/**
	 * @param nombre
	 * @param ppMax
	 * @param ppActuales
	 * @param tipoMov
	 * @param potencia
	 * @param tipo
	 * @param turnos
	 * @param probabilidad
	 */
	public Movimiento(String nombre, int ppMax, String tipoMov, int potencia, String tipo, int turnos,
			int probabilidad) {
		super();
		this.nombre = nombre;
		this.ppMax = ppMax;
		this.ppActuales = ppMax;
		this.tipoMov = tipoMov;
		this.potencia = potencia;
		this.tipo = tipo;
		this.turnos = turnos;
		this.probabilidad = probabilidad;
	}
	
	public Movimiento(Movimiento m) {
		super();
		this.nombre = m.nombre;
		this.ppMax = m.ppMax;
		this.ppActuales = m.ppMax;
		this.tipoMov = m.tipoMov;
		this.potencia = m.potencia;
		this.tipo = m.tipo;
		this.turnos = m.turnos;
		this.probabilidad = m.probabilidad;
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
	 * @return the ppMax
	 */
	public int getPpMax() {
		return ppMax;
	}
	/**
	 * @param ppMax the ppMax to set
	 */
	public void setPpMax(int ppMax) {
		this.ppMax = ppMax;
	}
	/**
	 * @return the ppActuales
	 */
	public int getPpActuales() {
		return ppActuales;
	}
	/**
	 * @param ppActuales the ppActuales to set
	 */
	public void setPpActuales(int ppActuales) {
		this.ppActuales = ppActuales;
	}
	/**
	 * @return the tipoMov
	 */
	public String getTipoMov() {
		return tipoMov;
	}
	/**
	 * @param tipoMov the tipoMov to set
	 */
	public void setTipoMov(String tipoMov) {
		this.tipoMov = tipoMov;
	}
	/**
	 * @return the potencia
	 */
	public int getPotencia() {
		return potencia;
	}
	/**
	 * @param potencia the potencia to set
	 */
	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the turnos
	 */
	public int getTurnos() {
		return turnos;
	}
	/**
	 * @param turnos the turnos to set
	 */
	public void setTurnos(int turnos) {
		this.turnos = turnos;
	}
	/**
	 * @return the probabilidad
	 */
	public int getProbabilidad() {
		return probabilidad;
	}
	/**
	 * @param probabilidad the probabilidad to set
	 */
	public void setProbabilidad(int probabilidad) {
		this.probabilidad = probabilidad;
	}

	@Override
	public String toString() {
		return "Movimiento [nombre=" + nombre + ", ppMax=" + ppMax + ", ppActuales=" + ppActuales + ", tipoMov="
				+ tipoMov + ", potencia=" + potencia + ", tipo=" + tipo + ", turnos=" + turnos + ", probabilidad="
				+ probabilidad + "]";
	}
}
