package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Entrenador;

public class EntrenadorDAO {

    public static Entrenador buscarPorUsuario(Connection con, String usuario, String pass) {
        String query = "SELECT * FROM ENTRENADOR WHERE USUARIO = ? AND PASS = ?";
        Entrenador entrenador = null;

        try 
        	(PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, usuario);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                entrenador = new Entrenador();
                entrenador.setIdEntrenador(rs.getInt("ID_ENTRENADOR"));
                entrenador.setUsuario(rs.getString("USUARIO"));
                entrenador.setPass(rs.getString("PASS"));
                entrenador.setPokedolares(rs.getInt("POKEDOLARES"));
            }
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
        }

        return entrenador;
    }

    public static boolean insertarEntrenador(Connection con, Entrenador entrenador) {
        String query = "INSERT INTO ENTRENADOR (ID_ENTRENADOR, USUARIO, PASS, POKEDOLARES) VALUES (?, ?, ?, ?)";

        try
        	(PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, entrenador.getIdEntrenador());
            ps.setString(2, entrenador.getUsuario());
            ps.setString(3, entrenador.getPass());
            ps.setInt(4, entrenador.getPokedolares());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
            return false;
        }
    }

    public static boolean actualizarPokedolares(Connection con, int idEntrenador, int nuevosPokedolares) {
        String query = "UPDATE ENTRENADOR SET POKEDOLARES = ? WHERE ID_ENTRENADOR = ?";

        try
        	(PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, nuevosPokedolares);
            ps.setInt(2, idEntrenador);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
            return false;
        }
    }

    public static boolean eliminarEntrenador(Connection con, int idEntrenador) {
        String query = "DELETE FROM ENTRENADOR WHERE ID_ENTRENADOR = ?";

        try
        	(PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idEntrenador);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
            return false;
        }
    }
    
    	public static boolean insertarPokemonEnCaja(Connection con, int idEntrenador, String nombre, int nivel) {
            String sql = "INSERT INTO Caja (idEntrenador, nombrePokemon, nivel) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, idEntrenador);
                stmt.setString(2, nombre);
                stmt.setInt(3, nivel);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } 
    	
    	public static boolean actualizarPPMovimiento(Connection con, int idPokemon, int idMovimiento, int nuevosPP) {
    		        String query = "UPDATE MOVIMIENTO_POKEMON SET PP_ACTUALES = ? WHERE ID_POKEMON = ? AND ID_MOVIMIENTO = ?";

    		        try (PreparedStatement ps = con.prepareStatement(query)) {
    		            ps.setInt(1, nuevosPP);
    		            ps.setInt(2, idPokemon);
    		            ps.setInt(3, idMovimiento);
    		            return ps.executeUpdate() > 0;
    		        } catch (SQLException e) {
    		            ConexionBD.printSQLException(e);
    		            return false;
    		        }
    		    }
}

