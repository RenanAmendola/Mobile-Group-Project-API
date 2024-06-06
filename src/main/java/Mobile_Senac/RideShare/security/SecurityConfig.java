package Mobile_Senac.RideShare.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/Usuario/all").permitAll()
                .requestMatchers("/Usuario/logar").permitAll()
                .requestMatchers("/Usuario/cadastrar").permitAll()
                .requestMatchers("/Carro/cadastrar").permitAll()
                .requestMatchers("/Carro/{id}").permitAll()
                .requestMatchers("/Carro/all").permitAll()
                .requestMatchers("/Carro/atualizar").permitAll()
                .requestMatchers("/Carro/deletar/{id}").permitAll()
                .requestMatchers("/Endereco/all").permitAll()  /*check */
                .requestMatchers("/Endereco/usuario/{idUsuario}").permitAll()   /*check */
                .requestMatchers("/Endereco/cadastrar").permitAll() /*check */
                .requestMatchers("/Endereco/atualizar").permitAll() /*check */
                .requestMatchers("/Endereco/delete/{id}").permitAll() /*check */
                .requestMatchers("/Endereco/{id}").permitAll() /*check */
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/logar")
                .permitAll()
            );

        return http.build();
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
