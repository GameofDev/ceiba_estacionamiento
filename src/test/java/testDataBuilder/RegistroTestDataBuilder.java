package testDataBuilder;

import java.util.Calendar;

import estacionamiento.dominio.Registro;

public class RegistroTestDataBuilder {
	private static final String PLACA = "ABC123";
	private static final double COSTO = 0;
	
	private String placa;
	private Calendar fechaIngreso = Calendar.getInstance();
	private Calendar fechaSalida= Calendar.getInstance();
	private double costo;
	
	public RegistroTestDataBuilder (){
		this.placa = PLACA;
		this.fechaIngreso.set(2018, Calendar.JULY, 5);
		this.fechaSalida.set(2018, Calendar.JULY, 20);
		this.costo = COSTO;
	}

	public RegistroTestDataBuilder conPlaca (String placa){
		this.placa = placa;
		return this;
	}
	
	public RegistroTestDataBuilder conFechaIngreso (Calendar fechaIngreso){
		this.fechaIngreso = fechaIngreso;
		return this;
	}
	
	public RegistroTestDataBuilder conFechaSalida (Calendar fechaSalida){
		this.fechaSalida = fechaSalida;
		return this;
	}
	
	public RegistroTestDataBuilder conCosto (double costo){
		this.costo = costo;
		return this;
	}
	
	public Registro build(){
		return new Registro(this.placa, this.fechaIngreso, this.fechaSalida, this.costo);
	}
	
}

