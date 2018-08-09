package estacionamiento.builder;

import estacionamiento.dominio.Registro;
import estacionamiento.entidades.RegistroEntity;

public class RegistroBuilder {

	private RegistroBuilder (){
		
	}
	
	public static Registro convertirADominio (RegistroEntity registroEntity){
		if (registroEntity == null){
			return null;
		}
		return new Registro (registroEntity.getPlaca(), registroEntity.getFechaIngreso());
	}
	
	public static RegistroEntity convertirAEntidad (Registro registro){
		if(registro==null){
			return null;
		}
		return new RegistroEntity(registro.getPlaca(), registro.getFechaIngreso() );
	}
	
}
