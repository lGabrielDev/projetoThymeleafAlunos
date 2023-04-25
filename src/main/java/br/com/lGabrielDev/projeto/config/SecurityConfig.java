package br.com.lGabrielDev.projeto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.lGabrielDev.projeto.services.UsuarioDetailsService;

@Configuration
public class SecurityConfig {

    //attributes
    @Autowired
    private UsuarioDetailsService uds;


    //configs
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .authorizeHttpRequests()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/img/**").permitAll()                
                .requestMatchers("/js/**").permitAll()
                .requestMatchers("/usuario-cadastrar").permitAll()
                .anyRequest().authenticated() // todas as outras rotas/requests vao ter permissao total
                .and()
                .csrf().disable()

                .formLogin()
                    .loginPage("/")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/home")
                    .permitAll(); // depois de logado, ele terá permissao total

                
        
        return http.build();
    }


   

    //method para bater a senha digitada com a senha hash
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(uds).passwordEncoder(new BCryptPasswordEncoder());
    }



    @Bean
    public PasswordEncoder passwordEncouEncoder(){
        return new BCryptPasswordEncoder();
    }


}
