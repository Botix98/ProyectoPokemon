package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import model.Pokemon;

public class PokemonDAO {
	public static LinkedList<Pokemon> cargarPokemonEntrenador(Connection con, int idEntrenador) {
		LinkedList<Pokemon> listaPokemon = new LinkedList<>();
		
		String query = "SELECT * FROM POKEMON"
					+ "	WHERE ID_ENTRENADOR = ?";

		try {
			PreparedStatement pt = con.prepareStatement(query);
			pt.setInt(1, idEntrenador);
			ResultSet rs = pt.executeQuery();

			Pokemon pokemon;
			
			while (rs.next()) {
				pokemon = new Pokemon();
				pokemon.setIdPokemon(rs.getInt("ID_POKEMON"));
				pokemon.setIdEntrenador(rs.getInt("ID_ENTRENADOR"));
				pokemon.setNumPokedex(rs.getInt("NUM_POKEDEX"));
				pokemon.setMote(rs.getString("MOTE"));
				pokemon.setVitalidadMax(rs.getInt("VITALIDAD_MAX"));
				pokemon.setVitalidadAct(rs.getInt("VITALIDAD_ACT"));
				pokemon.setAtaque(rs.getInt("ATAQUE"));
				pokemon.setAtEspecial(rs.getInt("AT_ESPECIAL"));
				pokemon.setDefensa(rs.getInt("DEFENSA"));
				pokemon.setDefEspecial(rs.getInt("DEF_ESPECIAL"));
				pokemon.setVelocidad(rs.getInt("VELOCIDAD"));
				pokemon.setNivel(rs.getInt("NIVEL"));
				pokemon.setFertilidad(rs.getInt("FERTILIDAD"));
				pokemon.setSexo(rs.getString("SEXO"));
				pokemon.setEstado(rs.getString("ESTADO"));
				pokemon.setEquipo(rs.getInt("EQUIPO"));
				
				listaPokemon.add(pokemon);
			}
		} catch (SQLException e) {
			ConexionBD.printSQLException(e);
		}

		return listaPokemon;
	}
	
	public static LinkedList<Pokemon> cargarPokemonEntrenadorPorSexo(Connection con, int idEntrenador, String sexo) {
		LinkedList<Pokemon> listaPokemon = new LinkedList<>();
		
		String query = "SELECT * FROM POKEMON"
					+ "	WHERE ID_ENTRENADOR = ?"
					+ " AND SEXO = ?";

		try {
			PreparedStatement pt = con.prepareStatement(query);
			pt.setInt(1, idEntrenador);
			pt.setString(2, sexo);
			ResultSet rs = pt.executeQuery();

			Pokemon pokemon;
			
			while (rs.next()) {
				pokemon = new Pokemon();
				pokemon.setIdPokemon(rs.getInt("ID_POKEMON"));
				pokemon.setIdEntrenador(rs.getInt("ID_ENTRENADOR"));
				pokemon.setNumPokedex(rs.getInt("NUM_POKEDEX"));
				pokemon.setMote(rs.getString("MOTE"));
				pokemon.setVitalidadMax(rs.getInt("VITALIDAD_MAX"));
				pokemon.setVitalidadAct(rs.getInt("VITALIDAD_ACT"));
				pokemon.setAtaque(rs.getInt("ATAQUE"));
				pokemon.setAtEspecial(rs.getInt("AT_ESPECIAL"));
				pokemon.setDefensa(rs.getInt("DEFENSA"));
				pokemon.setDefEspecial(rs.getInt("DEF_ESPECIAL"));
				pokemon.setVelocidad(rs.getInt("VELOCIDAD"));
				pokemon.setNivel(rs.getInt("NIVEL"));
				pokemon.setFertilidad(rs.getInt("FERTILIDAD"));
				pokemon.setSexo(rs.getString("SEXO"));
				pokemon.setEstado(rs.getString("ESTADO"));
				pokemon.setEquipo(rs.getInt("EQUIPO"));
				
				listaPokemon.add(pokemon);
			}
		} catch (SQLException e) {
			ConexionBD.printSQLException(e);
		}

		return listaPokemon;
	}
	
