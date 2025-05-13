package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import model.Pokemon;
import model.TipoEstados;

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
				pokemon.setEstado(TipoEstados.valueOf(rs.getString("ESTADO").toUpperCase()));
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
				pokemon.setEstado(TipoEstados.valueOf(rs.getString("ESTADO").toUpperCase()));
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
				pokemon.setEstado(TipoEstados.valueOf(rs.getString("ESTADO").toUpperCase()));
				pokemon.setEquipo(rs.getInt("EQUIPO"));
				pokemon.setExperiencia(rs.getInt("EXPERIENCIA"));
				
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
				pokemon.setEstado(TipoEstados.valueOf(rs.getString("ESTADO").toUpperCase()));
				pokemon.setEquipo(rs.getInt("EQUIPO"));
				pokemon.setExperiencia(rs.getInt("EXPERIENCIA"));
				
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
				pokemon.setEstado(TipoEstados.valueOf(rs.getString("ESTADO").toUpperCase()));
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
			ps.setString(15, pokemon.getEstado().toString());
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
	
	public static boolean actualizarEstadisticasTrasCombate(Connection con, Pokemon pokemon) {
		String query = "UPDATE POKEMON "
				+ "SET " 
			    + "ESTADO = ?, "
			    + "VITALIDAD_ACT = ?, "
			    + "EXPERIENCIA = ? "
			    + "WHERE ID_POKEMON = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, pokemon.getEstado().toString());
			ps.setInt(2, pokemon.getVitalidadAct());
			ps.setInt(3, pokemon.getExperiencia());
			ps.setInt(4, pokemon.getIdPokemon());
			
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
			
			ps.setString(1, pokemon.getEstado().toString());
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
	
	public static boolean actualizarEquipoPokemon(Connection con, int idPokemon, int equipo) {
	    String query = "UPDATE POKEMON "
	                    + "SET EQUIPO = ? "
	                    + "WHERE ID_POKEMON = ?";

	    try {
	        if (equipo != 1 && equipo != 2) {
	            throw new IllegalArgumentException("El valor de equipo debe ser 1 para equipo o 2 para caja");
	        }

	        PreparedStatement ps = con.prepareStatement(query);
	        
	        ps.setInt(1, equipo);
	        ps.setInt(2, idPokemon);

	        int filasAfectadas = ps.executeUpdate();

	        if (filasAfectadas > 0) {
	            return true;
	        }
	    } catch(SQLException e) {
	        ConexionBD.printSQLException(e);
	    } catch(IllegalArgumentException e) {
	        System.err.println(e.getMessage());
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
	
	public static int obtenerMaxIdPokemon(Connection con) {
	    int maxId = 0;
	    String query = "SELECT MAX(ID_POKEMON) AS MAX_ID FROM POKEMON";

	    try (PreparedStatement ps = con.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {

	        if (rs.next()) {
	            maxId = rs.getInt("MAX_ID");
	        }

	    } catch (SQLException e) {
	        ConexionBD.printSQLException(e);
	    }

	    return maxId;
	}
	
	public static int contarPokemonsEnEquipo(Connection con, int idEntrenador) {
	    int total = 0;
	    String query = "SELECT COUNT(*) AS total FROM POKEMON WHERE ID_ENTRENADOR = ? AND EQUIPO IS NOT NULL";

	    try (PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setInt(1, idEntrenador);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                total = rs.getInt("total");
	            }
	        }
	    } catch (SQLException e) {
	        ConexionBD.printSQLException(e);
	    }

	    return total;
	}


public static int contarSoloPokemonsEnEquipo(Connection con, int idEntrenador) {
    int total = 0;
    String query = "SELECT COUNT(*) AS total FROM POKEMON WHERE ID_ENTRENADOR = ? AND EQUIPO = 1";

    try (PreparedStatement ps = con.prepareStatement(query)) {
        ps.setInt(1, idEntrenador);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                total = rs.getInt("total");
            }
        }
    } catch (SQLException e) {
        ConexionBD.printSQLException(e);
    }

    return total;
	}

public static boolean eliminarPokemon(Connection con, int idPokemon) {
    String query = "DELETE FROM POKEMON WHERE ID_POKEMON = ?";
    
    try {
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, idPokemon);
        
        int filasAfectadas = ps.executeUpdate();
        
        return filasAfectadas > 0;
    } catch (SQLException e) {
        ConexionBD.printSQLException(e);
    }
    
    return false;
	}

	public static boolean cajaLlena(Connection con, int idEntrenador) {
    List<Pokemon> caja = cargarPokemonEquipoEntrenador(con, idEntrenador, 2);
    return caja.size() >= 30;
	}

	public static int contarSoloPokemonsEnCaja(Connection con, int idEntrenador) {
		int total = 0;
	    String query = "SELECT COUNT(*) AS total FROM POKEMON WHERE ID_ENTRENADOR = ? AND EQUIPO = 2";

	    try (PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setInt(1, idEntrenador);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                total = rs.getInt("total");
	            }
	        }
	    } catch (SQLException e) {
	        ConexionBD.printSQLException(e);
	    }

	    return total;
		}
}
