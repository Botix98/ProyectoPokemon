package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import model.Pokemon;

public class PokedexDAO {
	
	public static LinkedList<Pokemon> cargarPokedex(Connection con) {
		LinkedList<Pokemon> listaPokemon = new LinkedList<>();
		
		String query = "SELECT NUM_POKEDEX, " + "NOM_POKEMON, " + "NIVEL_EVO, " + "TIPO1, " + "TIPO2, " + "VITALIDAD, "
				+ "ATAQUE, " + "AT_ESPECIAL, " + "DEFENSA, " + "DEF_ESPECIAL, " + "VELOCIDAD "
				+ "FROM POKEDEX";
		Pokemon pokemon;

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				pokemon = new Pokemon();
				pokemon.setIdPokemon(rs.getInt("NUM_POKEDEX"));
				pokemon.setNombre(rs.getString("NOM_POKEMON"));
				pokemon.setNivel(rs.getInt("NIVEL_EVO"));
				pokemon.setTipo1(rs.getString("TIPO1"));
				pokemon.setTipo2(rs.getString("TIPO2"));
				pokemon.setVitalidadMax(rs.getInt("VITALIDAD"));
				pokemon.setVitalidadActual(pokemon.getVitalidadMax());
				pokemon.setAtaque(rs.getInt("ATAQUE"));
				pokemon.setAtaqueEspecial(rs.getInt("AT_ESPECIAL"));
				pokemon.setDefensa(rs.getInt("DEFENSA"));
				pokemon.setDefensaEspecial(rs.getInt("DEF_ESPECIAL"));
				pokemon.setVelocidad(rs.getInt("VELOCIDAD"));
				
				listaPokemon.add(pokemon);
			}
		} catch (SQLException e) {
			ConexionBD.printSQLException(e);
		}

		return listaPokemon;
	}
	
	/*public static LinkedList<Pokemon> cargarPokedex(Connection con) {
		LinkedList<Pokemon> listadoPokemon = new LinkedList<>();

		String query = "SELECT ID_POKEMON," + "ID_ENTRENADOR, " + "MOTE, " + "VITALIDAD, " + "ATAQUE, " + "AT_ESPECIAL, "
				+ "DEFENSA, " + "DEF_ESPECIAL, " + "VELOCIDAD, " + "NIVEL, " + "FERTILIDAD, " + "SEXO, " + "ESTADO, " + "EQUIPO "
				+ "FROM POKEMON";
		Pokemon pokemon;

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				pokemon = new Pokemon();
				pokemon.setIdPokemon(rs.getInt("ID_POKEMON"));
				pokemon.setIdEntrenador(rs.getInt("ID_ENTRENADOR"));
				pokemon.setMote(rs.getString("MOTE"));
				pokemon.setVitalidadMax(rs.getInt("VITALIDAD"));
				pokemon.setVitalidadActual(pokemon.getVitalidadMax());
				pokemon.setAtaque(rs.getInt("ATAQUE"));
				pokemon.setAtaqueEspecial(rs.getInt("AT_ESPECIAL"));
				pokemon.setDefensa(rs.getInt("DEFENSA"));
				pokemon.setDefensaEspecial(rs.getInt("DEF_ESPECIAL"));
				pokemon.setVelocidad(rs.getInt("VELOCIDAD"));
				pokemon.setNivel(rs.getInt("NIVEL"));
				pokemon.setFertilidad(rs.getInt("FERTILIDAD"));
				pokemon.setSexo(rs.getString("SEXO"));
				pokemon.setEstado(rs.getString("ESTADO"));
				pokemon.setEquipo(rs.getInt("EQUIPO"));

				listadoPokemon.add(pokemon);
			}
		} catch (SQLException e) {
			ConexionBD.printSQLException(e);
		}

		return listadoPokemon;
	}
	
	public static Pokemon seleccionarPokemon(Connection con, int idPokemon) {

		Pokemon pokemon = new Pokemon();

		String query = "SELECT ID_POKEMON," + "ID_ENTRENADOR, " + "MOTE, " + "VITALIDAD, " + "ATAQUE, " + "AT_ESPECIAL, "
				+ "DEFENSA, " + "DEF_ESPECIAL, " + "VELOCIDAD, " + "NIVEL, " + "FERTILIDAD, " + "SEXO, " + "ESTADO, " + "EQUIPO "
				+ "FROM POKEMON WHERE ID_POKEMON = ?";

		try {
			PreparedStatement pt = con.prepareStatement(query);
			pt.setInt(1, idPokemon);
			ResultSet rs = pt.executeQuery();

			while (rs.next()) {
				pokemon.setIdPokemon(rs.getInt("ID_POKEMON"));
				pokemon.setIdEntrenador(rs.getInt("ID_ENTRENADOR"));
				pokemon.setNombre(rs.getString("NOM_POKEMON"));
				pokemon.setMote(rs.getString("MOTE"));
				pokemon.setVitalidadMax(rs.getInt("VITALIDAD"));
				pokemon.setVitalidadActual(pokemon.getVitalidadMax());
				pokemon.setAtaque(rs.getInt("ATAQUE"));
				pokemon.setAtaqueEspecial(rs.getInt("AT_ESPECIAL"));
				pokemon.setDefensa(rs.getInt("DEFENSA"));
				pokemon.setDefensaEspecial(rs.getInt("DEF_ESPECIAL"));
				pokemon.setVelocidad(rs.getInt("VELOCIDAD"));
				pokemon.setNivel(rs.getInt("NIVEL"));
				pokemon.setFertilidad(rs.getInt("FERTILIDAD"));
				pokemon.setSexo(rs.getString("SEXO"));
				pokemon.setEstado(rs.getString("ESTADO"));
				pokemon.setEquipo(rs.getInt("EQUIPO"));
			}

		} catch (SQLException e) {
			ConexionBD.printSQLException(e);
		}

		return pokemon;
	}
	
	public static boolean anyadirPokemon(Connection con, Pokemon pokemon) {
		String query = "INSERT INTO POKEMON(ID_ENTRENADO, MOTE, VITALIDAD, ATAQUE, AT_ESPECIAL, "
				+ "DEFENSA, DEF_ESPECIAL, VELOCIDAD, NIVEL, FERTILIDAD, SEXO, ESTADO, EQUIPO) " + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, pokemon.getIdEntrenador());
			ps.setString(2, pokemon.getMote());
			ps.setInt(3, pokemon.getVitalidadMax());
			ps.setInt(4, pokemon.getAtaque());
			ps.setInt(5, pokemon.getAtaqueEspecial());
			ps.setInt(6, pokemon.getDefensa());
			ps.setInt(7, pokemon.getDefensaEspecial());
			ps.setInt(8, pokemon.getVelocidad());
			ps.setInt(9, pokemon.getNivel());
			ps.setInt(10, pokemon.getFertilidad());
			ps.setString(11, pokemon.getSexo());
			ps.setString(12, pokemon.getEstado());
			ps.setInt(13, pokemon.getEquipo());

			int filasAfectadas = ps.executeUpdate();

			if (filasAfectadas > 0) {
				return true;
			}

		} catch (SQLException e) {
			ConexionBD.printSQLException(e);
		}
		return false;
	}*/
}
