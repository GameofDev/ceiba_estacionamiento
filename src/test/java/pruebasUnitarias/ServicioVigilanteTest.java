package pruebasUnitarias;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import estacionamiento.dominio.Parqueadero;
import estacionamiento.dominio.Registro;
import estacionamiento.dominio.Vehiculo;
import estacionamiento.excepciones.IngresoException;
import estacionamiento.repositorio.VehiculoRepository;
import estacionamiento.servicios.RegistroServicio;
import estacionamiento.servicios.VehiculoServicio;
import estacionamiento.servicios.Vigilante;
import testDataBuilder.RegistroTestDataBuilder;
import testDataBuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
public class ServicioVigilanteTest {
	

@TestConfiguration
   static class ServiciosTestContextConfiguration {
       @Bean
       public Vigilante vigilante() {
           return new Vigilante();
       }
   } 
	@Autowired
	private Vigilante vigilante;
	
	
	@MockBean
	private RegistroServicio registroServicio;
	
	@MockBean
	public VehiculoServicio vehiculoServicio;
	
	@MockBean
	private VehiculoRepository vehiculoRepositorio; 
	
	
	@Test
	public void evaluarReglasIngresoTest (){
		//arrange
		VehiculoTestDataBuilder vehiculoDataBuilder = new VehiculoTestDataBuilder ();
		Vehiculo vehiculo = vehiculoDataBuilder.conPlaca("BBB123").build(); 
		//act
		try {
			vigilante.evaluarReglasIngreso(vehiculo);
		} catch(IngresoException e) {
			//assert
			fail();
		}
	}
	
	@Test
	public void evaluarReglasFallaTest (){
		//arrange
		VehiculoTestDataBuilder vehiculoDataBuilder = new VehiculoTestDataBuilder ();
		Vehiculo vehiculo = vehiculoDataBuilder.conPlaca("BBB123").conTipo("moto").build(); 
		Mockito.when(vehiculoServicio.cantidadCarros()).thenReturn(20);
		Mockito.when(vehiculoServicio.cantidadMotos()).thenReturn(10);
		
		//act
		try {
			vigilante.evaluarReglasIngreso(vehiculo);
			fail();
		} catch(IngresoException e) {
			//assert
			assertNotNull(e.getMessage());
		}
	 
	}
	
	
	@Test
	public void realizarCobroUnaHoraCarroTest (){
		//arrange
		RegistroTestDataBuilder registroTestDataBuilder = new RegistroTestDataBuilder();
		Calendar fechaSalida = Calendar.getInstance();
		fechaSalida.add(Calendar.HOUR_OF_DAY, 1);
		Registro registro = registroTestDataBuilder.conFechaIngreso(Calendar.getInstance()).conFechaSalida(fechaSalida).build();
		
		//act
		registro = vigilante.realizarCobro(registro, Parqueadero.getCostoCarrosHora(), Parqueadero.getCostoCarrosDia(), 0);
		//assert	
		assertEquals(Parqueadero.getCostoCarrosHora(), registro.getCosto());

	}
	
	@Test
	public void realizarCobroDiasCarroTest (){
		//arrange
		RegistroTestDataBuilder registroTestDataBuilder = new RegistroTestDataBuilder();
		Calendar fechaSalida = Calendar.getInstance();
		fechaSalida.add(Calendar.DATE,6);
		Registro registro = registroTestDataBuilder.conFechaIngreso(Calendar.getInstance()).conFechaSalida(fechaSalida).build();
		//act
		registro = vigilante.realizarCobro(registro, Parqueadero.getCostoCarrosHora(), Parqueadero.getCostoCarrosDia(), 0);
		//assert	
		assertEquals(Parqueadero.getCostoCarrosDia()*6, registro.getCosto());

	}
	
