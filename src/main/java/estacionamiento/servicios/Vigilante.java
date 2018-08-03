package estacionamiento.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estacionamiento.builder.*;
import estacionamiento.dominio.*;
import estacionamiento.entidades.*;
import estacionamiento.reglas_de_negocio.*;
import estacionamiento.repositorio.*;


@Service
public class Vigilante {
	
	@Autowired
	private RegistroRepository registroRepositorio;
	
	@Autowired
	private VehiculoRepository vehiculoRepositorio;
	
	/*Metodo utilizado para ingresar vehiculos al parqueadero
	 * En este metodo se evaluan las reglas de ingreso al parqueadero
	 * Utiliza dp metodos que realizan las tareas especificas de ingresarVehiculo y CrearRegistro
	 */
	public Registro registrarIngreso(Vehiculo vehiculo) {
		evaluarReglasIngreso(vehiculo);
		registrarVehiculo(vehiculo);
		return crearRegistro(vehiculo);
	}

	//Recibe un vehiculo, lo convierte en una entidad y lo envia a el repositorio 
	public void registrarVehiculo (Vehiculo vehiculo){
		vehiculoRepositorio.save(VehiculoBuilder.convertirAEntidad(vehiculo));
	}
	
	//Recibe un vehiculo, crea un registro que despues lo convierte en una entidad para enviarlo al repositorio
	public Registro crearRegistro (Vehiculo vehiculo){
		RegistroEntity registroEntity = RegistroBuilder.convertirAEntidad(new Registro(vehiculo.getPlaca(), Calendar.getInstance(), null, 0));
		return RegistroBuilder.convertirADominio(registroRepositorio.save(registroEntity));
	}
	
   //Recibe un vehiculo  y crea la lista de reglas que se les va a aplicar. Hace un ciclo envaluando el vehiculo en esa lista de reglas.
	private void evaluarReglasIngreso (Vehiculo vehiculo) {
		List<ReglaIngreso> reglas = new ArrayList<>();
		reglas.add(new ReglaCantidadParqueadero(cantidadCarros(),cantidadMotos()));
		reglas.add(new ReglaRestriccionPlaca());
		reglas.add(new ReglaTipoVehiculos());
		for (ReglaIngreso regla : reglas) {
			regla.verificarRegla(vehiculo);
	    }
	}
	
	public String despacharvehiculo (String placa){
		Registro registro = consultarRegistro(placa);
		Vehiculo vehiculo = consultarVehiculo(placa);
		if (vehiculo.getTipo().equals("moto")){
			
		}else if(vehiculo.getTipo().equals("carro")){
			
		}
		
		return "d";
	}
	
	//En este metodo se actualiza el registro con la fecha de salida y el costo del servicio
	public Registro actualizarRegistroMoto (Registro registro){
		long horasParqueado = diferenciaTiempo(registro.getFechaIngreso());
		long tiempo, dias, horasExtras;
		if(horasParqueado >= 24){
			dias = horasParqueado / 24;
			horasExtras = horasParqueado - (dias*24);
		}else if(horasParqueado < 24 && horasParqueado >= 9  ){
			horasExtras = horasParqueado - 9; 
		}
		
		
		
		return registro;//Obvio modificar
	}
	
	public Registro actualizarRegistroCarro (Registro registro){
		Long horas = diferenciaTiempo(registro.getFechaIngreso());
		
		return registro;//Modificar
	}
	
	
	
	
	//Se toma la fecha de ingreso al parqueadero y la fecha actual y se calcula la diferencia
	public long diferenciaTiempo(Calendar fechaIngreso){
		Calendar fechaSalida = Calendar.getInstance();
        long millIngreso = fechaIngreso.getTimeInMillis();
        long millSalida = fechaSalida.getTimeInMillis();
        long millTranscurrido = millSalida - millIngreso;
        
        return millTranscurrido / (60 * 60 * 1000);

	}
	
	public Vehiculo consultarVehiculo (String placa){
		return VehiculoBuilder.convertirADominio(vehiculoRepositorio.findByPlaca(placa));
	}
	
	public Registro consultarRegistro (String placa){
		return RegistroBuilder.convertirADominio(registroRepositorio.findByPlaca(placa));
	}
	          
	//Temporal
	public List<RegistroEntity> consultarRegistros (){
		return registroRepositorio.findAll();
	}
	
	//Temporal
	public List<VehiculoEntity> consultarVehiculos (String tipo){
		return vehiculoRepositorio.findAll();
	}
	
	//Temporal
	public List <VehiculoEntity> consultarCarros (){
		return vehiculoRepositorio.findByTipo("carro");
	}
	
	public int cantidadCarros (){
		return vehiculoRepositorio.findByTipo("carro").size();
	}
	
	public int cantidadMotos (){
		return vehiculoRepositorio.findByTipo("moto").size();
	}
	
	
}
