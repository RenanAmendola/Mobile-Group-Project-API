package Mobile_Senac.RideShare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import Mobile_Senac.RideShare.model.Usuario_Endereco;
import Mobile_Senac.RideShare.repository.Endereco_Repositorio;

@Service
public class Endereco_Service {

     @Autowired
    private Endereco_Repositorio enderecoRepositorio;

    public Optional<Usuario_Endereco> cadastrarEndereco(Usuario_Endereco endereco) {
        return Optional.of(enderecoRepositorio.save(endereco));
    }

    public Optional<Usuario_Endereco> atualizarEndereco(Usuario_Endereco updatedEnderecoData) {
        Optional<Usuario_Endereco> existingEnderecoOptional = enderecoRepositorio.findById(updatedEnderecoData.getId());

        if (existingEnderecoOptional.isPresent()) {
            Usuario_Endereco existingEndereco = existingEnderecoOptional.get();

            existingEndereco.setRua(updatedEnderecoData.getRua());
            existingEndereco.setBairro(updatedEnderecoData.getBairro());
            existingEndereco.setNumero(updatedEnderecoData.getNumero());
            existingEndereco.setCep(updatedEnderecoData.getCep());
            existingEndereco.setIdUsuario(updatedEnderecoData.getIdUsuario());

            Usuario_Endereco updatedEndereco = enderecoRepositorio.save(existingEndereco);
            return Optional.of(updatedEndereco);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado");
        }
    }

    public void deletarEndereco(Long id) {
        Optional<Usuario_Endereco> endereco = enderecoRepositorio.findById(id);

        if (endereco.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado");

        enderecoRepositorio.deleteById(id);
    }

    public List<Usuario_Endereco> listarEnderecosPorUsuario(Long idUsuario) {
        return enderecoRepositorio.findByIdUsuarioId(idUsuario);
    }

    public Optional<Usuario_Endereco> findById(Long id) {
        return enderecoRepositorio.findById(id);
    }


}
