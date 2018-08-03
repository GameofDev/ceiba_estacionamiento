package estacionamiento.dominio;

public  class Vehiculo {
	
	private String placa;
	private String tipo;
	private int cilindraje;
	


	public Vehiculo (String placa, String tipo, int cilindraje) {
		this.placa = placa;
		this.tipo = tipo;
		this.cilindraje = cilindraje;
	}
	
	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	
	public Vehiculo (){
	
	}

	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}
	


	

}
