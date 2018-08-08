package estacionamiento.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import estacionamiento.entidades.RegistroEntity;

@Repository
public interface RegistroRepository extends JpaRepository<RegistroEntity, Long> {
	
	public RegistroEntity findByPlaca(String placa);
	
	@Transactional
	public void removeByPlaca(String placa);

	
    
}
