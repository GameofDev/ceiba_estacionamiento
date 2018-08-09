package estacionamiento.reglas_de_negocio;

import estacionamiento.dominio.Vehiculo;
import estacionamiento.excepciones.IngresoException;

public class ReglaTipoVehiculos implements ReglaIngreso{

	public static final String VEHICULO_NO_AUTORIZADO = "Solo se permite el acceso a carros y motos";
	
	public void verificarRegla(Vehiculo vehiculo) {
		if(!(vehiculo.getTipo().equals("moto")||vehiculo.getTipo().equals("carro"))){
			mostrarMensaje(VEHICULO_NO_AUTORIZADO);
		}
	} 

	
	public void mostrarMensaje(String mensaje) {
		throw new IngresoException(mensaje);
	}

}
  