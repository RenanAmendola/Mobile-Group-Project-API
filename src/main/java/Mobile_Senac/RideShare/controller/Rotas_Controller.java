package Mobile_Senac.RideShare.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Mobile_Senac.RideShare.model.Rotas;
import Mobile_Senac.RideShare.service.Rotas_Service;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Rota")
public class Rotas_Controller {

      @Autowired
    private Rotas_Service rotasService;

    @GetMapping
    public List<Rotas> listarTodasRotas() {
        return rotasService.listarTodasRotas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rotas> encontrarPorId(@PathVariable Long id) {
        Optional<Rotas> rota = rotasService.encontrarPorId(id);
        if (rota.isPresent()) {
            return ResponseEntity.ok(rota.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/motorista/{idMotorista}")
    public ResponseEntity<List<Rotas>> encontrarPorMotorista(@PathVariable Long idMotorista) {
    return ResponseEntity.ok(rotasService.encontrarPorMotorista(idMotorista));
    }


    @PostMapping("/adicionar")
    public ResponseEntity<Rotas> salvarRota(@RequestBody Rotas rota) {
        Rotas rotaSalva = rotasService.salvarRota(rota);
        return ResponseEntity.ok(rotaSalva);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Rotas> atualizarRota(@Valid @RequestBody Rotas rota) {
        return rotasService.atualizarRota(rota)
        .map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRota(@PathVariable Long id) {
        if (rotasService.encontrarPorId(id).isPresent()) {
            rotasService.deletarRota(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
