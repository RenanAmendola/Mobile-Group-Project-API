package Mobile_Senac.RideShare.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import Mobile_Senac.RideShare.model.Usuario_Carro;
import Mobile_Senac.RideShare.repository.Carro_Repositorio;


@Service
public class Carro_Service {

    @Autowired
    private Carro_Repositorio carroRepositorio;

    public Optional<Usuario_Carro> cadastrarCarro(Usuario_Carro carro) {
        return Optional.of(carroRepositorio.save(carro));
    }

    public Optional<Usuario_Carro> atualizarCarro(Usuario_Carro updatedCarroData) {
        Optional<Usuario_Carro> existingCarroOptional = carroRepositorio.findById(updatedCarroData.getId());

        if (existingCarroOptional.isPresent()) {
            Usuario_Carro existingCarro = existingCarroOptional.get();

            existingCarro.setModelo(updatedCarroData.getModelo());
            existingCarro.setAno_carro(updatedCarroData.getAno_carro());
            existingCarro.setCor_carro(updatedCarroData.getCor_carro());
            existingCarro.setIdUsuario(updatedCarroData.getIdUsuario());

            Usuario_Carro updatedCarro = carroRepositorio.save(existingCarro);
            return Optional.of(updatedCarro);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrado");
        }
    }
}
