package model;

public class Movimiento {
	
	

	private String nombre;
	private int IdMovimiento;
	private int NivelAprendizaje;
	private int ppMax;
	private int ppActuales;
	private String tipoMov;
	private int potencia;
	private String tipo;
	private String estado;
	private int turnos;
	private String mejora;
	private int probabilidad;
	
	public Movimiento() {
	    this.nombre = "";
	    this.IdMovimiento = 0;
	    this.NivelAprendizaje = 0;
	    this.ppMax = 0;
	    this.ppActuales = 0;
	    this.tipoMov = "";
	    this.potencia = 0;
	    this.tipo = "";
	    this.estado = "";
	    this.turnos = 0;
	    this.mejora = "";
	    this.probabilidad = 0;
	}
	
	/**
	 * @param potencia
	 */
	
	public Movimiento(int potencia) {
	    this.nombre = "";
	    this.IdMovimiento = 0;
	    this.NivelAprendizaje = 0;
	    this.ppMax = 0;
	    this.ppActuales = 0;
	    this.tipoMov = "";
	    this.potencia = potencia;
	    this.tipo = "";
	    this.estado = "";
	    this.turnos = 0;
	    this.mejora = "";
	    this.probabilidad = 0;
	}

	public Movimiento(String nombre, int idMovimiento, int nivelAprendizaje, int ppMax, int ppActuales, String tipoMov,
			int potencia, String tipo, String estado, int turnos, String mejora, int probabilidad) {
		super();
		this.nombre = nombre;
		IdMovimiento = idMovimiento;
		NivelAprendizaje = nivelAprendizaje;
		this.ppMax = ppMax;
		this.ppActuales = ppActuales;
		this.tipoMov = tipoMov;
		this.potencia = potencia;
		this.tipo = tipo;
		this.estado = estado;
		this.turnos = turnos;
		this.mejora = mejora;
		this.probabilidad = probabilidad;
	}
	
	public Movimiento(Movimiento m) {
		super();
		this.nombre = m.nombre;
		this.IdMovimiento = m.IdMovimiento;
		this.NivelAprendizaje = m.NivelAprendizaje;
		this.ppMax = m.ppMax;
		this.ppActuales = m.ppMax;
		this.tipoMov = m.tipoMov;
		this.potencia = m.potencia;
		this.tipo = m.tipo;
		this.estado = m.estado;
		this.turnos = m.turnos;
		this.mejora = m.mejora;
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

	public int getIdMovimiento() {
		return IdMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		IdMovimiento = idMovimiento;
	}

	public int getNivelAprendizaje() {
		return NivelAprendizaje;
	}

	public void setNivelAprendizaje(int NivelAprendizaje) {
		this.NivelAprendizaje = NivelAprendizaje;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMejora() {
		return mejora;
	}

	public void setMejora(String mejora) {
		this.mejora = mejora;
	}
	
	@Override
	public String toString() {
		return "Movimiento [nombre=" + nombre + ", IdMovimiento=" + IdMovimiento + ", NivelAprendizaje="
				+ NivelAprendizaje + ", ppMax=" + ppMax + ", ppActuales=" + ppActuales + ", tipoMov=" + tipoMov
				+ ", potencia=" + potencia + ", tipo=" + tipo + ", estado=" + estado + ", turnos=" + turnos
				+ ", mejora=" + mejora + ", probabilidad=" + probabilidad + "]";
	}

	
}
