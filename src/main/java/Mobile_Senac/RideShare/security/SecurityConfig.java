package Mobile_Senac.RideShare.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz

                .requestMatchers("/Usuario/logar").permitAll()
                .requestMatchers("/Usuario/cadastrar").permitAll()




                .requestMatchers("/Rota/adicionar").permitAll()
                .requestMatchers("/Rota").permitAll()
                .requestMatchers("/Rota/{id}").permitAll()
                .requestMatchers("/Rota/motorista/{id}").permitAll()
                .requestMatchers("/Rota/atualizar").permitAll()
                .requestMatchers("/historico").permitAll()
                .requestMatchers("/historico/criar_historico").permitAll()
                .requestMatchers("/historico/{id}").permitAll()
                .requestMatchers("/Usuario/all").permitAll()
                .requestMatchers("/Usuario/atualizar").permitAll()
                .requestMatchers("/Usuario/{id}").permitAll()
                .requestMatchers("/Carro/cadastrar").permitAll()
                .requestMatchers("/Carro/{id}").permitAll()
                .requestMatchers("/Carro/all").permitAll()
                .requestMatchers("/Carro/atualizar").permitAll()
                .requestMatchers("/Carro/deletar/{id}").permitAll()
                .requestMatchers("/Endereco/all").permitAll()  
                .requestMatchers("/Endereco/usuario/{idUsuario}").permitAll()   
                .requestMatchers("/Endereco/cadastrar").permitAll() 
                .requestMatchers("/Endereco/atualizar").permitAll() 
                .requestMatchers("/Endereco/delete/{id}").permitAll() 
                .requestMatchers("/Endereco/{id}").permitAll() 
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/logar")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
                registry.addMapping("/**") // Permite CORS em todos os endpoints
                        .allowedOrigins("*") // Permite requisições de qualquer origem
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                        .allowedHeaders("*"); // Cabeçalhos permitidos
            }
        };
    }

    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    public UserDetailsService userDetailsService() {
        @SuppressWarnings("deprecation")
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("Usuario")
            .build();

        return new InMemoryUserDetailsManager(user);
    }
}
