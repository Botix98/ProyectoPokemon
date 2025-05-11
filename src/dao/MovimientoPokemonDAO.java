package dao;

import java.sql.*;
import java.util.LinkedList;
import model.MovimientoPokemon;

public class MovimientoPokemonDAO {

    public static boolean insertarMovimientoPokemon(Connection con, MovimientoPokemon mp) {
        String query = "INSERT INTO MOVIMIENTO_POKEMON (ID_POKEMON, ID_MOVIMIENTO, PP_ACTUALES) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, mp.getIdPokemon());
            ps.setInt(2, mp.getIdMovimiento());
            ps.setInt(3, mp.getPpActuales());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
            return false;
        }
    }

    public static boolean eliminarMovimientoPokemon(Connection con, int idPokemon, int idMovimiento) {
        String query = "DELETE FROM MOVIMIENTO_POKEMON WHERE ID_POKEMON = ? AND ID_MOVIMIENTO = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idPokemon);
            ps.setInt(2, idMovimiento);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
            return false;
        }
    }

    public static LinkedList<MovimientoPokemon> buscarPorIdPokemon(Connection con, int idPokemon) {
        LinkedList<MovimientoPokemon> lista = new LinkedList<>();
        String query = "SELECT * FROM MOVIMIENTO_POKEMON WHERE ID_POKEMON = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idPokemon);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MovimientoPokemon mp = new MovimientoPokemon();
                mp.setIdPokemon(rs.getInt("ID_POKEMON"));
                mp.setIdMovimiento(rs.getInt("ID_MOVIMIENTO"));
                mp.setPpActuales(rs.getInt("PP_ACTUALES"));
                lista.add(mp);
            }

        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
        }

        return lista;
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