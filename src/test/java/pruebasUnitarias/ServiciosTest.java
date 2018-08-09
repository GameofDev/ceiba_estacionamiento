package pruebasUnitarias;

import static org.junit.Assert.*;

import org.junit.Test;

import estacionamiento.dominio.Vehiculo;
import estacionamiento.excepciones.IngresoException;
import estacionamiento.reglas_de_negocio.ReglaTipoVehiculos;
import estacionamiento.servicios.Vigilante;
import testDataBuilder.VehiculoTestDataBuilder;

public class ServiciosTest {
	
	/*
	@Test
	public void evaluarReglasIngresoTest (){
		//arrange
		Vigilante vigilante = new Vigilante ();
		VehiculoTestDataBuilder vehiculoDataBuilder = new VehiculoTestDataBuilder ();
		Vehiculo vehiculo = vehiculoDataBuilder.build(); 
		
		//act
		try {
			vigilante.evaluarReglasIngreso(vehiculo);
			fail();
		} catch(IngresoException e) {
			//assert
			assertEquals(ReglaTipoVehiculos.VEHICULO_NO_AUTORIZADO, e.getMessage());
		}
	
	}
	
	@Test
	public void evaluarReglasFallaTest (){
		//arrange
		Vigilante vigilante = new Vigilante ();
		VehiculoTestDataBuilder vehiculoDataBuilder = new VehiculoTestDataBuilder ();
		Vehiculo vehiculo = vehiculoDataBuilder.build(); 
		
		//act
		try {
			vigilante.evaluarReglasIngreso(vehiculo);
			
		} catch(IngresoException e) {
			//assert
			assertNotNull(e.getMessage());
		}
	
	}
*/
	
	
}
