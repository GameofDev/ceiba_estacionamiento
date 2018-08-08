package dominioUnitTest;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import estacionamiento.dominio.Registro;
import estacionamiento.dominio.Vehiculo;
import testDataBuilder.RegistroTestDataBuilder;
import testDataBuilder.VehiculoTestDataBuilder;

public class RegistroTest {
	
	private static final String PLACA = "ZYX987";
	private static final Calendar FECHAINGRESO = Calendar.getInstance();
	private static final Calendar FECHASALIDA = Calendar.getInstance();
	private static final double COSTO = 5000;
	
	@Test
	public void crearRegistroTest (){
		
		// arrange
		FECHAINGRESO.set(2018,Calendar.MARCH,30);
		FECHASALIDA.set(2018,Calendar.APRIL,5);
		RegistroTestDataBuilder registroTestDataBuilder = new RegistroTestDataBuilder().
				conPlaca(PLACA).
				conFechaIngreso(FECHAINGRESO).
				conFechaSalida(FECHASALIDA).conCosto(COSTO);

		// act
		Registro registro = registroTestDataBuilder.build();

		// assert
		assertEquals(PLACA, registro.getPlaca());
		assertEquals(FECHAINGRESO, registro.getFechaIngreso());
		assertEquals(FECHASALIDA, registro.getFechaSalida());
		assertEquals(COSTO,registro.getCosto(), 5000.0);
		
		
	}

}
