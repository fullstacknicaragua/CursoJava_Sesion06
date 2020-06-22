package ejecucion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EjemploConexion {

	public static void main(String[] args) {
		Connection cn=null;
		try {
			cn=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-ANF9FGV;instanceName=JOABTO;databaseName=Taller", "sa", "12345");
			Statement consulta=cn.createStatement();
			ResultSet resultado=consulta.executeQuery("SELECT * FROM dbo.Vehiculos");
			while(resultado.next())
			{
				int idVehiculo=resultado.getInt("idVehiculo");
				String nPlaca=resultado.getString("nPlaca");
				String marca=resultado.getString("marca");
				String modelo=resultado.getString("modelo");
				String color=resultado.getString("color");
				String propietario=resultado.getString("propietario"); 
				
				System.out.println("id: " + idVehiculo + ", placa: " + nPlaca + ", marca: " + marca + ", modelo: " + modelo + ", color: " + color + ", propietario: " + propietario);
				Thread.sleep(400);
			}
			
			consulta.close();
			resultado.close();
		}
		catch(SQLException e) {
			System.out.println("Error de conexión!");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
