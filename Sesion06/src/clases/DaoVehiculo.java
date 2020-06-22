package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoVehiculo {
	private static DaoVehiculo miInstancia=null;
	private  Connection cn=null;

	private DaoVehiculo() throws SQLException {
		cn=Conexion.getConexion();
	}

	public static DaoVehiculo getInstancia() throws SQLException{
		if(miInstancia==null) {
			miInstancia=new DaoVehiculo();
		}
		return miInstancia;
	}
	//Método que se utiliza para obtener el listado de vehículos...
	public List<Vehiculo> listarVehiculos() throws SQLException{
		PreparedStatement consulta=cn.prepareStatement("SELECT * FROM dbo.Vehiculos");
		ResultSet resultado=consulta.executeQuery();

		List<Vehiculo> misVehiculos=null;
		while(resultado.next()) {
			if(misVehiculos==null) misVehiculos=new ArrayList<>();

			misVehiculos.add(new Vehiculo(resultado.getInt("idVehiculo"),
					resultado.getString("nPlaca"),
					resultado.getString("marca"),
					resultado.getString("modelo"),
					resultado.getString("color"),
					resultado.getString("propietario")));
		}
		consulta.close();
		resultado.close();

		return misVehiculos;
	}
	//Método que se utiliza para obtener los datos un vehículo a partir de un id...
	public Vehiculo obtenerVehiculo(int pidVehiculo) throws SQLException{
		PreparedStatement consulta=cn.prepareStatement("SELECT * FROM dbo.Vehiculos WHERE idVehiculo=?");
		consulta.setInt(1, pidVehiculo);
		ResultSet resultado=consulta.executeQuery();

		Vehiculo miVehiculo=null;
		if(resultado.next()) {
			miVehiculo=new Vehiculo(resultado.getInt("idVehiculo"),
					resultado.getString("nPlaca"),
					resultado.getString("marca"),
					resultado.getString("modelo"),
					resultado.getString("color"),
					resultado.getString("propietario"));
		}
		consulta.close();
		resultado.close();

		return miVehiculo;
	}
	//Método que se utiliza para insertar los datos de un vehículo...
	public void insertarVehiculo(Vehiculo pmiVehiculo) throws SQLException{
		PreparedStatement ps=cn.prepareStatement(
				"INSERT INTO dbo.Vehiculos (nPlaca, marca, modelo, color, propietario) VALUES(?,?,?,?,?)"
				);
		ps.setString(1, pmiVehiculo.getnPlaca());
		ps.setString(2, pmiVehiculo.getMarca());
		ps.setString(3, pmiVehiculo.getModelo());
		ps.setString(4, pmiVehiculo.getColor());
		ps.setString(5, pmiVehiculo.getPropietario());

		ps.executeUpdate();
		ps.close();
	}

	//Método que se utiliza para actualizar los datos de un vehículo...
	public void actualizarVehiculo(Vehiculo pmiVehiculo) throws SQLException{
		if(pmiVehiculo.getIdVehiculo()<=0) return;

		PreparedStatement ps=cn.prepareStatement("UPDATE dbo.Vehiculos SET nPlaca=?, marca=?, modelo=?, color=?, propietario=? WHERE idVehiculo=?");
		ps.setString(1, pmiVehiculo.getnPlaca());
		ps.setString(2, pmiVehiculo.getMarca());
		ps.setString(3, pmiVehiculo.getModelo());
		ps.setString(4, pmiVehiculo.getColor());
		ps.setString(5, pmiVehiculo.getPropietario());
		ps.setInt(6, pmiVehiculo.getIdVehiculo());

		ps.executeUpdate();
		ps.close();
	}
	//Método que se utiliza para eliminar los datos de un vehículo...
	public void eliminarVehiculo(int pidVehiculo) throws SQLException{
		if(pidVehiculo<=0) return;

		PreparedStatement ps=cn.prepareStatement("DELETE FROM dbo.Vehiculos WHERE idVehiculo=?");
		ps.setInt(1, pidVehiculo);

		ps.executeUpdate();
		ps.close();
	}
}

