package Mobile_Senac.RideShare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Mobile_Senac.RideShare.model.Usuario;

public interface Usuario_Repositorio extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByEmail(String email);



}
