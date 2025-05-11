package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import model.Rival;

public class RivalDAO {
	public static Rival cargarRival(Connection con, int idRival) {
		
		String query = "SELECT * FROM RIVAL"
					+ "	WHERE ID_RIVAL = ?";

		Rival rival = new Rival();
		
		try {
			PreparedStatement pt = con.prepareStatement(query);
			pt.setInt(1, idRival);
			ResultSet rs = pt.executeQuery();
			
			if (rs.next()) {
				rival.setNombre(rs.getString("NOMBRE"));
				rival.setIdRival(rs.getInt("ID_RIVAL"));
				rival.setFraseVictoria(rs.getString("FRASE_VICTORIA"));
				rival.setFraseDerrota(rs.getString("FRASE_DERROTA"));
				rival.setFraseRendicion(rs.getString("FRASE_RENDICION"));
			}
		} catch (SQLException e) {
			ConexionBD.printSQLException(e);
		}

		return rival;
	}
	
public static LinkedList<Rival> cargarTodosRivales(Connection con) {
		
		String query = "SELECT * FROM RIVAL ORDER BY ID_RIVAL";

		LinkedList<Rival> rivales = new LinkedList<Rival>();
		
		try {
			Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {
				Rival rival = new Rival();
				rival.setNombre(rs.getString("NOMBRE"));
				rival.setIdRival(rs.getInt("ID_RIVAL"));
				rival.setFraseVictoria(rs.getString("FRASE_VICTORIA"));
				rival.setFraseDerrota(rs.getString("FRASE_DERROTA"));
				rival.setFraseRendicion(rs.getString("FRASE_RENDICION"));
				
				rivales.add(rival);
			}
		} catch (SQLException e) {
			ConexionBD.printSQLException(e);
		}

		return rivales;
	}
}
