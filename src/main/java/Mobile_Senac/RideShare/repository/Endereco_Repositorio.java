package Mobile_Senac.RideShare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Mobile_Senac.RideShare.model.Usuario_Endereco;

public interface Endereco_Repositorio extends JpaRepository<Usuario_Endereco, Long> {

    @SuppressWarnings("null")
    public Optional<Usuario_Endereco> findById(Long id);
    
    public List<Usuario_Endereco> findByIdUsuarioId(Long idUsuario);
    
}