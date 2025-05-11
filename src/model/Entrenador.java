package model;

public class Entrenador {
	
	private String usuario;
	private String pass;
	private int pokedolares;
	private int idEntrenador;
	private int rivalesVencidos;
	
	/**
	 * @param usuario
	 * @param pass
	 * @param pokedolares
	 * @param rivalesVencidos
	 */
	
	public Entrenador() {
	    this.usuario = "";
	    this.pass = "";
	    this.pokedolares = 0;
	    this.idEntrenador = 0;
	    this.rivalesVencidos = 0;
	}
	
	public Entrenador(String usuario, String pass, int pokedolares, int rivalesVencidos) {
		super();
		this.usuario = usuario;
		this.pass = pass;
		this.pokedolares = pokedolares;
		this.rivalesVencidos = rivalesVencidos;
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
	
	public int getIdEntrenador() {
		return idEntrenador;
	}
	public void setIdEntrenador(int idEntrenador) {
		this.idEntrenador = idEntrenador;
	}

	/**
	 * @return the rivalesVencidos
	 */
	public int getRivalesVencidos() {
		return rivalesVencidos;
	}

	/**
	 * @param rivalesVencidos the rivalesVencidos to set
	 */
	public void setRivalesVencidos(int rivalesVencidos) {
		this.rivalesVencidos = rivalesVencidos;
	}

	@Override
	public String toString() {
		return "Entrenador [usuario=" + usuario + ", pass=" + pass + ", pokedolares=" + pokedolares + ", idEntrenador="
				+ idEntrenador + ", rivalesVencidos=" + rivalesVencidos + "]";
	}
}