	public static LinkedList<Pokemon> cargarPokemonEquipoEntrenador(Connection con, int idEntrenador, int equipo) {
		LinkedList<Pokemon> listaPokemon = new LinkedList<>();
		
		String query = "SELECT * FROM POKEMON"
					+ "	WHERE ID_ENTRENADOR = ?"
					+ " AND EQUIPO = ?";
		
		try {
			PreparedStatement pt = con.prepareStatement(query);
			pt.setInt(1, idEntrenador);
			pt.setInt(2, equipo);
			ResultSet rs = pt.executeQuery();

			Pokemon pokemon;
			
			while (rs.next()) {
				pokemon = new Pokemon();
				pokemon.setIdPokemon(rs.getInt("ID_POKEMON"));
				pokemon.setIdEntrenador(rs.getInt("ID_ENTRENADOR"));
				pokemon.setNumPokedex(rs.getInt("NUM_POKEDEX"));
				pokemon.setMote(rs.getString("MOTE"));
				pokemon.setVitalidadMax(rs.getInt("VITALIDAD_MAX"));
				pokemon.setVitalidadAct(rs.getInt("VITALIDAD_ACT"));
				pokemon.setAtaque(rs.getInt("ATAQUE"));
				pokemon.setAtEspecial(rs.getInt("AT_ESPECIAL"));
				pokemon.setDefensa(rs.getInt("DEFENSA"));
				pokemon.setDefEspecial(rs.getInt("DEF_ESPECIAL"));
				pokemon.setVelocidad(rs.getInt("VELOCIDAD"));
				pokemon.setNivel(rs.getInt("NIVEL"));
				pokemon.setFertilidad(rs.getInt("FERTILIDAD"));
				pokemon.setSexo(rs.getString("SEXO"));
				pokemon.setEstado(rs.getString("ESTADO"));
				pokemon.setEquipo(rs.getInt("EQUIPO"));
				
				listaPokemon.add(pokemon);
			}
		} catch (SQLException e) {
			ConexionBD.printSQLException(e);
		}

		return listaPokemon;
	}
	
	public static LinkedList<Pokemon> cargarPokemonEquipoRival(Connection con, int idRival) {
		LinkedList<Pokemon> listaPokemon = new LinkedList<>();
		
		String query = "SELECT * FROM POKEMON"
					+ "	WHERE ID_RIVAL = ?";
		
		try {
			PreparedStatement pt = con.prepareStatement(query);
			pt.setInt(1, idRival);
			ResultSet rs = pt.executeQuery();

			Pokemon pokemon;
			
			while (rs.next()) {
				pokemon = new Pokemon();
				pokemon.setIdPokemon(rs.getInt("ID_POKEMON"));
				pokemon.setIdRival(rs.getInt("ID_RIVAL"));
				pokemon.setNumPokedex(rs.getInt("NUM_POKEDEX"));
				pokemon.setMote(rs.getString("MOTE"));
				pokemon.setVitalidadMax(rs.getInt("VITALIDAD_MAX"));
				pokemon.setVitalidadAct(rs.getInt("VITALIDAD_ACT"));
				pokemon.setAtaque(rs.getInt("ATAQUE"));
				pokemon.setAtEspecial(rs.getInt("AT_ESPECIAL"));
				pokemon.setDefensa(rs.getInt("DEFENSA"));
				pokemon.setDefEspecial(rs.getInt("DEF_ESPECIAL"));
				pokemon.setVelocidad(rs.getInt("VELOCIDAD"));
				pokemon.setNivel(rs.getInt("NIVEL"));
				pokemon.setFertilidad(rs.getInt("FERTILIDAD"));
				pokemon.setSexo(rs.getString("SEXO"));
				pokemon.setEstado(rs.getString("ESTADO"));
				pokemon.setEquipo(rs.getInt("EQUIPO"));
				
				listaPokemon.add(pokemon);
			}
		} catch (SQLException e) {
			ConexionBD.printSQLException(e);
		}

		return listaPokemon;
	}
	
