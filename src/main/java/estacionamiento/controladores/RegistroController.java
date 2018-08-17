package estacionamiento.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import estacionamiento.dominio.Registro;
import estacionamiento.dominio.Vehiculo;
import estacionamiento.servicios.Vigilante;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class RegistroController {

	@Autowired
	private Vigilante vigilante;

	@PostMapping("/registro")
	public Registro registrarIngreso(@RequestBody Vehiculo vehiculo) {
		return vigilante.registrarIngreso(vehiculo);
	}

	@PostMapping("/Despachar")
	public Registro despacharVehiculo(@RequestBody Vehiculo vehiculo) {
		return vigilante.despacharVehiculo(vehiculo.getPlaca());
	}

	@GetMapping("/prueba")
	public Vehiculo prueba() {
		return vigilante.consultarVehiculo("abc123");
	}

}
