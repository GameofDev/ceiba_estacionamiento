package estacionamiento.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import estacionamiento.entidades.RegistroEntity;

@Repository
public interface RegistroRepository extends JpaRepository<RegistroEntity, Long> {
	
	public RegistroEntity findByPlaca(String placa);
	//public RegistroEntity findById(String placa);
	
		//public int cantidadTipoVehiculoEnParqueadero(@Param(value = "ID_TIPO_VEHICULO") Long tipoVehiculo);
	
    
}
