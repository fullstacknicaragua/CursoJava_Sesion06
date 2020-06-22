package clases;

public class Vehiculo {
	private int idVehiculo;
	private String nPlaca, marca, modelo, color, propietario;
	
	public Vehiculo(String pnPlaca, String pmarca, String pmodelo, String pcolor, String ppropietario) {
		this.nPlaca=pnPlaca; 
		this.marca=pmarca;
		this.modelo=pmodelo;
		this.color=pcolor;
		this.propietario=ppropietario;
	}
	public Vehiculo(int pidVehiculo, String pnPlaca, String pmarca, String pmodelo, String pcolor, String ppropietario) {
		this.idVehiculo=pidVehiculo;
		this.nPlaca=pnPlaca; 
		this.marca=pmarca;
		this.modelo=pmodelo;
		this.color=pcolor;
		this.propietario=ppropietario;
	}
	public int getIdVehiculo() {
		return idVehiculo;
	}
	public String getnPlaca() {
		return nPlaca;
	}
	public void setnPlaca(String nPlaca) {
		this.nPlaca = nPlaca;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPropietario() {
		return propietario;
	}
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
}
