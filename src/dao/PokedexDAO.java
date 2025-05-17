package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import model.Pokedex;
import model.Pokemon;

public class PokedexDAO {
	
	public static LinkedList<Pokedex> cargarPokedexCompleta(Connection con) {
		LinkedList<Pokedex> pokedex = new LinkedList<>();
		
		String query = "SELECT * FROM POKEDEX ORDER BY NUM_POKEDEX";
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			recorrerRS(pokedex, rs);
		} catch (SQLException e) {
			ConexionBD.printSQLException(e);
		}

		return pokedex;
	}
	
	public static LinkedList<Pokedex> cargarPokedexEntreNumsPokedex(Connection con, int num1, int num2) {
		LinkedList<Pokedex> pokedex = new LinkedList<>();
		
		String query = "SELECT * FROM POKEDEX WHERE NUM_POKEDEX BETWEEN ? AND ? ORDER BY NUM_POKEDEX";
		
		try {
			PreparedStatement pt = con.prepareStatement(query);
			pt.setInt(1, num1);
			pt.setInt(2, num2);
			ResultSet rs = pt.executeQuery();

			recorrerRS(pokedex, rs);
		} catch (SQLException e) {
			ConexionBD.printSQLException(e);
		}

		return pokedex;
	}
	
	public static Pokedex cargarPorNumPokedex(Connection con, int numPokedex) {
		
		String query = "SELECT NUM_POKEDEX, "
							+ "NOM_POKEMON, "
							+ "NIVEL_EVO, "
							+ "TIPO1, "
							+ "TIPO2, "
							+ "VITALIDAD, "
							+ "ATAQUE, "
							+ "AT_ESPECIAL, "
							+ "DEFENSA, "
							+ "DEF_ESPECIAL, "
							+ "VELOCIDAD "
						+ "FROM POKEDEX "
						+ "WHERE NUM_POKEDEX = ? ";
		
		Pokedex pokedex = new Pokedex();

		try {
			PreparedStatement pt = con.prepareStatement(query);
			pt.setInt(1, numPokedex);
			ResultSet rs = pt.executeQuery();
			
			if (rs.next()) {
				pokedex.setNumPokedex(rs.getInt("NUM_POKEDEX"));
				pokedex.setNomPokemon(rs.getString("NOM_POKEMON"));
				pokedex.setNivelEvo(rs.getInt("NIVEL_EVO"));
				pokedex.setTipo(0, rs.getString("TIPO1"));
				pokedex.setTipo(1, rs.getString("TIPO2"));
				pokedex.setVitalidad(rs.getInt("VITALIDAD"));
				pokedex.setAtaque(rs.getInt("ATAQUE"));
				pokedex.setAtEspecial(rs.getInt("AT_ESPECIAL"));
				pokedex.setDefensa(rs.getInt("DEFENSA"));
				pokedex.setDefEspecial(rs.getInt("DEF_ESPECIAL"));
				pokedex.setVelocidad(rs.getInt("VELOCIDAD"));
			}

		} catch (SQLException e) {
			ConexionBD.printSQLException(e);
		}

		return pokedex;
	}
	
	public static LinkedList<Pokedex> cargarPorUnTipo(Connection con, String tipo) {
		LinkedList<Pokedex> pokedex = new LinkedList<>();
		
		String query = "SELECT NUM_POKEDEX, "
							+ "NOM_POKEMON, "
							+ "NIVEL_EVO, "
							+ "TIPO1, "
							+ "TIPO2, "
							+ "VITALIDAD, "
							+ "ATAQUE, "
							+ "AT_ESPECIAL, "
							+ "DEFENSA, "
							+ "DEF_ESPECIAL, "
							+ "VELOCIDAD "
						+ "FROM POKEDEX "
						+ "WHERE TIPO1 = ? "
						+ "OR TIPO2 = ?";

		try {
			PreparedStatement pt = con.prepareStatement(query);
			pt.setString(1, tipo);
			pt.setString(2, tipo);
			ResultSet rs = pt.executeQuery();

			recorrerRS(pokedex, rs);
		} catch (SQLException e) {
			ConexionBD.printSQLException(e);
		}

		return pokedex;
	}
	
	public static LinkedList<Pokedex> cargarPorVariosTipos(Connection con, String tipo1, String tipo2) {
		LinkedList<Pokedex> pokedex = new LinkedList<>();
		
		String query = "SELECT NUM_POKEDEX, "
							+ "NOM_POKEMON, "
							+ "NIVEL_EVO, "
							+ "TIPO1, "
							+ "TIPO2, "
							+ "VITALIDAD, "
							+ "ATAQUE, "
							+ "AT_ESPECIAL, "
							+ "DEFENSA, "
							+ "DEF_ESPECIAL, "
							+ "VELOCIDAD "
						+ "FROM POKEDEX "
						+ "WHERE TIPO1 = ? "
						+ "OR TIPO2 = ? "
						+ "OR TIPO1 = ? "
						+ "OR TIPO2 = ?";
		
		Pokedex pokemon;

		try {
			PreparedStatement pt = con.prepareStatement(query);
			pt.setString(1, tipo1);
			pt.setString(2, tipo1);
			pt.setString(3, tipo2);
			pt.setString(4, tipo2);
			ResultSet rs = pt.executeQuery();

			recorrerRS(pokedex, rs);
		} catch (SQLException e) {
			ConexionBD.printSQLException(e);
		}

		return pokedex;
	}
	
	private static void recorrerRS(LinkedList<Pokedex> pokedex, ResultSet rs) throws SQLException {
		Pokedex pokemon;
		
		while (rs.next()) {
			pokemon = new Pokedex();
			pokemon.setNumPokedex(rs.getInt("NUM_POKEDEX"));
			pokemon.setNomPokemon(rs.getString("NOM_POKEMON"));
			pokemon.setNivelEvo(rs.getInt("NIVEL_EVO"));
			pokemon.setTipo(0, rs.getString("TIPO1"));
			pokemon.setTipo(1, rs.getString("TIPO2"));
			pokemon.setVitalidad(rs.getInt("VITALIDAD"));
			pokemon.setAtaque(rs.getInt("ATAQUE"));
			pokemon.setAtEspecial(rs.getInt("AT_ESPECIAL"));
			pokemon.setDefensa(rs.getInt("DEFENSA"));
			pokemon.setDefEspecial(rs.getInt("DEF_ESPECIAL"));
			pokemon.setVelocidad(rs.getInt("VELOCIDAD"));
			
			pokedex.add(pokemon);
		}
	}
}
