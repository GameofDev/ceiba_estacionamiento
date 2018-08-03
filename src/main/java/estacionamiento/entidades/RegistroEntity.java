package estacionamiento.entidades;

import java.util.Calendar;

import javax.persistence.*;

@Entity 
@SequenceGenerator(name="IDs_registros",initialValue = 1, sequenceName="IDs_registros")
public class RegistroEntity {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="IDs_registros")
	@Column(name = "id1", updatable = false, nullable = false)
	Long id;
	private String placa;
	private Calendar fechaIngreso;
	private Calendar fechaSalida;
	private double costo;
	
	public RegistroEntity (Long id){
		this.id = id;
	}
	
	public RegistroEntity (String placa, Calendar fechaIngreso, Calendar fechaSalida, double costo){
		this.placa = placa;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.costo = costo;
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
	public Calendar getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public double getCosto() {
		return costo;
	}
	public void setValor(double costo) {
		this.costo = costo;
	}
	

}
