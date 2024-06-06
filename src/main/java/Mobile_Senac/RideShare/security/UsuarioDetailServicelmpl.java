package Mobile_Senac.RideShare.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Mobile_Senac.RideShare.model.Usuario;
import Mobile_Senac.RideShare.repository.Usuario_Repositorio;

@Service
public class UsuarioDetailServicelmpl implements UserDetailsService{

    	
	@Autowired
	private Usuario_Repositorio UsuRe;
	
	@Override
	public UserDetails loadUserByUsername(String userEmail)
	throws UsernameNotFoundException{
		
		Optional<Usuario> usuario = UsuRe.findByEmail(userEmail);
		usuario.orElseThrow(() -> new UsernameNotFoundException(userEmail + "Não encontrado"));
		
		return usuario.map(UserDetailslmpl::new).get();
	}

}
