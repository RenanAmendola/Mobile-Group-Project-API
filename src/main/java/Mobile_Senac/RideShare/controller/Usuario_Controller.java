package Mobile_Senac.RideShare.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import Mobile_Senac.RideShare.model.Usuario;
import Mobile_Senac.RideShare.model.UsuarioLogin;
import Mobile_Senac.RideShare.repository.Usuario_Repositorio;
import Mobile_Senac.RideShare.service.Usuario_Service;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/Usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Usuario_Controller {

@Autowired
private Usuario_Service UsuarioService;
	
@Autowired
private Usuario_Repositorio UsuRe;


@GetMapping("/all")
public ResponseEntity<List<Usuario>> getAll(){
	return ResponseEntity.ok(UsuRe.findAll());
}


@GetMapping("/{id}")
public ResponseEntity<Usuario> getById(@PathVariable long id){
	return UsuRe.findById(id)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.notFound().build());
}

@PostMapping("/logar")
public ResponseEntity<UsuarioLogin> autenticationUsuario(@RequestBody Optional<UsuarioLogin> usuario){
	
	return UsuarioService.logarUsuario(usuario)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());	
}

	
@PostMapping("/cadastrar")
public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario){
	return UsuarioService.cadastrarUsuario(usuario)
			.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
}
	
@PutMapping("/atualizar")
public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario){
	return UsuarioService.atualizarUsuario(usuario)
			.map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/deletar/{id}")
		public void delete(@PathVariable long id) {
		Optional<Usuario> usuario = UsuRe.findById(id);
		
			if(usuario.isEmpty())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
				UsuRe.deleteById(id);
	}

}
