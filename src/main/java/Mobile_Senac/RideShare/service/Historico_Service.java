package Mobile_Senac.RideShare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import Mobile_Senac.RideShare.model.Historico;
import Mobile_Senac.RideShare.repository.Historico_Repositorio;

@Service
public class Historico_Service {

    @Autowired
    private Historico_Repositorio historicoRepositorio;

    public List<Historico> listarTodosHistoricos() {
        return historicoRepositorio.findAll();
    }

    public Optional<Historico> encontrarPorId(Long id) {
        return historicoRepositorio.findById(id);
    }

    public List<Historico> encontrarPorUsuarioPrincipal(Long idUsuarioPrincipal) {
        return historicoRepositorio.findByIdUsuarioPrincipal_Id(idUsuarioPrincipal);
    }

    public Historico salvarHistorico(Historico historico) {
        return historicoRepositorio.save(historico);
    }

    public void deletarHistorico(Long id) {
        Optional<Historico> historico = historicoRepositorio.findById(id);
        if (historico.isPresent()) {
            historicoRepositorio.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Histórico não encontrado");
        }
    }

    public Optional<Historico> atualizarHistorico(Historico updatedHistoricoData) {
        Optional<Historico> existingHistoricoOptional = historicoRepositorio.findById(updatedHistoricoData.getId());

        if (existingHistoricoOptional.isPresent()) {
            Historico existingHistorico = existingHistoricoOptional.get();

            existingHistorico.setIdMotorista(updatedHistoricoData.getIdMotorista());
            existingHistorico.setidUsuarioPrincipal(updatedHistoricoData.getidUsuarioPrincipal());
            existingHistorico.setIdpassageiro1(updatedHistoricoData.getIdpassageiro1());
            existingHistorico.setIdpassageiro2(updatedHistoricoData.getIdpassageiro2());
            existingHistorico.setIdpassageiro3(updatedHistoricoData.getIdpassageiro3());
            existingHistorico.setStatus(updatedHistoricoData.getStatus());
            existingHistorico.setDataHoraCarona(updatedHistoricoData.getDataHoraCarona());

            Historico updatedHistorico = historicoRepositorio.save(existingHistorico);
            return Optional.of(updatedHistorico);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Histórico não encontrado");
        }
    }
}
