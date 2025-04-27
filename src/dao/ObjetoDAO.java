package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import model.Objeto;

public class ObjetoDAO {
	
	public static LinkedList<Objeto> cargarObjeto(Connection con) {
		LinkedList<Objeto> listaObjeto = new LinkedList<>();
		
		String query = "SELECT ID_OBJETO, " + "NOM_OBJETO, " + "ATAQUE, " + "AT_ESPECIAL, " + "DEFENSA, " + "DEF_ESPECIAL, " + "VELOCIDAD, " + "PRECIO " + "FROM OBJETO";
		Objeto objeto;
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				objeto = new Objeto();
				objeto.setIdObjeto(rs.getInt("ID_OBJETO"));
				objeto.setIdObjeto(rs.getInt("NOM_OBJETO"));
				objeto.setIdObjeto(rs.getInt("ATAQUE"));
				objeto.setIdObjeto(rs.getInt("AT_ESP"));
				objeto.setIdObjeto(rs.getInt("DEFENSA"));
				objeto.setIdObjeto(rs.getInt("DEF_ESP"));
				objeto.setIdObjeto(rs.getInt("VELOCIDAD"));
				objeto.setIdObjeto(rs.getInt("PRECIO"));


				listaObjeto.add(objeto);
			}
		} catch (SQLException e) {
			ConexionBD.printSQLException(e);
		}
		
		return listaObjeto;
		
	}
}
