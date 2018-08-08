package estacionamiento.entidades;

import java.util.Calendar;

import javax.persistence.*;

@Entity 
@SequenceGenerator(name="IDs_registros",initialValue = 1, sequenceName="IDs_registros")
public class RegistroEntity {
	
	@Id 
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="IDs_registros")
	@Column(name = "placa", updatable = false, nullable = false)
	private String placa;
	private Calendar fechaIngreso;
	
	public RegistroEntity (String placa){
		this.placa = placa;
	}
	
	public RegistroEntity (String placa, Calendar fechaIngreso, Calendar fechaSalida, double costo){
		this.placa = placa;
		this.fechaIngreso = fechaIngreso;

	}
	
	public RegistroEntity (){
		
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
	

}
