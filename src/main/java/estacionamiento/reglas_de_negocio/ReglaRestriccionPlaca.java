package estacionamiento.reglas_de_negocio;

import java.util.Calendar;

import estacionamiento.dominio.Vehiculo;
import estacionamiento.excepciones.IngresoException;

public class ReglaRestriccionPlaca implements ReglaIngreso {

	public static final String NO_ESTA_AUTORIZADO = "No esta autorizado para ingresar el dia de hoy";
	
	
	@Override
	public  void verificarRegla(Vehiculo vehiculo) {
		Calendar fecha = Calendar.getInstance();
		int dia = fecha.get(Calendar.DAY_OF_WEEK);
		if (vehiculo.getPlaca().toUpperCase().startsWith("A") && ((dia == Calendar.SUNDAY) || (dia == Calendar.MONDAY) )){
			mostrarMensaje(NO_ESTA_AUTORIZADO);
		}
	}
 
	@Override
	public void mostrarMensaje(String mensaje) {
		throw new IngresoException(mensaje);
		
	}

}
