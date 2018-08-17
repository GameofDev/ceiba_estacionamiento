package estacionamiento.reglas_de_negocio;

import estacionamiento.dominio.Vehiculo;
import estacionamiento.excepciones.IngresoException;

public class ReglaValidacionDatos implements ReglaIngreso {
	
	public static final String DATOS_NO_VALIDOS = "Los datos ingresados no son validad: No dejar Placa en Blaco ni Cilindrajes negativos";
	
	
	public void verificarRegla(Vehiculo vehiculo) {
		if(vehiculo.getPlaca().trim().isEmpty()){
			mostrarMensaje(DATOS_NO_VALIDOS);
		}
	}

	@Override
	public void mostrarMensaje(String mensaje) {
		throw new IngresoException(mensaje);
	}

}
