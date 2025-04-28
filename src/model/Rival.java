package model;

import java.util.Arrays;

public class Rival {
	private String nombre;
	private Pokemon[] equipo;
	
	/**
	 * @param nombre
	 * @param pokemon
	 */
	public Rival(String nombre, Pokemon listaPokemon[]) {
		super();
		this.nombre = nombre;
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
	 * @return the pokemon
	 */
	public Pokemon getPokemon(int n) {
		return equipo[n];
	}

	/**
	 * @param pokemon the pokemon to set
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
		return "Rival [nombre=" + nombre + ", equipo=" + Arrays.toString(equipo) + "]";
	}
}
