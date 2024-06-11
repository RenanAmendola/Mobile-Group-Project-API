package Mobile_Senac.RideShare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import Mobile_Senac.RideShare.model.Rotas;
import Mobile_Senac.RideShare.repository.Rotas_Repositorio;

@Service
public class Rotas_Service {

    @Autowired
    private Rotas_Repositorio rotasRepositorio;

    public List<Rotas> listarTodasRotas() {
        return rotasRepositorio.findAll();
    }

    public Optional<Rotas> encontrarPorId(Long id) {
        return rotasRepositorio.findById(id);
    }

    public List<Rotas> encontrarPorMotorista(Long idMotorista) {
        return rotasRepositorio.findByIdMotorista_Id(idMotorista);
    }

    public Rotas salvarRota(Rotas rota) {
        return rotasRepositorio.save(rota);
    }

    public void deletarRota(Long id) {
        rotasRepositorio.deleteById(id);
    }


    public Optional<Rotas> atualizarRota(Rotas updatedRotaData) {
        Optional<Rotas> existingRotaOptional = rotasRepositorio.findById(updatedRotaData.getId());

        if (existingRotaOptional.isPresent()) {
            Rotas existingEndereco = existingRotaOptional.get();

            existingEndereco.setIdMotorista(updatedRotaData.getIdMotorista());
            existingEndereco.setInicio(updatedRotaData.getInicio());
            existingEndereco.setDestino(updatedRotaData.getDestino());
            existingEndereco.setParada1(updatedRotaData.getParada1());
            existingEndereco.setParada2(updatedRotaData.getParada2());
            existingEndereco.setParada3(updatedRotaData.getParada3());
            existingEndereco.setParada4(updatedRotaData.getParada4());
            existingEndereco.setHorario(updatedRotaData.getHorario());

            Rotas updatedEndereco = rotasRepositorio.save(existingEndereco);
            return Optional.of(updatedEndereco);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rota não encontrado");
        }
    } 
}
