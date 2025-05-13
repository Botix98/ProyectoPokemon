package model;

import java.io.File;

import dao.ConexionBD;
import dao.PokedexDAO;
import dao.PokemonDAO;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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
	private TipoEstados estado;
	private int equipo;
	private int experiencia;
	
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
		this.estado = null;
		this.equipo = 0;
		this.experiencia = 0;
	}
	
	/**
	 * @param idPokemon
	 * @param idEntrenador
	 * @param idRival
	 * @param tipoPropietario
	 * @param numPokedex
	 * @param mote
	 * @param vitalidadMax
	 * @param vitalidadAct
	 * @param ataque
	 * @param atEspecial
	 * @param defensa
	 * @param defEspecial
	 * @param velocidad
	 * @param nivel
	 * @param fertilidad
	 * @param sexo
	 * @param estado
	 * @param equipo
	 * @param experiencia
	 */
	public Pokemon(int idPokemon, int idEntrenador, int idRival, String tipoPropietario, int numPokedex, String mote,
			int vitalidadMax, int vitalidadAct, int ataque, int atEspecial, int defensa, int defEspecial, int velocidad,
			int nivel, int fertilidad, String sexo, String estado, int equipo, int experiencia) {
		this.idPokemon = idPokemon;
		this.idEntrenador = idEntrenador;
		this.idRival = idRival;
		this.tipoPropietario = tipoPropietario;
		this.numPokedex = numPokedex;
		this.mote = mote;
		this.vitalidadMax = vitalidadMax;
		this.vitalidadAct = vitalidadAct;
		this.ataque = ataque;
		this.atEspecial = atEspecial;
		this.defensa = defensa;
		this.defEspecial = defEspecial;
		this.velocidad = velocidad;
		this.nivel = nivel;
		this.fertilidad = fertilidad;
		this.sexo = sexo;
		this.estado = TipoEstados.valueOf(estado);
		this.equipo = equipo;
		this.experiencia = experiencia;
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
	 * @return the equipo
	 */
	public int getEquipo() {
		return equipo;
	}

	/**
	 * @return the estado
	 */
	public TipoEstados getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(TipoEstados estado) {
		this.estado = estado;
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
	
	/**
	 * @return the experiencia
	 */
	public int getExperiencia() {
		return experiencia;
	}

	/**
	 * @param experiencia the experiencia to set
	 */
	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public Double comprobarVentajaDesventaja(Movimiento movAtacante) {
		double res = 1;
		TipoPokemon tipoAtaque = TipoPokemon.valueOf(movAtacante.getTipoMov());
    	String[] tipos = PokedexDAO.cargarPorNumPokedex(ConexionBD.getConnection(), this.getNumPokedex()).getTipos();
    	
    	res = res * tipoAtaque.efectividadContra(TipoPokemon.valueOf(tipos[0]));
    	
    	if (tipos[1] != null) {
    		res = res * tipoAtaque.efectividadContra(TipoPokemon.valueOf(tipos[1]));
    	}
    	
    	System.out.println("Ventaja: " + res);
    	
    	return res;
	}
	
	

	public Image evolucionar() {
		Pokedex pokedex = PokedexDAO.cargarPorNumPokedex(ConexionBD.getConnection(), getNumPokedex());
		Pokedex pokedexNEW = PokedexDAO.cargarPorNumPokedex(ConexionBD.getConnection(), getNumPokedex() +1);
		
		setVelocidad(getVelocidad() - pokedex.getVelocidad() + pokedexNEW.getVelocidad());
		setAtaque(getAtaque() - pokedex.getAtaque() + pokedexNEW.getAtaque());
		setAtEspecial(getAtEspecial() - pokedex.getAtEspecial() + pokedexNEW.getAtEspecial());
		setDefensa(getDefensa() - pokedex.getDefensa() + pokedexNEW.getDefensa());
		setDefEspecial(getDefEspecial() - pokedex.getDefEspecial() + pokedexNEW.getDefEspecial());
		setVitalidadMax(getVitalidadMax() - pokedex.getVitalidad() + pokedexNEW.getVitalidad());
		setVitalidadAct(getVitalidadMax());
		setNumPokedex(getNumPokedex() + 1);

		return new Image(new File("./img/Pokemon/Back/" + getNumPokedex() + ".png").toURI().toString());
	}

	@Override
	public String toString() {
		return "Pokemon [idPokemon=" + idPokemon + ", idEntrenador=" + idEntrenador + ", idRival=" + idRival
				+ ", tipoPropietario=" + tipoPropietario + ", numPokedex=" + numPokedex + ", mote=" + mote
				+ ", vitalidadMax=" + vitalidadMax + ", vitalidadAct=" + vitalidadAct + ", ataque=" + ataque
				+ ", atEspecial=" + atEspecial + ", defensa=" + defensa + ", defEspecial=" + defEspecial
				+ ", velocidad=" + velocidad + ", nivel=" + nivel + ", fertilidad=" + fertilidad + ", sexo=" + sexo
				+ ", estado=" + estado + ", equipo=" + equipo + ", experiencia=" + experiencia + "]";
	}
}
