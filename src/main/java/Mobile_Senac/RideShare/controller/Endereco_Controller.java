package Mobile_Senac.RideShare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import Mobile_Senac.RideShare.model.Usuario_Endereco;
import Mobile_Senac.RideShare.repository.Endereco_Repositorio;
import Mobile_Senac.RideShare.service.Endereco_Service;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/Endereco")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Endereco_Controller {

    @Autowired
    private Endereco_Service enderecoService;

    @Autowired
    private Endereco_Repositorio enderecoRepositorio;

    @GetMapping("/all")
    public ResponseEntity<List<Usuario_Endereco>> getAll() {
        return ResponseEntity.ok(enderecoRepositorio.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario_Endereco> getById(@PathVariable long id) {
        return enderecoService.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }



    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Usuario_Endereco>> getByUsuarioId(@PathVariable long idUsuario) {
        return ResponseEntity.ok(enderecoService.listarEnderecosPorUsuario(idUsuario));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario_Endereco> postEndereco(@Valid @RequestBody Usuario_Endereco endereco) {
        return enderecoService.cadastrarEndereco(endereco)
                .map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Usuario_Endereco> putEndereco(@Valid @RequestBody Usuario_Endereco endereco) {
        return enderecoService.atualizarEndereco(endereco)
                .map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        enderecoService.deletarEndereco(id);
    }


}
