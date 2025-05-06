package model;

import java.util.Arrays;
import java.util.Iterator;

public class Entrenador {
	
	private String usuario;
	private String pass;
	private int pokedolares;
	private int IdEntrenador;
	
	/**
	 * @param usuario
	 * @param pass
	 * @param pokedolares
	 */
	
	public Entrenador() {
	    this.usuario = "";
	    this.pass = "";
	    this.pokedolares = 0;
	    this.IdEntrenador = 1;
	}
	
	public Entrenador(String usuario, String pass, int pokedolares) {
		super();
		this.usuario = usuario;
		this.pass = pass;
		this.pokedolares = pokedolares;
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
		return IdEntrenador;
	}
	public void setIdEntrenador(int idEntrenador) {
		IdEntrenador = idEntrenador;
	}
	@Override
	public String toString() {
		return "Entrenador [usuario=" + usuario + ", pass=" + pass + ", pokedolares=" + pokedolares + "]";
	}
}
