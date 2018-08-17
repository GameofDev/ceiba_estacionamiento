package estacionamiento.reglas_de_negocio;

import estacionamiento.dominio.Vehiculo;
import estacionamiento.excepciones.IngresoException;

public class ReglaErrorPlaca implements ReglaIngreso {
	
	private Vehiculo vehiculoConsulta;
	public static final String VEHICULO_YA_EXISTE = "Ya existe un vehiculo con esa placa";
	
	public ReglaErrorPlaca (Vehiculo vehiculo){
		this.vehiculoConsulta = vehiculo;
	}
	
	public void verificarRegla(Vehiculo vehiculo) {
		if (vehiculoConsulta.getPlaca().equals(vehiculo.getPlaca())){
			mostrarMensaje(VEHICULO_YA_EXISTE);
		} 
	}

	@Override
	public void mostrarMensaje(String mensaje) {
		throw new IngresoException(mensaje);
	}

}
