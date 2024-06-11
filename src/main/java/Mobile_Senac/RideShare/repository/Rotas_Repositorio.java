package Mobile_Senac.RideShare.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Mobile_Senac.RideShare.model.Rotas;



public interface Rotas_Repositorio extends JpaRepository<Rotas, Long>{

    @SuppressWarnings("null")
    public Optional<Rotas> findById(Long id);

    public List<Rotas> findByIdMotorista_Id(Long idMotorista);
    
}
/*findByIdUsuarioPrincipal_Id */