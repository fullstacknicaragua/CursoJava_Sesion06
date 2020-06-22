package ejecucion;

import clases.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class VehiculoApp {
	public static void main(String[] args) {
		try {
			Scanner entrada=new Scanner(System.in);
			DaoVehiculo dao;

			dao = DaoVehiculo.getInstancia();

			int opcion=0;

			System.out.println("Seleccione una opción:");
			System.out.println("1. Listar Vehículos.");
			System.out.println("2. Buscar Vehículo.");
			System.out.println("3. Insertar Vehículo.");
			System.out.println("4. Actualizar Vehículo.");
			System.out.println("5. Eliminar Vehículo.");

			opcion=entrada.nextInt();
			switch(opcion) {
			case 1:
				listarVehiculos(dao);
				break;
			case 2:
				obtenerVehiculo(dao);
				break;
			case 3:
				insertarVehiculo(dao);
				break;
			case 4:
				actualizarVehiculo(dao);
				break;
			case 5:
				eliminarVehiculo(dao);
				break;
			default:
				System.out.println("La opción seleccionada no es válida...");
			}


			entrada.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private static void	listarVehiculos(DaoVehiculo dao) {
		System.out.println("LISTADO VEHICULOS");
		System.out.println("------------------------------------");
		List<Vehiculo> misVehiculos=null;
		try {
			misVehiculos=dao.listarVehiculos();
			for(Vehiculo vs:misVehiculos) {
				System.out.println("id: " + vs.getIdVehiculo() + ", placa: " + vs.getnPlaca() + ", marca: " + vs.getMarca() + ", modelo: " + vs.getModelo() + ", color: " + vs.getColor() + ", propietario: " + vs.getPropietario());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private static void obtenerVehiculo(DaoVehiculo dao) {
		Scanner entrada=new Scanner(System.in);
		System.out.println("BUSQUEDA VEHICULO");
		System.out.println("------------------------------------");
		System.out.println("Indique el id del vehículo: ");
		int miidVehiculo=entrada.nextInt(); //Leyendo el id vehículo digitado por el usuario...

		Vehiculo miVehiculo=null;
		try {
			miVehiculo=dao.obtenerVehiculo(miidVehiculo);
		} catch (Exception e) {
			System.out.println(e);
		}
		if (miVehiculo!=null) {
			System.out.println("id: " + miVehiculo.getIdVehiculo() + ", placa: " + miVehiculo.getnPlaca() + ", marca: " + miVehiculo.getMarca() + ", modelo: " + miVehiculo.getModelo() + ", color: " + miVehiculo.getColor() + ", propietario: " + miVehiculo.getPropietario());
		}
		else
		{System.out.println("No se ha encontrado resultado con el id que ingresó.");}

		entrada.close();
	}
	private static void	insertarVehiculo(DaoVehiculo dao){
		Scanner entrada=new Scanner(System.in);
		System.out.println("INSERTAR VEHICULO");
		System.out.println("------------------------------------");

		System.out.println("Introduzca el número de placa: ");
		String nPlaca=entrada.next();
		System.out.println("Introduzca la marca: ");
		String marca=entrada.next();
		System.out.println("Introduzca el modelo: ");
		String modelo=entrada.next();
		System.out.println("Introduzca el color: ");
		String color=entrada.next();
		System.out.println("Introduzca nombre propietario: ");
		String propietario=entrada.next();

		try {
			dao.insertarVehiculo(new Vehiculo(nPlaca, marca, modelo, color, propietario));
			System.out.println("Registro insertado exitosamente!!!");
		} catch (Exception e) {
			System.out.println(e);
		}
		entrada.close();
	}
	private static void	actualizarVehiculo(DaoVehiculo dao){
		Scanner entrada=new Scanner(System.in);
		System.out.println("ACTUALIZAR VEHICULO");
		System.out.println("------------------------------------");
		System.out.println("Indique el id del vehículo: ");
		int idVehiculo=entrada.nextInt(); //Leyendo el id vehículo digitado por el usuario...
		System.out.println("--------------------");
		System.out.println("Introduzca el número de placa: ");
		String nPlaca=entrada.next();
		System.out.println("Introduzca la marca: ");
		String marca=entrada.next();
		System.out.println("Introduzca el modelo: ");
		String modelo=entrada.next();
		System.out.println("Introduzca el color: ");
		String color=entrada.next();
		System.out.println("Introduzca nombre propietario: ");
		String propietario=entrada.next();

		try {
			dao.actualizarVehiculo(new Vehiculo(idVehiculo,nPlaca, marca, modelo, color, propietario));
			System.out.println("Registro actualizado exitosamente!!!");
		} catch (Exception e) {
			System.out.println(e);
		}
		entrada.close();
	}
	private static void	eliminarVehiculo(DaoVehiculo dao){
		Scanner entrada=new Scanner(System.in);
		System.out.println("ELIMINAR VEHICULO");
		System.out.println("------------------------------------");
		System.out.println("Indique el id del vehículo: ");
		int miidVehiculo=entrada.nextInt(); //Leyendo el id vehículo digitado por el usuario...
		try {
			dao.eliminarVehiculo(miidVehiculo);
			System.out.println("Registro eliminado exitosamente!!!");
		} catch (Exception e) {
			System.out.println(e);
		}
		entrada.close();
	}
}

