package dominioUnitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import estacionamiento.dominio.Vehiculo;
import testDataBuilder.VehiculoTestDataBuilder;


public class VehiculoTest {

	private static final String PLACA = "ZXY987";
	private static final String TIPO = "moto";
	private static final int CILINDRAJE = 600;
	
	@Test
	public void crearVehiculoTest(){
		
		// arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().
				conPlaca(PLACA).
				conTipo(TIPO).
				conCilindraje(CILINDRAJE);

		// act
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();

		// assert
		assertEquals(PLACA, vehiculo.getPlaca());
		assertEquals(TIPO, vehiculo.getTipo());
		assertEquals(CILINDRAJE, vehiculo.getCilindraje());
		
	}
	
}
