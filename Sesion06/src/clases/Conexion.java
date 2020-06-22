package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static final String strConexion="jdbc:sqlserver://DESKTOP-ANF9FGV;instanceName=JOABTO;databaseName=Taller";
	private static Connection cn=null;

	private Conexion() {}

	public static Connection getConexion() throws SQLException{
		if(cn==null) {
			cn=DriverManager.getConnection(strConexion, "sa", "12345");
		}
		return cn;
	}
}