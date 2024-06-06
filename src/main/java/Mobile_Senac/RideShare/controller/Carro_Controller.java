package Mobile_Senac.RideShare.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.server.ResponseStatusException;

import Mobile_Senac.RideShare.model.Usuario_Carro;
import Mobile_Senac.RideShare.repository.Carro_Repositorio;
import Mobile_Senac.RideShare.service.Carro_Service;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Carro")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Carro_Controller {

    @Autowired
    private Carro_Service carroService;

    @Autowired
    private Carro_Repositorio carroRepositorio;

    @GetMapping("/all")
    public ResponseEntity<List<Usuario_Carro>> getAll() {
        return ResponseEntity.ok(carroRepositorio.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario_Carro> getById(@PathVariable long id) {
        return carroRepositorio.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario_Carro> postCarro(@Valid @RequestBody Usuario_Carro carro) {
        return carroService.cadastrarCarro(carro)
                .map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Usuario_Carro> putCarro(@Valid @RequestBody Usuario_Carro carro) {
        return carroService.atualizarCarro(carro)
                .map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/deletar/{id}")
    public void delete(@PathVariable long id) {
        Optional<Usuario_Carro> carro = carroRepositorio.findById(id);

        if (carro.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        carroRepositorio.deleteById(id);
    }
}
