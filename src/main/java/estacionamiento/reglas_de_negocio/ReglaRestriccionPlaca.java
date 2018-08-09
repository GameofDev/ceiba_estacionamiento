package estacionamiento.reglas_de_negocio;

import java.util.Calendar;
import java.util.Date;

import estacionamiento.dominio.Vehiculo;
import estacionamiento.excepciones.IngresoException;

public class ReglaRestriccionPlaca implements ReglaIngreso {

	public static final String NO_ESTA_AUTORIZADO = "No esta autorizado para ingresar el dia de hoy";
	private int dia;
	
	public ReglaRestriccionPlaca (int dia ){
		this.dia = dia;
	}
	

	public  void verificarRegla(Vehiculo vehiculo) {
		if (vehiculo.getPlaca().toUpperCase().startsWith("A") && ((dia == Calendar.SUNDAY) || (dia == Calendar.MONDAY) )){
			mostrarMensaje(NO_ESTA_AUTORIZADO);
		}
	}
 
	@Override
	public void mostrarMensaje(String mensaje) {
		throw new IngresoException(mensaje);
		
	

	}
}
