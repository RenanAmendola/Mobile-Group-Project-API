package Mobile_Senac.RideShare.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import Mobile_Senac.RideShare.model.Historico;
import Mobile_Senac.RideShare.service.Historico_Service;

@RestController
@RequestMapping("/historico")
public class Historico_Controller {

    @Autowired
    private Historico_Service historicoService;

    @GetMapping
    public ResponseEntity<List<Historico>> listarTodos() {
        List<Historico> historicos = historicoService.listarTodosHistoricos();
        return ResponseEntity.ok(historicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historico> encontrarPorId(@PathVariable Long id) {
        Optional<Historico> historico = historicoService.encontrarPorId(id);
        return historico.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/Usuario/{idUsuarioPrincipal}")
    public ResponseEntity<List<Historico>> encontrarPorUsuarioPrincipal(@PathVariable Long idUsuarioPrincipal) {
        List<Historico> historicos = historicoService.encontrarPorUsuarioPrincipal(idUsuarioPrincipal);
        return ResponseEntity.ok(historicos);
    }

    @PostMapping("/criar_historico")
    public ResponseEntity<Historico> salvar(@RequestBody Historico historico) {
        Historico novoHistorico = historicoService.salvarHistorico(historico);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoHistorico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Historico> atualizar(@PathVariable Long id, @RequestBody Historico historico) {
        historico.setId(id);
        Optional<Historico> historicoAtualizado = historicoService.atualizarHistorico(historico);
        return historicoAtualizado.map(ResponseEntity::ok)
                                  .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            historicoService.deletarHistorico(id);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
