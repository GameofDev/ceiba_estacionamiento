package estacionamiento.reglas_de_negocio;

import estacionamiento.dominio.Vehiculo;
import estacionamiento.excepciones.IngresoException;

public class ReglaVehiculoNoEncontrado implements ReglaIngreso {

	public static final String VEHICULO_NO_ENCONTRADO = "No existe un vehiculo registrado con esta placa";

	@Override
	public void verificarRegla(Vehiculo vehiculo) {
		if(vehiculo == null){
			mostrarMensaje(VEHICULO_NO_ENCONTRADO);
		} 
	} 

	@Override
	public void mostrarMensaje(String mensaje) {
		throw new IngresoException(mensaje);
	}

}
