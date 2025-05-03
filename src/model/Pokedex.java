package model;

import java.util.Arrays;

public class Pokedex {
	 private int numPokedex;
	 private String nomPokemon;
	 private int nivelEvo;
	 private String[] tipos;
	 private int vitalidad;
	 private int ataque;
	 private int atEspecial;
	 private int defensa;
	 private int defEspecial;
	 private int velocidad;

	public Pokedex() {
		this.numPokedex = 0;
		this.nomPokemon = "";
		this.nivelEvo = 0;
		this.tipos = new String[2];
		this.vitalidad = 0;
		this.ataque = 0;
		this.atEspecial = 0;
		this.defensa = 0;
		this.defEspecial = 0;
		this.velocidad = 0;
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
	 * @return the nomPokemon
	 */
	public String getNomPokemon() {
		return nomPokemon;
	}

	/**
	 * @param nomPokemon the nomPokemon to set
	 */
	public void setNomPokemon(String nomPokemon) {
		this.nomPokemon = nomPokemon;
	}

	/**
	 * @return the nivelEvo
	 */
	public int getNivelEvo() {
		return nivelEvo;
	}

	/**
	 * @param nivelEvo the nivelEvo to set
	 */
	public void setNivelEvo(int nivelEvo) {
		this.nivelEvo = nivelEvo;
	}

	/**
	 * @return the tipos
	 */
	public String[] getTipos() {
		return tipos;
	}

	/**
	 * @param tipos the tipos to set
	 */
	public void setTipos(String[] tipos) {
		this.tipos = tipos;
	}
	
	/**
	 * @return the tipos
	 */
	public String getTipo(int n) {
		return tipos[n];
	}

	/**
	 * @param tipos the tipos to set
	 */
	public void setTipo(int n, String tipo) {
		this.tipos[n] = tipo;
	}

	/**
	 * @return the vitalidad
	 */
	public int getVitalidad() {
		return vitalidad;
	}

	/**
	 * @param vitalidad the vitalidad to set
	 */
	public void setVitalidad(int vitalidad) {
		this.vitalidad = vitalidad;
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

	@Override
	public String toString() {
		return "Pokedex [numPokedex=" + numPokedex + ", nomPokemon=" + nomPokemon + ", nivelEvo=" + nivelEvo
				+ ", tipos=" + Arrays.toString(tipos) + ", vitalidad=" + vitalidad + ", ataque=" + ataque
				+ ", atEspecial=" + atEspecial + ", defensa=" + defensa + ", defEspecial=" + defEspecial
				+ ", velocidad=" + velocidad + "]";
	}
}
