package estacionamiento.reglas_de_negocio;

import estacionamiento.dominio.Vehiculo;

public interface ReglaIngreso {

	public void verificarRegla(Vehiculo vehiculo);
	
	public void mostrarMensaje(String mensaje);

}
