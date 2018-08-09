package testDataBuilder;

import estacionamiento.dominio.Vehiculo;

public class VehiculoTestDataBuilder {

	private static final String PLACA = "ABC123";
	private static final String TIPO = "moto";
	private static final int CILINDRAJE = 400;

	private String placa;
	private String tipo; 
	private int cilindraje;

	public VehiculoTestDataBuilder (){
		this.placa = PLACA;
		this.tipo = TIPO;
		this.cilindraje = CILINDRAJE;
	}
	
	public VehiculoTestDataBuilder conPlaca (String placa){
		this.placa = placa;
		return this;
	}
	
	public VehiculoTestDataBuilder conTipo (String tipo){
		this.tipo = tipo;
		return this;
	}
	
	public VehiculoTestDataBuilder conCilindraje (int cilindraje){
		this.cilindraje = cilindraje;
		return this;
	}
	
	public Vehiculo build(){
		return new Vehiculo(this.placa, this.tipo, this.cilindraje);
		
	}
	

	

}
