package pruebasUnitarias;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import estacionamiento.dominio.Vehiculo;
import estacionamiento.excepciones.IngresoException;
import estacionamiento.reglas_de_negocio.ReglaRestriccionPlaca;
import estacionamiento.reglas_de_negocio.ReglaTipoVehiculos;
import estacionamiento.servicios.VehiculoServicio;
import estacionamiento.servicios.Vigilante;
import testDataBuilder.VehiculoTestDataBuilder;

public class ServiciosTest {
	
	
	@Test
	public void evaluarReglasIngresoTest (){
		//arrange
		Vigilante vigilante = new Vigilante ();
		VehiculoTestDataBuilder vehiculoDataBuilder = new VehiculoTestDataBuilder ();
		Vehiculo vehiculo = vehiculoDataBuilder.conPlaca("BBB123").build(); 
		VehiculoServicio vehiculoServicio = Mockito.mock(VehiculoServicio.class);
		Mockito.when(vehiculoServicio.cantidadCarros()).thenReturn(5);
		Mockito.when(vehiculoServicio.cantidadMotos()).thenReturn(5);
		//act
		try {
			vigilante.evaluarReglasIngreso(vehiculo);
		} catch(IngresoException e) {
			//assert
			fail();
		}
	
	}
	/*
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
	
	}*/

	
	
}