	public static Pokemon cargarPokemonEntrenadorPorId(Connection con, int idEntrenador, int idPokemon) {
		String query = "SELECT * FROM POKEMON"
					+ "	WHERE ID_ENTRENADOR = ?"
					+ " AND ID_POKEMON = ?";
		Pokemon pokemon = new Pokemon();
		
		try {
			PreparedStatement pt = con.prepareStatement(query);
			pt.setInt(1, idEntrenador);
			pt.setInt(2, idPokemon);
			ResultSet rs = pt.executeQuery();

			if (rs.next()) {
				pokemon = new Pokemon();
				pokemon.setIdPokemon(rs.getInt("ID_POKEMON"));
				pokemon.setIdEntrenador(rs.getInt("ID_ENTRENADOR"));
				pokemon.setNumPokedex(rs.getInt("NUM_POKEDEX"));
				pokemon.setMote(rs.getString("MOTE"));
				pokemon.setVitalidadMax(rs.getInt("VITALIDAD_MAX"));
				pokemon.setVitalidadAct(rs.getInt("VITALIDAD_ACT"));
				pokemon.setAtaque(rs.getInt("ATAQUE"));
				pokemon.setAtEspecial(rs.getInt("AT_ESPECIAL"));
				pokemon.setDefensa(rs.getInt("DEFENSA"));
				pokemon.setDefEspecial(rs.getInt("DEF_ESPECIAL"));
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
		String query = "INSERT INTO POKEMON(ID_POKEMON, ID_ENTRENADOR, NUM_POKEDEX, MOTE, VITALIDAD_MAX, VITALIDAD_ACT, "
				+ "ATAQUE, AT_ESPECIAL, DEFENSA, DEF_ESPECIAL, VELOCIDAD, NIVEL, FERTILIDAD, SEXO, ESTADO, EQUIPO, TIPO_PROPIETARIO) "
				+ "VALUES(? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?, ?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, pokemon.getIdPokemon());
			ps.setInt(2, pokemon.getIdEntrenador());
			ps.setInt(3, pokemon.getNumPokedex());
			ps.setString(4, pokemon.getMote());
			ps.setInt(5, pokemon.getVitalidadMax());
			ps.setInt(6, pokemon.getVitalidadAct());
			ps.setInt(7, pokemon.getAtaque());
			ps.setInt(8, pokemon.getAtEspecial());
			ps.setInt(9, pokemon.getDefensa());
			ps.setInt(10, pokemon.getDefEspecial());
			ps.setInt(11, pokemon.getVelocidad());
			ps.setInt(12, pokemon.getNivel());
			ps.setInt(13, pokemon.getFertilidad());
			ps.setString(14, pokemon.getSexo());
			ps.setString(15, pokemon.getEstado());
			ps.setInt(16, pokemon.getEquipo());
			ps.setString(17, pokemon.getTipoPropietario());

			int filasAfectadas = ps.executeUpdate();

			if (filasAfectadas > 0) {
				return true;
			}

		} catch (SQLException e) {
			ConexionBD.printSQLException(e);
		}
		return false;
	}
	
	public static boolean actualizarPokemonSubirNivel(Connection con, Pokemon pokemon) {
		String query = "UPDATE POKEMON "
						+ "SET VITALIDAD_MAX = ?, "
						+ "VITALIDAD_ACT = ?, "
						+ "ATAQUE = ?, "
						+ "AT_ESPECIAL = ?, "
						+ "DEFENSA = ?, "
						+ "DEF_ESPECIAL = ?, "
						+ "VELOCIDAD = ?, "
						+ "NIVEL = ? "
						+ "WHERE ID_POKEMON = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, pokemon.getVitalidadMax());
			ps.setInt(2, pokemon.getVitalidadAct());
			ps.setInt(3, pokemon.getAtaque());
			ps.setInt(4, pokemon.getAtEspecial());
			ps.setInt(5, pokemon.getDefensa());
			ps.setInt(6, pokemon.getDefEspecial());
			ps.setInt(7, pokemon.getVelocidad());
			ps.setInt(8, pokemon.getNivel());
			ps.setInt(9, pokemon.getIdPokemon());
			
			int filasAfectadas = ps.executeUpdate();
			
			if (filasAfectadas > 0) {
				return true;
			}
		} catch(SQLException e) {
			ConexionBD.printSQLException(e);
		}
		return false;
	}
	
