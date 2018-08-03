package estacionamiento.reglas_de_negocio;

import estacionamiento.dominio.Parqueadero;
import estacionamiento.dominio.Vehiculo;
import estacionamiento.excepciones.IngresoException;

public class ReglaCantidadParqueadero implements ReglaIngreso{

	public static final String PARQUEADERO_NO_DISPONIBLE = "NO EXISTEN PARQUEADEROS DISPONIBLES PARA SU VEHICULO";
	private int numCarrosActual;
	private int numMotosActual;
	private Parqueadero parqueadero;
		
	public ReglaCantidadParqueadero (int numCarrosActual, int numMotosActual){
		this.numCarrosActual = numCarrosActual;
		this.numMotosActual = numMotosActual;
	}
	
	public void verificarRegla(Vehiculo vehiculo) {
    	if(vehiculo.getTipo().equals("moto")){
    		parqueoDisponibleMoto();
    	} else if (vehiculo.getTipo().equals("carro")){
    		parqueoDisponibleCarro();
    	}
	}

	public void mostrarMensaje(String mensaje) {
		throw new IngresoException(mensaje);
	}

	public void parqueoDisponibleMoto (){
		if(parqueadero.getLimiteMotos()== numMotosActual){
			mostrarMensaje(PARQUEADERO_NO_DISPONIBLE);
   	     }
	}
	
	public void parqueoDisponibleCarro (){
		if(parqueadero.getLimiteCarros()== numCarrosActual){
			mostrarMensaje(PARQUEADERO_NO_DISPONIBLE);
   	     }
	}
}
