package estacionamiento.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import estacionamiento.entidades.RegistroEntity;

@Repository
public interface RegistroRepository extends JpaRepository<RegistroEntity, Long> {
	
	public RegistroEntity findByPlaca(String placa);
	
    
}
