package Mobile_Senac.RideShare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Mobile_Senac.RideShare.model.Historico;


public interface Historico_Repositorio extends JpaRepository<Historico, Long>{

    @SuppressWarnings("null")
    public Optional<Historico> findById(Long id);

    public List<Historico> findByIdUsuarioPrincipal_Id(Long idUsuarioPrincipal);

    
}
