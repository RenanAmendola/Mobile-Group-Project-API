package Mobile_Senac.RideShare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Mobile_Senac.RideShare.model.Usuario_Carro;




public interface Carro_Repositorio extends JpaRepository<Usuario_Carro, Long> {

    @SuppressWarnings("null")
    public Optional<Usuario_Carro> findById(Long id);


    public Optional<Usuario_Carro> findByIdUsuarioId(Long idUsuario);
}
