package estacionamiento.servicios;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estacionamiento.builder.RegistroBuilder;
import estacionamiento.dominio.Registro;
import estacionamiento.dominio.Vehiculo;
import estacionamiento.entidades.RegistroEntity;
import estacionamiento.repositorio.RegistroRepository;

@Service
public class RegistroServicio {
	
	@Autowired
	private RegistroRepository registroRepositorio;
	

	
	public void borrarRegistro (String placa){
		registroRepositorio.removeByPlaca(placa);
	}
	
	//Recibe un vehiculo, crea un registro que despues lo convierte en una entidad para enviarlo al repositorio
	public Registro crearRegistro (Vehiculo vehiculo){
		RegistroEntity registroEntity = RegistroBuilder.convertirAEntidad(new Registro(vehiculo.getPlaca(), Calendar.getInstance(), null, 0));
		return RegistroBuilder.convertirADominio(registroRepositorio.save(registroEntity));
	} 
	
	public List<RegistroEntity> consultarRegistros (){
		return registroRepositorio.findAll();
	}
	
	public Registro consultarRegistro (String placa){
		return RegistroBuilder.convertirADominio(registroRepositorio.findByPlaca(placa));
	}
	  

}
