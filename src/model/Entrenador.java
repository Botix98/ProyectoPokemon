package model;

import java.util.Arrays;
import java.util.Iterator;

public class Entrenador {
	private String usuario;
	private String pass;
	private int pokedolares;
	private Pokemon[] equipo;
	
	/**
	 * @param usuario
	 * @param pass
	 * @param pokedolares
	 */
	public Entrenador(String usuario, String pass, int pokedolares, Pokemon[] listaPokemon) {
		super();
		this.usuario = usuario;
		this.pass = pass;
		this.pokedolares = pokedolares;
		this.equipo = new Pokemon[6];
		for (int i = 0; i < this.equipo.length; i++) {
			if (listaPokemon[i] != null) {
				this.equipo[i] = listaPokemon[i];
			}
			else {
				this.equipo[i] = null;
			}
		}
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	/**
	 * @return the pokedolares
	 */
	public int getPokedolares() {
		return pokedolares;
	}
	/**
	 * @param pokedolares the pokedolares to set
	 */
	public void setPokedolares(int pokedolares) {
		this.pokedolares = pokedolares;
	}
	
	/**
	 * @return the equipo
	 */
	public Pokemon getPokemon(int n) {
		return equipo[n];
	}
	/**
	 * @param equipo the equipo to set
	 */
	public void setPokemon(Pokemon pokemon, int n) {
		this.equipo[n] = pokemon;
	}
	
	/**
	 * @return the equipo
	 */
	public Pokemon[] getEquipo() {
		return equipo;
	}
	
	/**
	 * @param equipo the equipo to set
	 */
	public void setEquipo(Pokemon[] equipo) {
		this.equipo = equipo;
	}
	
	@Override
	public String toString() {
		return "Entrenador [usuario=" + usuario + ", pass=" + pass + ", pokedolares=" + pokedolares + ", equipo="
				+ Arrays.toString(equipo) + "]";
	}
}
