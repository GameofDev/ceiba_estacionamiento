package estacionamiento.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import estacionamiento.entidades.VehiculoEntity;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Long>{
	
	public List<VehiculoEntity> findByTipo (String tipo);
	
	public VehiculoEntity findByPlaca(String placa);
	
	@Transactional
	public void removeByPlaca(String placa);
	


}
