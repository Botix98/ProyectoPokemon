package model;

public class Pokemon {
	private int idPokemon;
	private int idEntrenador;
	private int idRival;
	private String tipoPropietario;
	private int numPokedex;
	private String mote;
	private int vitalidadMax;
	private int vitalidadAct;
	private int ataque;
	private int atEspecial;
	private int defensa;
	private int defEspecial;
	private int velocidad;
	private int nivel;
	private int fertilidad;
	private String sexo;
	private String estado;
	private int equipo;
	
	public Pokemon() {
		this.idPokemon = 0;
		this.idEntrenador = 0;
		this.idRival = 0;
		this.tipoPropietario = "";
		this.numPokedex = 0;
		this.mote = "";
		this.vitalidadMax = 0;
		this.vitalidadAct = 0;
		this.ataque = 0;
		this.atEspecial = 0;
		this.defensa = 0;
		this.defEspecial = 0;
		this.velocidad = 0;
		this.nivel = 0;
		this.fertilidad = 0;
		this.sexo = "";
		this.estado = "";
		this.equipo = 0;
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
	 * @return the numPokedex
	 */
	public int getNumPokedex() {
		return numPokedex;
	}

	/**
	 * @param numPokedex the numPokedex to set
	 */
	public void setNumPokedex(int numPokedex) {
		this.numPokedex = numPokedex;
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
	 * @return the vitalidadMax
	 */
	public int getVitalidadMax() {
		return vitalidadMax;
	}

	/**
	 * @param vitalidadMax the vitalidadMax to set
	 */
	public void setVitalidadMax(int vitalidadMax) {
		this.vitalidadMax = vitalidadMax;
	}

	/**
	 * @return the vitalidadAct
	 */
	public int getVitalidadAct() {
		return vitalidadAct;
	}

	/**
	 * @param vitalidadAct the vitalidadAct to set
	 */
	public void setVitalidadAct(int vitalidadAct) {
		this.vitalidadAct = vitalidadAct;
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
	 * @return the atEspecial
	 */
	public int getAtEspecial() {
		return atEspecial;
	}

	/**
	 * @param atEspecial the atEspecial to set
	 */
	public void setAtEspecial(int atEspecial) {
		this.atEspecial = atEspecial;
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
	 * @return the defEspecial
	 */
	public int getDefEspecial() {
		return defEspecial;
	}

	/**
	 * @param defEspecial the defEspecial to set
	 */
	public void setDefEspecial(int defEspecial) {
		this.defEspecial = defEspecial;
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
	 * @return the tipoPropietario
	 */
	public String getTipoPropietario() {
		return tipoPropietario;
	}

	/**
	 * @param tipoPropietario the tipoPropietario to set
	 */
	public void setTipoPropietario(String tipoPropietario) {
		this.tipoPropietario = tipoPropietario;
	}

	@Override
	public String toString() {
		return "Pokemon [idPokemon=" + idPokemon + ", idEntrenador=" + idEntrenador + ", idRival=" + idRival
				+ ", tipoPropietario=" + tipoPropietario + ", numPokedex=" + numPokedex + ", mote=" + mote
				+ ", vitalidadMax=" + vitalidadMax + ", vitalidadAct=" + vitalidadAct + ", ataque=" + ataque
				+ ", atEspecial=" + atEspecial + ", defensa=" + defensa + ", defEspecial=" + defEspecial
				+ ", velocidad=" + velocidad + ", nivel=" + nivel + ", fertilidad=" + fertilidad + ", sexo=" + sexo
				+ ", estado=" + estado + ", equipo=" + equipo + "]";
	}
}
