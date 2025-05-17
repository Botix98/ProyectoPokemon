package model;

import java.sql.Connection;
import java.util.LinkedList;

import dao.ConexionBD;
import dao.ObjetoDAO;

public class Objeto {
	
	private int idObjeto;
	private String nomObjeto;
    private int ataque;
    private int ataqueEsp;
    private int defensa;
    private int defensaEsp;
    private int velocidad;
	private int precio;
	private boolean equipable;
	
	/**
	 * @param idObjeto
	 * @param nomObjeto
	 * @param ataque
	 * @param ataqueEsp
	 * @param defensa
	 * @param defensaEsp
	 * @param velocidad
	 * @param precio
	 */
	
	public Objeto(int idObjeto, String nomObjeto, int ataque, int ataqueEsp, int defensa, int defensaEsp, int velocidad,
			int precio, boolean equipable) {
		this.idObjeto = idObjeto;
		this.nomObjeto = nomObjeto;
		this.ataque = ataque;
		this.ataqueEsp = ataqueEsp;
		this.defensa = defensa;
		this.defensaEsp = defensaEsp;
		this.velocidad = velocidad;
		this.precio = precio;
		this.equipable = equipable;
	}

	public Objeto() {
		this.idObjeto = 0;
		this.nomObjeto = "";
		this.ataque = 0;
		this.ataqueEsp = 0;
		this.defensa = 0;
		this.defensaEsp = 0;
		this.velocidad = 0;
		this.precio = 0;
		this.equipable = true;
	}
	
	public void aplicarBonificacionEstadisticas(Pokemon pokemon) {
		
		
        if (this.getAtaque() != 0) {
            pokemon.setAtaque((int) (pokemon.getAtaque() * this.getAtaque()));
        }
        if (this.getAtaqueEsp() != 0) {
            pokemon.setAtEspecial((int) (pokemon.getAtEspecial() * this.getAtaqueEsp()));
        }
        if (this.getDefensa() != 0) {
            pokemon.setDefensa((int) (pokemon.getDefensa() * this.getDefensa()));
        }
        if (this.getDefensaEsp() != 0) {
            pokemon.setDefEspecial((int) (pokemon.getDefEspecial() * this.getDefensaEsp()));
        }
        if (this.getVelocidad() != 0) {
            pokemon.setVelocidad((int) (pokemon.getVelocidad() * this.getVelocidad()));
        }
    }

    public void eliminarBonificacionEstadisticas(Pokemon pokemon) {
        if (this.getAtaque() != 0) {
            pokemon.setAtaque((int) (pokemon.getAtaque() / this.getAtaque()));
        }
        if (this.getAtaqueEsp() != 0) {
            pokemon.setAtEspecial((int) (pokemon.getAtEspecial() / this.getAtaqueEsp()));
        }
        if (this.getDefensa() != 0) {
            pokemon.setDefensa((int) (pokemon.getDefensa() / this.getDefensa()));
        }
        if (this.getDefensaEsp() != 0) {
            pokemon.setDefEspecial((int) (pokemon.getDefEspecial() / this.getDefensaEsp()));
        }
        if (this.getVelocidad() != 0) {
            pokemon.setVelocidad((int) (pokemon.getVelocidad() / this.getVelocidad()));
        }
    }

	/**
	 * @return the idObjeto
	 */
	public int getIdObjeto() {
		return idObjeto;
	}

	/**
	 * @param idObjeto the idObjeto to set
	 */
	public void setIdObjeto(int idObjeto) {
		this.idObjeto = idObjeto;
	}

	/**
	 * @return the nomObjeto
	 */
	public String getNomObjeto() {
		return nomObjeto;
	}

	/**
	 * @param nomObjeto the nomObjeto to set
	 */
	public void setNomObjeto(String nomObjeto) {
		this.nomObjeto = nomObjeto;
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
	 * @return the ataqueEsp
	 */
	public int getAtaqueEsp() {
		return ataqueEsp;
	}

	/**
	 * @param ataqueEsp the ataqueEsp to set
	 */
	public void setAtaqueEsp(int ataqueEsp) {
		this.ataqueEsp = ataqueEsp;
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
	 * @return the defensaEsp
	 */
	public int getDefensaEsp() {
		return defensaEsp;
	}

	/**
	 * @param defensaEsp the defensaEsp to set
	 */
	public void setDefensaEsp(int defensaEsp) {
		this.defensaEsp = defensaEsp;
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
	 * @return the precio
	 */
	public int getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public boolean isEquipable() {
	    return equipable;
	}

	public void setEquipable(boolean equipable) {
	    this.equipable = equipable;
	}

	@Override
	public String toString() {
		return "Objeto [idObjeto=" + idObjeto + ", nomObjeto=" + nomObjeto + ", ataque=" + ataque + ", ataqueEsp="
				+ ataqueEsp + ", defensa=" + defensa + ", defensaEsp=" + defensaEsp + ", velocidad=" + velocidad
				+ ", precio=" + precio + ", equipable=" + equipable + "]";
	}
}
