package estacionamiento.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import estacionamiento.builder.VehiculoBuilder;
import estacionamiento.dominio.Vehiculo;
import estacionamiento.entidades.VehiculoEntity;
import estacionamiento.repositorio.VehiculoRepository;

public class VehiculoServicio {
	
	@Autowired
	private VehiculoRepository vehiculoRepositorio;
	
	
	public void registrarVehiculo (Vehiculo vehiculo){
		vehiculoRepositorio.save(VehiculoBuilder.convertirAEntidad(vehiculo));
	}
	
	public void borrarVehiculo (String placa){
		vehiculoRepositorio.removeByPlaca(placa);
	}
	
	public Vehiculo consultarVehiculo (String placa){
		return VehiculoBuilder.convertirADominio(vehiculoRepositorio.findByPlaca(placa));
	}
	
	public List<VehiculoEntity> consultarVehiculos (String tipo){
		return vehiculoRepositorio.findAll();
	}
	
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
