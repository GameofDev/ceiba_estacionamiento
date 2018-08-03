package estacionamiento.entidades;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="IDs_vehiculos", sequenceName="Ids_vehiculos",initialValue = 1)
public class VehiculoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="IDs_vehiculos")
	@Column(name = "id2", updatable = false, nullable = false)
	Long id;
	private String placa;
	private String tipo;
	private int cilindraje;
	
	public VehiculoEntity (String placa, String tipo, int cilindraje){
		this.placa = placa;
		this.tipo = tipo;
		this.cilindraje = cilindraje;
	}
	
	public VehiculoEntity (){
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
	
	
	
}