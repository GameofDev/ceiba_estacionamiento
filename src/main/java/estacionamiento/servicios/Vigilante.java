package estacionamiento.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import estacionamiento.dominio.*;
import estacionamiento.reglas_de_negocio.*;



@Service
public class Vigilante {
	
	private Parqueadero parqueadero;
		
	@Autowired
	private RegistroServicio registroServicio;
	
	@Autowired
	private VehiculoServicio vehiculoServicio;
	
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
	private void evaluarReglasIngreso (Vehiculo vehiculo) {
		List<ReglaIngreso> reglas = new ArrayList<>();
		reglas.add(new ReglaCantidadParqueadero(vehiculoServicio.cantidadCarros(),vehiculoServicio.cantidadMotos()));
		reglas.add(new ReglaRestriccionPlaca());
		reglas.add(new ReglaTipoVehiculos());
		for (ReglaIngreso regla : reglas) {
			regla.verificarRegla(vehiculo);
	    }
	}
	

	//Debe evaluar la excepciones de salida: La placa no existe. 
	public Registro despacharVehiculo (String placa){
		Registro registro = registroServicio.consultarRegistro(placa);
		Vehiculo vehiculo = vehiculoServicio.consultarVehiculo(placa);
		int altoCilindraje=0;
		if (vehiculo.getTipo().equals("moto")){
			if(vehiculo.getCilindraje()>500){
				altoCilindraje = parqueadero.getExtraMotos();
			}
			return realizarCobro(registro,parqueadero.getCostoMotosHora(),parqueadero.getCostoMotosDia(),altoCilindraje);
		}
		return realizarCobro(registro,parqueadero.getCostoCarrosHora(),parqueadero.getCostoCarrosDia(),altoCilindraje);
	}
	
	
	
	//En este metodo se actualiza el registro con la fecha de salida y el costo del servicio
	public Registro realizarCobro (Registro registro, int costoHoras,int costoDias, int altoCilindraje){
		long horasParqueado = diferenciaTiempo(registro.getFechaIngreso());
		long dias;
		long horasExtras;
		double costo=0;
		
		vehiculoServicio.borrarVehiculo (registro.getPlaca());
		registroServicio.borrarRegistro (registro.getPlaca());
		
		if(horasParqueado >= 24){
			dias = horasParqueado / 24;
			horasExtras = horasParqueado - (dias*24);
			costo = (costoHoras*horasExtras) + (costoDias*dias);
		}else if(horasParqueado < 24 && horasParqueado >= 9  ){
			costo = costoDias; 
		}else if(horasParqueado< 9){
			costo = costoHoras *  horasParqueado;
		}
		registro.setValor(costo+altoCilindraje);
		registro.setFechaSalida(Calendar.getInstance());
		return registro;
	}	
	
	//Se toma la fecha de ingreso al parqueadero y la fecha actual y se calcula la diferencia
	public long diferenciaTiempo(Calendar fechaIngreso){
		Calendar fechaSalida = Calendar.getInstance();
        long millIngreso = fechaIngreso.getTimeInMillis();
        long millSalida = fechaSalida.getTimeInMillis();
        long millTranscurrido = millSalida - millIngreso;
        long horas = millTranscurrido/(60*60*1000); // Lo convertimos en horas
        return (horas>=1)?horas:1;
	}
	
        
	
}
