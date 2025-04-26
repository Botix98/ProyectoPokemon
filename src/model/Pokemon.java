package model;

public class Pokemon {
	private String nombre;
	private int vitalidadMax;
	private int vitalidadActual;
	private int ataque;
	private int ataqueEspecial;
	private int defensa;
	private int defensaEspecial;
	private int velocidad;
	private int nivel;
	private String estado;
	private Movimiento movimiento1;
	private Movimiento movimiento2;
	private Movimiento movimiento3;
	private Movimiento movimiento4;
	
	/**
	 * @param nombre
	 * @param vitalidad
	 * @param ataque
	 * @param ataqueEspecial
	 * @param defensa
	 * @param defensaEspecial
	 * @param velocidad
	 * @param nivel
	 * @param estado
	 */
	public Pokemon(String nombre, int vitalidadMax, int ataque, int ataqueEspecial, int defensa, int defensaEspecial,
			int velocidad, int nivel, String estado, Movimiento movimiento1, Movimiento movimiento2, Movimiento movimiento3, Movimiento movimiento4) {
		this.nombre = nombre;
		this.vitalidadMax = vitalidadMax;
		this.vitalidadActual = vitalidadMax;
		this.ataque = ataque;
		this.ataqueEspecial = ataqueEspecial;
		this.defensa = defensa;
		this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad;
		this.nivel = nivel;
		this.estado = estado;
		this.movimiento1 = movimiento1;
		this.movimiento2 = movimiento2;
		this.movimiento3 = movimiento3;
		this.movimiento4 = movimiento4;
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
	 * @return the vitalidad
	 */
	public int getVitalidadMax() {
		return vitalidadMax;
	}

	/**
	 * @param vitalidad the vitalidad to set
	 */
	public void setVitalidadMax(int vitalidadMax) {
		this.vitalidadMax = vitalidadMax;
	}

	/**
	 * @return the ataque
	 */
	public int getAtaque() {
		return ataque;
	}

	/**
	 * @param ataque the ataque to set
	 */
	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	/**
	 * @return the ataqueEspecial
	 */
	public int getAtaqueEspecial() {
		return ataqueEspecial;
	}

	/**
	 * @param ataqueEspecial the ataqueEspecial to set
	 */
	public void setAtaqueEspecial(int ataqueEspecial) {
		this.ataqueEspecial = ataqueEspecial;
	}

	/**
	 * @return the defensa
	 */
	public int getDefensa() {
		return defensa;
	}

	/**
	 * @param defensa the defensa to set
	 */
	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	/**
	 * @return the defensaEspecial
	 */
	public int getDefensaEspecial() {
		return defensaEspecial;
	}

	/**
	 * @param defensaEspecial the defensaEspecial to set
	 */
	public void setDefensaEspecial(int defensaEspecial) {
		this.defensaEspecial = defensaEspecial;
	}

	/**
	 * @return the velocidad
	 */
	public int getVelocidad() {
		return velocidad;
	}

	/**
	 * @param velocidad the velocidad to set
	 */
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	/**
	 * @return the nivel
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * @return the vitalidadActual
	 */
	public int getVitalidadActual() {
		return vitalidadActual;
	}

	/**
	 * @param vitalidadActual the vitalidadActual to set
	 */
	public void setVitalidadActual(int vitalidadActual) {
		this.vitalidadActual = vitalidadActual;
	}

	/**
	 * @return the movimiento1
	 */
	public Movimiento getMovimiento1() {
		return movimiento1;
	}

	/**
	 * @param movimiento1 the movimiento1 to set
	 */
	public void setMovimiento1(Movimiento movimiento1) {
		this.movimiento1 = movimiento1;
	}

	/**
	 * @return the movimiento2
	 */
	public Movimiento getMovimiento2() {
		return movimiento2;
	}

	/**
	 * @param movimiento2 the movimiento2 to set
	 */
	public void setMovimiento2(Movimiento movimiento2) {
		this.movimiento2 = movimiento2;
	}

	/**
	 * @return the movimiento3
	 */
	public Movimiento getMovimiento3() {
		return movimiento3;
	}

	/**
	 * @param movimiento3 the movimiento3 to set
	 */
	public void setMovimiento3(Movimiento movimiento3) {
		this.movimiento3 = movimiento3;
	}

	/**
	 * @return the movimiento4
	 */
	public Movimiento getMovimiento4() {
		return movimiento4;
	}

	/**
	 * @param movimiento4 the movimiento4 to set
	 */
	public void setMovimiento4(Movimiento movimiento4) {
		this.movimiento4 = movimiento4;
	}

	@Override
	public String toString() {
		return "Pokemon [nombre=" + nombre + ", vitalidadMax=" + vitalidadMax + ", vitalidadActual=" + vitalidadActual
				+ ", ataque=" + ataque + ", ataqueEspecial=" + ataqueEspecial + ", defensa=" + defensa
				+ ", defensaEspecial=" + defensaEspecial + ", velocidad=" + velocidad + ", nivel=" + nivel + ", estado="
				+ estado + ", movimiento1=" + movimiento1 + ", movimiento2=" + movimiento2 + ", movimiento3="
				+ movimiento3 + ", movimiento4=" + movimiento4 + "]";
	}
}
