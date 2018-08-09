package estacionamiento.builder;

import estacionamiento.dominio.*;
import estacionamiento.entidades.*;



public class VehiculoBuilder  {
	
	
	
	private VehiculoBuilder (){
		
	}
	
	public static Vehiculo convertirADominio (VehiculoEntity vehiculoEntity){
		if (vehiculoEntity == null){
			return null;
		} 
		return new Vehiculo (vehiculoEntity.getPlaca(), vehiculoEntity.getTipo(), vehiculoEntity.getCilindraje()); 
	}
	
	public static VehiculoEntity convertirAEntidad (Vehiculo vehiculo){
		if(vehiculo==null){
			return null;
		} 
		return new VehiculoEntity(vehiculo.getPlaca(), vehiculo.getTipo(), vehiculo.getCilindraje());
	}
	

	

}
