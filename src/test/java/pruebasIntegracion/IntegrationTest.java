package pruebasIntegracion;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.adn.estacionamiento.EstacionamientoApplication;

import estacionamiento.dominio.Registro;
import estacionamiento.dominio.Vehiculo;
import estacionamiento.servicios.Vigilante;
import testDataBuilder.VehiculoTestDataBuilder;

@SpringBootTest(classes = EstacionamientoApplication.class)
@Transactional
@RunWith(SpringRunner.class)
@Rollback(value=true)

public class IntegrationTest {
	
	@Autowired
	private Vigilante vigilante; 
	
	
	@Test
	public void IngresoVehiculoTest (){
		//arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		
		//act
		vigilante.registrarIngreso(vehiculo);
		Registro registro = vigilante.consultarRegistro(vehiculo.getPlaca());
		
		//assert
		assertEquals(vehiculo.getPlaca(),registro.getPlaca());
		
	}
	
	@Test
	public void DespachoVehiculoParqueadoTest (){
		//arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		vigilante.registrarIngreso(vehiculo);
		
		//act
		Registro registro = vigilante.despacharVehiculo(vehiculo.getPlaca()); 

		//assert
		assertNull(vigilante.consultarRegistro(vehiculo.getPlaca()));
		
	}


}
