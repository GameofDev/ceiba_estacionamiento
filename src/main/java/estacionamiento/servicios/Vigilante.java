package estacionamiento.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estacionamiento.dominio.*;
import estacionamiento.reglas_de_negocio.*;
import estacionamiento.repositorio.VehiculoRepository;


 
@Service
public class Vigilante {
	 
		 
	private static final int ALTO_CILINADRAJE = 500;

	@Autowired 
	private RegistroServicio registroServicio;
	
	@Autowired
	public VehiculoServicio vehiculoServicio;
	
	
	/*Metodo utilizado para ingresar vehiculos al parqueadero
	 * En este metodo se evaluan las reglas de ingreso al parqueadero
	 * Se ingresa el vehiculo yse crea un rgeistro del mismo.
	 */
	public Registro registrarIngreso(Vehiculo vehiculo) {
		evaluarReglasIngreso(vehiculo);
		vehiculoServicio.registrarVehiculo(vehiculo);
		return registroServicio.crearRegistro(vehiculo);
	}
	
   //Recibe un vehiculo  y crea la lista de reglas que se les va a aplicar. Hace un ciclo envaluando el vehiculo en esa lista de reglas.
	public void evaluarReglasIngreso (Vehiculo vehiculo) {
		List<ReglaIngreso> reglas = new ArrayList<>();
		reglas.add(new ReglaCantidadParqueadero(vehiculoServicio.cantidadCarros(),vehiculoServicio.cantidadMotos()));
		reglas.add(new ReglaRestriccionPlaca(Calendar.DAY_OF_WEEK));
		reglas.add(new ReglaTipoVehiculos());
		reglas.add(new ReglaTipoVehiculos());
		reglas.add(new ReglaValidacionDatos());
		for (ReglaIngreso regla : reglas) {
			regla.verificarRegla(vehiculo);
	    }
	}
	
	public void evaluacionReglasSalida (Vehiculo vehiculo){
		List<ReglaIngreso> reglas = new ArrayList<>();
		reglas.add(new ReglaVehiculoNoEncontrado());
		for (ReglaIngreso regla : reglas) {
			regla.verificarRegla(vehiculo);
	    }
	}
	 
 
	//Debe evaluar la excepciones de salida: La placa no existe. 
	public Registro despacharVehiculo (String placa){
		Vehiculo vehiculo = vehiculoServicio.consultarVehiculo(placa);
		evaluacionReglasSalida(vehiculo);
		Registro registro = consultarRegistro(placa);
		registro.setFechaSalida(Calendar.getInstance());
		
		int altoCilindraje=0;
		if (vehiculo.getTipo().equals("moto")){
			if(vehiculo.getCilindraje()>ALTO_CILINADRAJE){
				altoCilindraje = Parqueadero.getExtraMotos();
			}
			return realizarCobro(registro,Parqueadero.getCostoMotosHora(),Parqueadero.getCostoMotosDia(),altoCilindraje);
		}
		return realizarCobro(registro,Parqueadero.getCostoCarrosHora(),Parqueadero.getCostoCarrosDia(),altoCilindraje);
	}
	
	public Registro consultarRegistro(String placa){
		return registroServicio.consultarRegistro(placa);
	}
	
	public Vehiculo consultarVehiculo(String placa){
		return vehiculoServicio.consultarVehiculo(placa);
	}
	
	//En este metodo se actualiza el registro con la fecha de salida y el costo del servicio
	public Registro realizarCobro (Registro registro, int costoHoras,int costoDias, int altoCilindraje){
		long horasParqueado = diferenciaTiempo(registro.getFechaIngreso(), registro.getFechaSalida());
		long dias;
		long horasExtras;
		long costo=0;
		
		vehiculoServicio.borrarVehiculo (registro.getPlaca());
		registroServicio.borrarRegistro (registro.getPlaca());
		
		//Convertir en Funciones
		if(horasParqueado >= 24){
			dias = horasParqueado / 24;
			horasExtras = horasParqueado - (dias*24);
			if (horasExtras>9){
				costo = costoDias + (costoDias*dias); 
			}else{
				costo = (costoHoras*horasExtras) + (costoDias*dias);
			}
			
		}else if(horasParqueado < 24 && horasParqueado >= 9  ){
			costo = costoDias; 
		}
		else if(horasParqueado< 9){
			costo = costoHoras *  horasParqueado;
		}
		registro.setValor(costo+altoCilindraje);
		return registro;
	}	
	
	/*refactor inicio
	public long cobrarDias (long horasParqueado, int costoDias) {
		long dias;
		long horasExtras;
		long costo=0;
		
		if(horasParqueado >= 24){
			dias = horasParqueado / 24;
			horasExtras = horasParqueado - (dias*24);
			if (horasExtras>9){
				costo = costoDias + (costoDias*dias); 
			}else{
			}
			
			return 1L;
		}else
			return 2L;
		}
	 
	
	refactor final*/
	
	//Se toma la fecha de ingreso al parqueadero y la fecha actual y se calcula la diferencia
	public long diferenciaTiempo(Calendar fechaIngreso, Calendar fechaSalida){
        long millIngreso = fechaIngreso.getTimeInMillis();
        long millSalida = fechaSalida.getTimeInMillis();
        long millTranscurrido = millSalida - millIngreso;
        long horas = millTranscurrido/(60*60*1000); // Lo convertimos en horas
        return (horas>=1)?horas:1;
	}
       
	
}