	public static boolean actualizarMotePokemon(Connection con, Pokemon pokemon) {
		String query = "UPDATE POKEMON "
						+ "SET MOTE = ? "
						+ "WHERE ID_POKEMON = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, pokemon.getMote());
			ps.setInt(2, pokemon.getIdPokemon());
			
			int filasAfectadas = ps.executeUpdate();
			
			if (filasAfectadas > 0) {
				return true;
			}
		} catch(SQLException e) {
			ConexionBD.printSQLException(e);
		}
		return false;
	}
	
	public static boolean actualizarVitalidadPokemon(Connection con, Pokemon pokemon) {
		String query = "UPDATE POKEMON "
						+ "SET VITALIDAD_ACT = ? "
						+ "WHERE ID_POKEMON = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, pokemon.getVitalidadAct());
			ps.setInt(2, pokemon.getIdPokemon());
			
			int filasAfectadas = ps.executeUpdate();
			
			if (filasAfectadas > 0) {
				return true;
			}
		} catch(SQLException e) {
			ConexionBD.printSQLException(e);
		}
		return false;
	}
	
	public static boolean actualizarEstadoPokemon(Connection con, Pokemon pokemon) {
		String query = "UPDATE POKEMON "
						+ "SET ESTADO = ? "
						+ "WHERE ID_POKEMON = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, pokemon.getEstado());
			ps.setInt(2, pokemon.getIdPokemon());
			
			int filasAfectadas = ps.executeUpdate();
			
			if (filasAfectadas > 0) {
				return true;
			}
		} catch(SQLException e) {
			ConexionBD.printSQLException(e);
		}
		return false;
	}
	
	public static boolean actualizarFertilidadPokemon(Connection con, Pokemon pokemon) {
		String query = "UPDATE POKEMON "
						+ "SET FERTILIDAD = ? "
						+ "WHERE ID_POKEMON = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, pokemon.getFertilidad());
			ps.setInt(2, pokemon.getIdPokemon());
			
			int filasAfectadas = ps.executeUpdate();
			
			if (filasAfectadas > 0) {
				return true;
			}
		} catch(SQLException e) {
			ConexionBD.printSQLException(e);
		}
		return false;
	}
	
	public static boolean actualizarEquipoPokemon(Connection con, Pokemon pokemon) {
		String query = "UPDATE POKEMON "
						+ "SET EQUIPO = ? "
						+ "WHERE ID_POKEMON = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, pokemon.getEquipo());
			ps.setInt(2, pokemon.getIdPokemon());
			
			int filasAfectadas = ps.executeUpdate();
			
			if (filasAfectadas > 0) {
				return true;
			}
		} catch(SQLException e) {
			ConexionBD.printSQLException(e);
		}
		return false;
	}
	
	public static void actualizarEquipo(Connection con, int idPokemon, int nuevoEquipo) {
	    try {
	        String query = "UPDATE POKEMON SET EQUIPO = ? WHERE ID_POKEMON = ?";
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setInt(1, nuevoEquipo);
	        ps.setInt(2, idPokemon);
	        ps.executeUpdate();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
}