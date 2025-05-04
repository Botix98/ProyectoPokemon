package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import model.Objeto;

public class ObjetoDAO {
    
    // Método para cargar todos los objetos (select)
    public static LinkedList<Objeto> cargarObjetos(Connection con) {
        LinkedList<Objeto> listaObjeto = new LinkedList<>();
        String query = "SELECT * FROM OBJETO";
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                Objeto objeto = new Objeto();
                objeto.setIdObjeto(rs.getInt("ID_OBJETO"));
                objeto.setNomObjeto(rs.getString("NOM_OBJETO"));
                objeto.setAtaque(rs.getInt("ATAQUE"));
                objeto.setAtaqueEsp(rs.getInt("AT_ESPECIAL"));
                objeto.setDefensa(rs.getInt("DEFENSA"));
                objeto.setDefensaEsp(rs.getInt("DEF_ESPECIAL"));
                objeto.setVelocidad(rs.getInt("VELOCIDAD"));
                objeto.setPrecio(rs.getInt("PRECIO"));
                
                listaObjeto.add(objeto);
            }
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
        }
        
        return listaObjeto;
    }

    // Método para buscar un objeto por su ID (select)
    public static Objeto buscarObjetoPorId(Connection con, int idObjeto) {
        Objeto objeto = null;
        String query = "SELECT * FROM OBJETO WHERE ID_OBJETO = ?";
        
        try (
        	PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idObjeto);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                objeto = new Objeto();
                objeto.setIdObjeto(rs.getInt("ID_OBJETO"));
                objeto.setNomObjeto(rs.getString("NOM_OBJETO"));
                objeto.setAtaque(rs.getInt("ATAQUE"));
                objeto.setAtaqueEsp(rs.getInt("AT_ESPECIAL"));
                objeto.setDefensa(rs.getInt("DEFENSA"));
                objeto.setDefensaEsp(rs.getInt("DEF_ESPECIAL"));
                objeto.setVelocidad(rs.getInt("VELOCIDAD"));
                objeto.setPrecio(rs.getInt("PRECIO"));
            }
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
        }
        
        return objeto;
    }

    // Método para insertar un objeto (insert)
    public static boolean insertarObjeto(Connection con, Objeto objeto) {
        String query = "INSERT INTO OBJETO (NOM_OBJETO, ATAQUE, AT_ESPECIAL, DEFENSA, DEF_ESPECIAL, VELOCIDAD, PRECIO) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (
        	PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, objeto.getNomObjeto());
            ps.setInt(2, objeto.getAtaque());
            ps.setInt(3, objeto.getAtaqueEsp());
            ps.setInt(4, objeto.getDefensa());
            ps.setInt(5, objeto.getDefensaEsp());
            ps.setInt(6, objeto.getVelocidad());
            ps.setInt(7, objeto.getPrecio());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
            return false;
        }
    }

    // Método para actualizar un objeto (update)
    public static boolean actualizarObjeto(Connection con, Objeto objeto) {
        String query = "UPDATE OBJETO SET NOM_OBJETO = ?, ATAQUE = ?, AT_ESPECIAL = ?, DEFENSA = ?, DEF_ESPECIAL = ?, "
                     + "VELOCIDAD = ?, PRECIO = ? WHERE ID_OBJETO = ?";
        
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, objeto.getNomObjeto());
            ps.setInt(2, objeto.getAtaque());
            ps.setInt(3, objeto.getAtaqueEsp());
            ps.setInt(4, objeto.getDefensa());
            ps.setInt(5, objeto.getDefensaEsp());
            ps.setInt(6, objeto.getVelocidad());
            ps.setInt(7, objeto.getPrecio());
            ps.setInt(8, objeto.getIdObjeto());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
            return false;
        }
    }

    // Método para eliminar un objeto (delete)
    public static boolean eliminarObjeto(Connection con, int idObjeto) {
        String query = "DELETE FROM OBJETO WHERE ID_OBJETO = ?";
        
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idObjeto);
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
            return false;
        }
    }
}
