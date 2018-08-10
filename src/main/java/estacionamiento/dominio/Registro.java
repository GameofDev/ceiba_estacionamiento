package estacionamiento.dominio;

import java.util.Calendar;

public class Registro {
	
	private String placa;
	private Calendar fechaIngreso;
	private Calendar fechaSalida;
	private long costo;
	
	public Registro (){
		
	}
	
	public Registro (String placa, Calendar fechaIngreso, Calendar fechaSalida, long costo){
		this.placa = placa;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.costo = costo;
	}
	
	public Registro (String placa, Calendar fechaIngreso){
		this.placa = placa;
		this.fechaIngreso = fechaIngreso;
	} 
	

	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Calendar getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Calendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Calendar getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public long getCosto() {
		return costo;
	}
	public void setValor(long costo) {
		this.costo = costo;
	}
	

}
