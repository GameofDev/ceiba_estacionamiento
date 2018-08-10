package pruebasUnitarias;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import estacionamiento.dominio.Vehiculo;
import estacionamiento.excepciones.IngresoException;
import estacionamiento.reglas_de_negocio.*;
import testDataBuilder.VehiculoTestDataBuilder;

public class ReglasTest {
	 
	@Test
	public void parqueaderoNoDisponibleTest (){
		// arrange
		ReglaIngreso reglaNegocio = new ReglaCantidadParqueadero (20,10);
		VehiculoTestDataBuilder vehiculoDataBuilder = new VehiculoTestDataBuilder ();
		Vehiculo vehiculo = vehiculoDataBuilder.build();
		
		// act
		try {
			reglaNegocio.verificarRegla(vehiculo);
			fail();
		} catch(IngresoException e) {
			//assert
			assertEquals(ReglaCantidadParqueadero.PARQUEADERO_NO_DISPONIBLE, e.getMessage());
		}
	
	}
	
	@Test
	public void paqueaderoMotoLLenoTest(){
		// arrange
		ReglaIngreso reglaNegocio = new ReglaCantidadParqueadero (1,10);
				VehiculoTestDataBuilder vehiculoDataBuilder = new VehiculoTestDataBuilder ();
				Vehiculo vehiculo = vehiculoDataBuilder.conTipo("moto").build();
				
				// act
				try {
					reglaNegocio.verificarRegla(vehiculo);
					fail();
				} catch(IngresoException e) {
					//assert
					assertEquals(ReglaCantidadParqueadero.PARQUEADERO_NO_DISPONIBLE, e.getMessage());
				}
	}
	
	@Test
	public void parqueaderoCarroLLenoTest () {
		// arrange
		ReglaIngreso reglaNegocio = new ReglaCantidadParqueadero (20,1);
		VehiculoTestDataBuilder vehiculoDataBuilder = new VehiculoTestDataBuilder ();
		Vehiculo vehiculo = vehiculoDataBuilder.conTipo("carro").build();
		
		// act
		try {
			reglaNegocio.verificarRegla(vehiculo);
			fail();
		} catch(IngresoException e) {
			//assert
			assertEquals(ReglaCantidadParqueadero.PARQUEADERO_NO_DISPONIBLE, e.getMessage());
		}
	}
	
	@Test 
	public void ingresoDomingoConRestriccionTest () {
		// arrange
		ReglaIngreso reglaNegocio = new ReglaRestriccionPlaca(Calendar.SUNDAY);
		VehiculoTestDataBuilder vehiculoDataBuilder = new VehiculoTestDataBuilder ();
		Vehiculo vehiculo = vehiculoDataBuilder.conPlaca("ABC123").build();
		//act
		try {
			reglaNegocio.verificarRegla(vehiculo);
			fail();
		} catch(IngresoException e) {
			//assert
			assertEquals(ReglaRestriccionPlaca.NO_ESTA_AUTORIZADO, e.getMessage());
		}
	}
	
	@Test 
	public void ingresoLunesConRestriccionTest () {
		// arrange
		ReglaIngreso reglaNegocio = new ReglaRestriccionPlaca(Calendar.MONDAY);
		VehiculoTestDataBuilder vehiculoDataBuilder = new VehiculoTestDataBuilder ();
		Vehiculo vehiculo = vehiculoDataBuilder.conPlaca("ABC123").build();
		//act
		try {
			reglaNegocio.verificarRegla(vehiculo);
			fail();
		} catch(IngresoException e) {
			//assert
			assertEquals(ReglaRestriccionPlaca.NO_ESTA_AUTORIZADO, e.getMessage());
		}
	}
	
	@Test 
	public void ingresoVehiculoDiferenteTest () {
		// arrange
		ReglaIngreso reglaNegocio = new ReglaTipoVehiculos();
		VehiculoTestDataBuilder vehiculoDataBuilder = new VehiculoTestDataBuilder ();
		Vehiculo vehiculo = vehiculoDataBuilder.conTipo("CAMION").build();
		//act
		try {
			reglaNegocio.verificarRegla(vehiculo);
			fail();
		} catch(IngresoException e) {
			//assert
			assertEquals(ReglaTipoVehiculos.VEHICULO_NO_AUTORIZADO, e.getMessage());
		}
	}
	
	
 
}
