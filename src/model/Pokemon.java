package model;

public class Pokemon {
	private String nombre;
	private int idPokemon;
	private int idEntrenador;
	private String mote;
	private int vitalidadMax;
	private int vitalidadActual;
	private int ataque;
	private int ataqueEspecial;
	private int defensa;
	private int defensaEspecial;
	private int velocidad;
	private int nivel;
	private int fertilidad;
	private String sexo;
	private String estado;
	private int equipo;
	private String tipo1;
	private String tipo2;
	private Movimiento[] movimientos;
	
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
			int velocidad, int nivel, String estado, Movimiento[] listaMovimientos) {
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
		this.movimientos = new Movimiento[4];
		for (int i = 0; i < this.movimientos.length; i++) {
			this.movimientos[i] = listaMovimientos[i];
		}
	}
	
	

	/**
	 * @param nombre
	 * @param idPokemon
	 * @param idEntrenador
	 * @param mote
	 * @param vitalidadMax
	 * @param vitalidadActual
	 * @param ataque
	 * @param ataqueEspecial
	 * @param defensa
	 * @param defensaEspecial
	 * @param velocidad
	 * @param nivel
	 * @param fertilidad
	 * @param sexo
	 * @param estado
	 * @param equipo
	 * @param movimiento1
	 * @param movimiento2
	 * @param movimiento3
	 * @param movimiento4
	 * @param iD_POKEMON
	 * @param tipo1
	 * @param tipo2
	 */
	public Pokemon(String nombre, int idPokemon, int idEntrenador, String mote, int vitalidadMax,
			int ataque, int ataqueEspecial, int defensa, int defensaEspecial, int velocidad, int nivel, int fertilidad,
			String sexo, String estado, int equipo, Movimiento[] listaMovimientos, String tipo1, String tipo2) {
		super();
		this.nombre = nombre;
		this.idPokemon = idPokemon;
		this.idEntrenador = idEntrenador;
		this.mote = mote;
		this.vitalidadMax = vitalidadMax;
		this.vitalidadActual = vitalidadMax;
		this.ataque = ataque;
		this.ataqueEspecial = ataqueEspecial;
		this.defensa = defensa;
		this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad;
		this.nivel = nivel;
		this.fertilidad = fertilidad;
		this.sexo = sexo;
		this.estado = estado;
		this.equipo = equipo;
		this.movimientos = new Movimiento[4];
		for (int i = 0; i < this.movimientos.length; i++) {
			this.movimientos[i] = listaMovimientos[i];
		}
		this.tipo1 = tipo1;
		this.tipo2 = tipo2;
	}

	/**
	 * 
	 */
	public Pokemon() {
		super();
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
	 * @return the movimientos
	 */
	public Movimiento getMovimiento(int n) {
		return movimientos[n];
	}



	/**
	 * @param movimientos the movimientos to set
	 */
	public void setMovimiento(Movimiento movimiento, int n) {
		this.movimientos[n] = movimiento;
	}

	/**
	 * @return the idPokemon
	 */
	public int getIdPokemon() {
		return idPokemon;
	}

	/**
	 * @param idPokemon the idPokemon to set
	 */
	public void setIdPokemon(int idPokemon) {
		this.idPokemon = idPokemon;
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
	 * @return the mote
	 */
	public String getMote() {
		return mote;
	}

	/**
	 * @param mote the mote to set
	 */
	public void setMote(String mote) {
		this.mote = mote;
	}

	/**
	 * @return the fertilidad
	 */
	public int getFertilidad() {
		return fertilidad;
	}

	/**
	 * @param fertilidad the fertilidad to set
	 */
	public void setFertilidad(int fertilidad) {
		this.fertilidad = fertilidad;
	}

	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return the equipo
	 */
	public int getEquipo() {
		return equipo;
	}

	/**
	 * @param equipo the equipo to set
	 */
	public void setEquipo(int equipo) {
		this.equipo = equipo;
	}
	
	/**
	 * @return the tipo1
	 */
	public String getTipo1() {
		return tipo1;
	}

	/**
	 * @param tipo1 the tipo1 to set
	 */
	public void setTipo1(String tipo1) {
		this.tipo1 = tipo1;
	}

	/**
	 * @return the tipo2
	 */
	public String getTipo2() {
		return tipo2;
	}

	/**
	 * @param tipo2 the tipo2 to set
	 */
	public void setTipo2(String tipo2) {
		this.tipo2 = tipo2;
	}

	@Override
	public String toString() {
		return "Pokemon [nombre=" + nombre + ", idPokemon=" + idPokemon + ", idEntrenador=" + idEntrenador + ", mote="
				+ mote + ", vitalidadMax=" + vitalidadMax + ", vitalidadActual=" + vitalidadActual + ", ataque="
				+ ataque + ", ataqueEspecial=" + ataqueEspecial + ", defensa=" + defensa + ", defensaEspecial="
				+ defensaEspecial + ", velocidad=" + velocidad + ", nivel=" + nivel + ", fertilidad=" + fertilidad
				+ ", sexo=" + sexo + ", estado=" + estado + ", equipo=" + equipo + ", tipo1=" + tipo1 + ", tipo2="
				+ tipo2 + ", movimiento1=" + movimientos[0] + ", movimiento2=" + movimientos[1] + ", movimiento3="
				+ movimientos[2] + ", movimiento4=" + movimientos[3] + "]";
	}
}
