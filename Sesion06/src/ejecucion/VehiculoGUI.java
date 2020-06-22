package ejecucion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import clases.*;

public class VehiculoGUI extends JFrame {
	private JPanel pninfo, pnlistado, pncomandos;
	private JLabel label1, label2, label3, label4, label5, label6;
	private JTextField txtidVehiculo, txtnPlaca, txtmarca, txtmodelo, txtcolor, txtpropietario;
	private JButton btnnuevo, btneditar, btnguardar, btneliminar, btnsalir;
	private JTable tbllistado; JScrollPane scroll;
	DefaultTableModel modelo=new DefaultTableModel();
	boolean guardando, editando, limpiando;

	public VehiculoGUI() {
		guardando=false; editando=false; limpiando=false;

		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Gestionar Información Vehicular.");
		setResizable(false);

		this.inicializarComponentes();
		this.inicializarListado();
		this.habilitarCampos(false);
		this.habilitarBotones(true);
	}
	//
	private void habilitarCampos(boolean phabilitado) {
		txtidVehiculo.setEditable(false); 
		txtnPlaca.setEditable(phabilitado);
		txtmarca.setEditable(phabilitado);
		txtmodelo.setEditable(phabilitado);
		txtcolor.setEditable(phabilitado);
		txtpropietario.setEditable(phabilitado);
	}
	private void habilitarBotones(boolean phabilitado) {
		btnnuevo.setEnabled(phabilitado);
		btneditar.setEnabled(phabilitado);
		btnguardar.setEnabled(!phabilitado);
		btneliminar.setEnabled(phabilitado);
	}
	private void inicializarComponentes() {
		label1= new JLabel("Id. Vehículo:");
		label1.setBounds(10,10,100,30);
		//
		label2= new JLabel("Número placa:");
		label2.setBounds(10,50,100,30);
		//
		label3= new JLabel("Marca Vehículo:");
		label3.setBounds(10,90,100,30);
		//
		label4= new JLabel("Modelo Vehículo:");
		label4.setBounds(10,130,100,30);
		//
		label5= new JLabel("Color Vehículo:");
		label5.setBounds(10,170,100,30);
		//
		label6= new JLabel("Propietario:");
		label6.setBounds(10,210,100,30);
		//
		txtidVehiculo=new JTextField("");
		txtidVehiculo.setBounds(120, 10, 200, 30);
		//
		txtnPlaca=new JTextField();
		txtnPlaca.setBounds(120, 50, 200, 30);
		//
		txtmarca=new JTextField();
		txtmarca.setBounds(120, 90, 200, 30);
		//
		txtmodelo=new JTextField();
		txtmodelo.setBounds(120, 130, 200, 30);
		//
		txtcolor=new JTextField();
		txtcolor.setBounds(120, 170, 200, 30);
		//
		txtpropietario=new JTextField();
		txtpropietario.setBounds(120, 210, 200, 30);
		//
		btnnuevo=new JButton("Nuevo");
		btnnuevo.setBounds(30,20,120,30);
		btnnuevo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnnuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				habilitarCampos(true);
				habilitarBotones(false);
				guardando=true;
			}			
		});
		//
		btneditar=new JButton("Editar");
		btneditar.setBounds(30,60,120,30);
		btneditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btneditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				habilitarCampos(true);
				habilitarBotones(false);
				editando=true;
			}			
		});
		//
		btnguardar=new JButton("Guardar");
		btnguardar.setBounds(30,100,120,30);
		btnguardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnguardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (guardando) {
					insertarVehiculo();
					guardando=false;
				}
				else if (editando) {
					actualizarVehiculo();
					editando=false;
				}				
				habilitarCampos(false);
				habilitarBotones(true);
			}			
		});
		//
		btneliminar=new JButton("Eliminar");
		btneliminar.setBounds(30,140,120,30);
		btneliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btneliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eliminarVehiculo();
			}			
		});
		//
		btnsalir=new JButton("Salir");
		btnsalir.setBounds(30,180,120,30);
		btnsalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnsalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0); 
			}			
		});
		//
		tbllistado=new JTable();
		tbllistado.setBounds(0, 0, 980, 470);
		tbllistado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//
		scroll = new JScrollPane(tbllistado);
		scroll.setBounds(0, 0, 980, 470);
		//
		pncomandos=new JPanel();
		pncomandos.setLayout(null);
		pncomandos.setBounds(800, 0, 180, 380);
		pncomandos.setBackground(Color.green);
		pncomandos.add(btnnuevo); pncomandos.add(btneditar); pncomandos.add(btnguardar); pncomandos.add(btneliminar); pncomandos.add(btnsalir);
		//
		pninfo=new JPanel();
		pninfo.setLayout(null);
		pninfo.setBounds(0, 0, 980, 280);
		pninfo.setBackground(Color.red);
		pninfo.add(label1); pninfo.add(label2); pninfo.add(label3); pninfo.add(label4); pninfo.add(label5); pninfo.add(label6);
		pninfo.add(txtidVehiculo); pninfo.add(txtnPlaca); pninfo.add(txtmarca); pninfo.add(txtmodelo); pninfo.add(txtcolor); pninfo.add(txtpropietario);
		pninfo.add(pncomandos);
		//
		pnlistado=new JPanel();
		pnlistado.setLayout(null);
		pnlistado.setBounds(0, 281, 980, 470);
		pnlistado.setBackground(Color.blue);
		pnlistado.add(scroll);

		add(pninfo);
		add(pnlistado);
	}
	private void inicializarListado() {
		modelo.addColumn("Id. Vehiculo");
		modelo.addColumn("No. Placa");
		modelo.addColumn("Marca");
		modelo.addColumn("Modelo");
		modelo.addColumn("Color");
		modelo.addColumn("Propietario");

		this.tbllistado.setModel(modelo);
		listarVehiculos();
		ListSelectionModel miseleccion = tbllistado.getSelectionModel();
		miseleccion.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!limpiando) {
					int[] selectedRow = tbllistado.getSelectedRows();
					txtidVehiculo.setText(String.valueOf(tbllistado.getValueAt(selectedRow[0], 0)));
					txtnPlaca.setText(String.valueOf(tbllistado.getValueAt(selectedRow[0], 1)));
					txtmarca.setText(String.valueOf(tbllistado.getValueAt(selectedRow[0], 2)));
					txtmodelo.setText(String.valueOf(tbllistado.getValueAt(selectedRow[0], 3)));
					txtcolor.setText(String.valueOf(tbllistado.getValueAt(selectedRow[0], 4)));
					txtpropietario.setText(String.valueOf(tbllistado.getValueAt(selectedRow[0], 5)));
				}
			}
		});	
	}
	//
	private void listarVehiculos() {
		List<Vehiculo> misVehiculos=null;
		Object[] filas = new Object[6];
		try {
			DaoVehiculo dao =  DaoVehiculo.getInstancia();
			misVehiculos=dao.listarVehiculos();
			for(Vehiculo vs:misVehiculos) {
				filas[0]=vs.getIdVehiculo();
				filas[1]=vs.getnPlaca();
				filas[2]=vs.getMarca();
				filas[3]=vs.getModelo();
				filas[4]=vs.getColor();
				filas[5]=vs.getPropietario();

				modelo.addRow(filas);
			}
			this.tbllistado.setModel(modelo);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e, "Mensaje Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void insertarVehiculo(){
		String nPlaca=txtnPlaca.getText();
		String marca=txtmarca.getText();
		String modelo=txtmodelo.getText();
		String color=txtcolor.getText();
		String propietario=txtpropietario.getText();
		try {
			DaoVehiculo dao =  DaoVehiculo.getInstancia();
			dao.insertarVehiculo(new Vehiculo(nPlaca, marca, modelo, color, propietario));
			JOptionPane.showMessageDialog(this, "Registro guardado exitosamente!!!", "Mensaje Éxito!", JOptionPane.INFORMATION_MESSAGE);
			listarVehiculos(); //Que vuelva a cargar el listado...
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e, "Mensaje Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void actualizarVehiculo(){
		int idVehiculo=Integer.parseInt(txtidVehiculo.getText());
		String nPlaca=txtnPlaca.getText();
		String marca=txtmarca.getText();
		String modelo=txtmodelo.getText();
		String color=txtcolor.getText();
		String propietario=txtpropietario.getText();

		try {
			DaoVehiculo dao =  DaoVehiculo.getInstancia();
			dao.actualizarVehiculo(new Vehiculo(idVehiculo,nPlaca, marca, modelo, color, propietario));
			JOptionPane.showMessageDialog(this, "Registro actualizado exitosamente!!!", "Mensaje Éxito!", JOptionPane.INFORMATION_MESSAGE);
			listarVehiculos(); //Que vuelva a cargar el listado...
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e, "Mensaje Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void eliminarVehiculo(){
		int idVehiculo=Integer.parseInt(txtidVehiculo.getText());
		try {
			DaoVehiculo dao =  DaoVehiculo.getInstancia();
			dao.eliminarVehiculo(idVehiculo);
			JOptionPane.showMessageDialog(this, "Registro eliminado exitosamente!!!", "Mensaje Éxito!", JOptionPane.INFORMATION_MESSAGE);
			listarVehiculos(); //Que vuelva a cargar el listado...
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e, "Mensaje Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
	//
	public static void main(String[] args) {
		VehiculoGUI miGUI = new VehiculoGUI();
		//miGUI.setSize(1000, 800);
		//miGUI.setLocation(200, 200);
		miGUI.setBounds(0, 0, 1000, 800);
		miGUI.setVisible(true);
		miGUI.setLocationRelativeTo(null);
	}
}