	@Test
	public void realizarCobroUnDiaUnaHoraCarroTest (){
		//arrange
		RegistroTestDataBuilder registroTestDataBuilder = new RegistroTestDataBuilder();
		Calendar fechaIngreso = Calendar.getInstance();
		Calendar fechaSalida = (Calendar) fechaIngreso.clone();
		fechaSalida.add(Calendar.HOUR_OF_DAY, 25);
		Registro registro = registroTestDataBuilder.conFechaIngreso(fechaIngreso).conFechaSalida(fechaSalida).build();
		
		//act
		registro = vigilante.realizarCobro(registro, Parqueadero.getCostoCarrosHora(), Parqueadero.getCostoCarrosDia(), 0);
		//assert	
		assertEquals(Parqueadero.getCostoCarrosDia()+Parqueadero.getCostoCarrosHora(), registro.getCosto());

	}
	
	@Test
	public void realizarCobroDiasHorasCarroTest (){
		//arrange
		RegistroTestDataBuilder registroTestDataBuilder = new RegistroTestDataBuilder();
		Calendar fechaSalida = Calendar.getInstance();
		fechaSalida.add(Calendar.HOUR_OF_DAY, 3);
		fechaSalida.add(Calendar.DATE,8);
		Registro registro = registroTestDataBuilder.conFechaIngreso(Calendar.getInstance()).conFechaSalida(fechaSalida).build();
		
		//act
		registro = vigilante.realizarCobro(registro, Parqueadero.getCostoCarrosHora(), Parqueadero.getCostoCarrosDia(), 0);
		//assert	
		assertEquals((Parqueadero.getCostoCarrosDia()*8)+(Parqueadero.getCostoCarrosHora()*3), registro.getCosto());

	}
	
	@Test
	public void realizarCobro9HorasTest (){
		//arrange
		RegistroTestDataBuilder registroTestDataBuilder = new RegistroTestDataBuilder();
		Calendar fechaSalida = Calendar.getInstance();
		fechaSalida.add(Calendar.HOUR_OF_DAY, 9);
		Registro registro = registroTestDataBuilder.conFechaIngreso(Calendar.getInstance()).conFechaSalida(fechaSalida).build();
		
		//act
		registro = vigilante.realizarCobro(registro, Parqueadero.getCostoCarrosHora(), Parqueadero.getCostoCarrosDia(), 0);
		//assert	
		assertEquals(Parqueadero.getCostoCarrosDia(), registro.getCosto());

	}
	
	@Test
	public void realizarCobro8HorasTest (){
		//arrange
		RegistroTestDataBuilder registroTestDataBuilder = new RegistroTestDataBuilder();
		Calendar fechaSalida = Calendar.getInstance();
		fechaSalida.add(Calendar.HOUR_OF_DAY, 8);
		Registro registro = registroTestDataBuilder.conFechaIngreso(Calendar.getInstance()).conFechaSalida(fechaSalida).build();
		//act
		registro = vigilante.realizarCobro(registro, Parqueadero.getCostoCarrosHora(), Parqueadero.getCostoCarrosDia(), 0);
		//assert	
		assertEquals(Parqueadero.getCostoCarrosHora()*8, registro.getCosto());

	}
	
	
	@Test
	public void realizarCobroMotoAltoCilindrajeTest (){
		//arrange
		RegistroTestDataBuilder registroTestDataBuilder = new RegistroTestDataBuilder();
		Calendar fechaSalida = Calendar.getInstance();
		fechaSalida.add(Calendar.HOUR_OF_DAY,7);
		Registro registro = registroTestDataBuilder.conFechaIngreso(Calendar.getInstance()).conFechaSalida(fechaSalida).build();
		
		//act
		registro = vigilante.realizarCobro(registro, Parqueadero.getCostoMotosHora(), Parqueadero.getCostoMotosDia(), Parqueadero.getExtraMotos());
		//assert	
		assertEquals((Parqueadero.getCostoMotosHora()*7 + Parqueadero.getExtraMotos()), registro.getCosto());

	}

	
	
}
