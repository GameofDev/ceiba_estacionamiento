package estacionamiento.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import estacionamiento.dominio.Registro;
import estacionamiento.dominio.Vehiculo;
import estacionamiento.servicios.Vigilante;
import estacionamiento.entidades.*;

//Se encarga de controlar todo lo que tenga que ver con los registros del parqueadero

@RestController
public class RegistroController {
	
	@Autowired
	private Vigilante vigilante;
	
	@PostMapping("/registro")
	public Registro registrarIngreso(@RequestBody Vehiculo vehiculo) {
		return vigilante.registrarIngreso(vehiculo);
	}
	
	@GetMapping("/prueba")
	public List<RegistroEntity> prueba (){
		return vigilante.consultarRegistros();
	}
	
	@GetMapping("/contador")
	public int contador (){
		return vigilante.cantidadMotos();
	}
	
	@GetMapping("/carros")
	public List <VehiculoEntity> consultarVehiculos () {
		return vigilante.consultarCarros();
	}
	
	@GetMapping("/pruebaTiempo")
	public String consultarTiempo (@RequestBody Vehiculo vehiculo) {
		return vigilante.despacharvehiculo(vehiculo.getPlaca());
	}
	
	
	
	
	
}
