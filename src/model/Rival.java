package model;

public class Rival {
	private String nombre;
	private Pokemon pokemon;
	
	/**
	 * @param nombre
	 * @param pokemon
	 */
	public Rival(String nombre, Pokemon pokemon) {
		super();
		this.nombre = nombre;
		this.pokemon = pokemon;
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
	public Pokemon getPokemon() {
		return pokemon;
	}

	/**
	 * @param pokemon the pokemon to set
	 */
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	@Override
	public String toString() {
		return "Rival [nombre=" + nombre + ", "+ pokemon.toString() + "]";
	}
}
