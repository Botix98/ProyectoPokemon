package dao;

import java.sql.*;
import java.util.LinkedList;
import model.Movimiento;
import model.TipoEstados;

public class MovimientoDAO {

    public static LinkedList<Movimiento> cargarTodos(Connection con) {
        LinkedList<Movimiento> lista = new LinkedList<>();
        String query = "SELECT * FROM MOVIMIENTO";

        try
        	(Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

        	while (rs.next()) {
        	    Movimiento m = new Movimiento();
        	    m.setIdMovimiento(rs.getInt("ID_MOVIMIENTO"));
        	    m.setNombre(rs.getString("NOM_MOVIMIENTO"));
        	    m.setNivelAprendizaje(rs.getInt("NIVEL_APRENDIZAJE"));
        	    m.setPpMax(rs.getInt("PP_MAX"));
        	    m.setTipo(rs.getString("TIPO"));
        	    m.setPotencia(rs.getInt("POTENCIA"));
        	    m.setTipoMov(rs.getString("TIPO_MOV"));
        	    m.setEstado(TipoEstados.valueOf(rs.getString("ESTADO")));
        	    m.setTurnos(rs.getInt("TURNOS"));
        	    m.setMejora(rs.getString("MEJORA"));
        	    m.setProbabilidad(rs.getInt("PROBABILIDAD"));
        	    m.setPpActuales(m.getPpMax());

        	    lista.add(m);
        	}
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
        }

        return lista;
    }

    public static Movimiento buscarPorId(Connection con, int id) {
        String query = "SELECT * FROM MOVIMIENTO WHERE ID_MOVIMIENTO = ?";
        Movimiento m = null;

        try
        	(PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                m = new Movimiento();
                m.setIdMovimiento(rs.getInt("ID_MOVIMIENTO"));
                m.setNombre(rs.getString("NOM_MOVIMIENTO"));
                m.setNivelAprendizaje(rs.getInt("NIVEL_APRENDIZAJE"));
                m.setPpMax(rs.getInt("PP_MAX"));
                m.setTipo(rs.getString("TIPO"));
                m.setPotencia(rs.getInt("POTENCIA"));
                m.setTipoMov(rs.getString("TIPO_MOV"));
                m.setEstado(TipoEstados.valueOf(rs.getString("ESTADO")));
                m.setTurnos(rs.getInt("TURNOS"));
                m.setMejora(rs.getString("MEJORA"));
                m.setProbabilidad(rs.getInt("PROBABILIDAD"));
                m.setPpActuales(m.getPpMax());
            }
            
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
        }

        return m;
    }
    
    public static LinkedList<Movimiento> buscarPorTipoMov(Connection con, String tipoMov) {
        LinkedList<Movimiento> lista = new LinkedList<>();
        String query = "SELECT * FROM MOVIMIENTO WHERE TIPO_MOV = ?";

        try
        	(PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, tipoMov);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Movimiento m = new Movimiento();
                m.setIdMovimiento(rs.getInt("ID_MOVIMIENTO"));
                m.setNombre(rs.getString("NOM_MOVIMIENTO"));
                m.setNivelAprendizaje(rs.getInt("NIVEL_APRENDIZAJE"));
                m.setPpMax(rs.getInt("PP_MAX"));
                m.setTipo(rs.getString("TIPO"));
                m.setPotencia(rs.getInt("POTENCIA"));
                m.setTipoMov(rs.getString("TIPO_MOV"));
                m.setEstado(TipoEstados.valueOf(rs.getString("ESTADO")));
                m.setTurnos(rs.getInt("TURNOS"));
                m.setMejora(rs.getString("MEJORA"));
                m.setProbabilidad(rs.getInt("PROBABILIDAD"));
                m.setPpActuales(m.getPpMax());

                lista.add(m);
            }
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
        }

        return lista;
    }
    
    

    public static boolean insertarMovimiento(Connection con, Movimiento m) {
        String query = "INSERT INTO MOVIMIENTO (ID_MOVIMIENTO, NOM_MOVIMIENTO, NIVEL_APRENDIZAJE, PP_MAX, TIPO, POTENCIA, TIPO_MOV, ESTADO, TURNOS, MEJORA, PROBABILIDAD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try
        	(PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, m.getIdMovimiento());
            ps.setString(2, m.getNombre());
            ps.setInt(3, m.getNivelAprendizaje());
            ps.setInt(4, m.getPpMax());
            ps.setString(5, m.getTipo());
            ps.setInt(6, m.getPotencia());
            ps.setString(7, m.getTipoMov());
            ps.setString(8, m.getEstado().toString());
            ps.setInt(9, m.getTurnos());
            ps.setString(10, m.getMejora());
            ps.setInt(11, m.getProbabilidad());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
            return false;
        }
    }

    public static boolean actualizarMovimiento(Connection con, Movimiento m) {
        String query = "UPDATE MOVIMIENTO SET NOM_MOVIMIENTO = ?, NIVEL_APRENDIZAJE = ?, PP_MAX = ?, TIPO = ?, POTENCIA = ?, TIPO_MOV = ?, ESTADO = ?, TURNOS = ?, MEJORA = ?, PROBABILIDAD = ? WHERE ID_MOVIMIENTO = ?";

        try
        	(PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getNivelAprendizaje());
            ps.setInt(3, m.getPpMax());
            ps.setString(4, m.getTipo());
            ps.setInt(5, m.getPotencia());
            ps.setString(6, m.getTipoMov());
            ps.setString(7, m.getEstado().toString());
            ps.setInt(8, m.getTurnos());
            ps.setString(9, m.getMejora());
            ps.setInt(10, m.getProbabilidad());
            ps.setInt(11, m.getIdMovimiento());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
            return false;
        }
    }

    public static boolean eliminarMovimiento(Connection con, int id) {
        String query = "DELETE FROM MOVIMIENTO WHERE ID_MOVIMIENTO = ?";

        try
        	(PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            ConexionBD.printSQLException(e);
            return false;
        }
    }
}