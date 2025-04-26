package model;

public class Entrenador {
	private String usuario;
	private String pass;
	private int pokedolares;
	private Pokemon pokemon;
	
	/**
	 * @param usuario
	 * @param pass
	 * @param pokedolares
	 */
	public Entrenador(String usuario, String pass, int pokedolares, Pokemon pokemon) {
		super();
		this.usuario = usuario;
		this.pass = pass;
		this.pokedolares = pokedolares;
		this.pokemon = pokemon;
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
		return "Entrenador [usuario=" + usuario + ", pass=" + pass + ", pokedolares=" + pokedolares + ", " + pokemon.toString() + "]";
	}
	
}
