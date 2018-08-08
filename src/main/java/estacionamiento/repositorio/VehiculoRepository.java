package estacionamiento.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import estacionamiento.entidades.RegistroEntity;
import estacionamiento.entidades.VehiculoEntity;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Long>{
	
	public List<VehiculoEntity> findByTipo (String tipo);
	public VehiculoEntity findByPlaca(String placa);
	public VehiculoEntity findById(String placa);

}
