package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import model.Mochila;

public class MochilaDAO {

    // Obtener todos los objetos de la mochila de un entrenador (select)
    public static LinkedList<Mochila> cargarMochilaPorEntrenador(Connection con, int idEntrenador) {
        LinkedList<Mochila> listaMochila = new LinkedList<>();
        String query = "SELECT * FROM MOCHILA WHERE ID_ENTRENADOR = ?";
        
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idEntrenador);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Mochila m = new Mochila();
                m.setIdEntrenador(rs.getInt("ID_ENTRENADOR"));
                m.setIdObjeto(rs.getInt("ID_OBJETO"));
                m.setCantidad(rs.getInt("CANTIDAD"));
                
                listaMochila.add(m);
            }
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
        }

        return listaMochila;
    }

    // Insertar un objeto en la mochila (insert)
    public static boolean insertarEnMochila(Connection con, Mochila mochila) {
        String query = "INSERT INTO MOCHILA (ID_ENTRENADOR, ID_OBJETO, CANTIDAD) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, mochila.getIdEntrenador());
            ps.setInt(2, mochila.getIdObjeto());
            ps.setInt(3, mochila.getCantidad());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
            return false;
        }
    }

    // Actualizar cantidad de un objeto en la mochila (update)
    public static boolean actualizarCantidad(Connection con, Mochila mochila) {
        String query = "UPDATE MOCHILA SET CANTIDAD = ? WHERE ID_ENTRENADOR = ? AND ID_OBJETO = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, mochila.getCantidad());
            ps.setInt(2, mochila.getIdEntrenador());
            ps.setInt(3, mochila.getIdObjeto());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
            return false;
        }
    }

    // Eliminar un objeto de la mochila (delete)
    public static boolean eliminarDeMochila(Connection con, int idEntrenador, int idObjeto) {
        String query = "DELETE FROM MOCHILA WHERE ID_ENTRENADOR = ? AND ID_OBJETO = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idEntrenador);
            ps.setInt(2, idObjeto);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
            return false;
        }
    }
    
    public static Mochila buscarObjetoEnMochila(Connection con, int idEntrenador, int idObjeto) {
        Mochila mochila = null;
        String sql = "SELECT * FROM mochila WHERE id_entrenador = ? AND id_objeto = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idEntrenador);
            stmt.setInt(2, idObjeto);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                mochila = new Mochila(
                    rs.getInt("id_entrenador"),
                    rs.getInt("id_objeto"),
                    rs.getInt("cantidad")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mochila;
    }
}
