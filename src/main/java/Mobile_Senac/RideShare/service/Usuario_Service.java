package Mobile_Senac.RideShare.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import Mobile_Senac.RideShare.model.Usuario;
import Mobile_Senac.RideShare.model.UsuarioLogin;
import Mobile_Senac.RideShare.repository.Usuario_Repositorio;


@Service
public class Usuario_Service {

	@Autowired
	private Usuario_Repositorio UsuRe;
	
	public Optional<Usuario> cadastrarUsuario(Usuario usuario){
		
		if(UsuRe.findByEmail(usuario.getEmail())
				.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuario ja existe!",null);

			usuario.setSenha(criptografarSenha(usuario.getSenha()));
		
		return Optional.of(UsuRe.save(usuario));										
	}
	
	public Optional<Usuario> atualizarUsuario(Usuario updatedUsuarioData) {

		Optional<Usuario> existingUsuarioOptional = UsuRe.findById(updatedUsuarioData.getId());

        if (existingUsuarioOptional.isPresent()) {
            Usuario existingUsuario = existingUsuarioOptional.get();

            Optional<Usuario> existingUsuarioByEmail = UsuRe.findByEmail(updatedUsuarioData.getEmail());
            if (existingUsuarioByEmail.isPresent() && !existingUsuarioByEmail.get().getId().equals(existingUsuario.getId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O e-mail ja esta cadastrado", null);
            }

            existingUsuario.setNome(updatedUsuarioData.getNome());
            existingUsuario.setSobrenome(updatedUsuarioData.getSobrenome());
            existingUsuario.setEmail(updatedUsuarioData.getEmail());
            existingUsuario.setTelefone(updatedUsuarioData.getTelefone());
            existingUsuario.setSenha(updatedUsuarioData.getSenha());
            existingUsuario.setTipo(updatedUsuarioData.getTipo());
            existingUsuario.setMatricula(updatedUsuarioData.getMatricula());
            existingUsuario.setCPF(updatedUsuarioData.getCPF());
            existingUsuario.setFoto(updatedUsuarioData.getFoto());
         
			existingUsuario.setSenha(criptografarSenha(updatedUsuarioData.getSenha()));
            Usuario updatedUsuario = UsuRe.save(existingUsuario);

            return Optional.of(updatedUsuario);
        } else {
           
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado", null);
        }
    }
	
	public Optional<UsuarioLogin> logarUsuario(Optional<UsuarioLogin> UsuarioLogin) {
		
		Optional<Usuario> Usuario = UsuRe.findByEmail(UsuarioLogin.get().getEmail());
		
		if (Usuario.isPresent()) {
			if(compararSenhas(UsuarioLogin.get().getSenha(),Usuario.get().getSenha())) {
				
				UsuarioLogin.get().setId(Usuario.get().getId());
				UsuarioLogin.get().setNome(Usuario.get().getNome());
				UsuarioLogin.get().setSobrenome(Usuario.get().getSobrenome());
				UsuarioLogin.get().setTelefone(Usuario.get().getTelefone());
				UsuarioLogin.get().setEmail(Usuario.get().getEmail());
				UsuarioLogin.get().setToken(gerarBasicToken(UsuarioLogin.get().getEmail(),
				UsuarioLogin.get().getSenha()));
				
				return UsuarioLogin;
			}
		}
		
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario ou senha invalido!", null);
	}
	

	
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		return senhaDigitada.equals(senhaBanco);			
	}


	private String gerarBasicToken(String email, String password) {
		String estrutura = email + ":" + password;
		byte[] estruturaBase64 = Base64.getEncoder().encode(estrutura.getBytes(StandardCharsets.US_ASCII));
		return "basic " + new String(estruturaBase64, StandardCharsets.US_ASCII);
	}

	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(senha);
		
		return senhaEncoder;
	}
	

}
