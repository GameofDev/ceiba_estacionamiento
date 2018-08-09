package dominioUnitTest;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

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
	public void ingresoDomingoConRestriccion () {
		// arrange
		ReglaIngreso reglaNegocio = new ReglaRestriccionPlaca();
		VehiculoTestDataBuilder vehiculoDataBuilder = new VehiculoTestDataBuilder ();
		Vehiculo vehiculo = vehiculoDataBuilder.conPlaca("aBC123").build();
		Calendar fecha = Mockito.mock(Calendar.class);
		int dia;
		Mockito.when(dia = fecha.get(Calendar.DAY_OF_WEEK)).thenReturn(dia = Calendar.THURSDAY);
		
		//act
		try {
			reglaNegocio.verificarRegla(vehiculo);
			fail();
		} catch(IngresoException e) {
			//assert
			assertEquals(ReglaRestriccionPlaca.NO_ESTA_AUTORIZADO, e.getMessage());
		}
		
		
		//assertEquals(fecha.get(Calendar.DAY_OF_WEEK), Calendar.SUNDAY);
		
	}
	
	
	
	
	
	
 
}
