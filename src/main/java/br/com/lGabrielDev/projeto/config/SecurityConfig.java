package br.com.lGabrielDev.projeto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.lGabrielDev.projeto.services.UsuarioDetailsService;

@Configuration
public class SecurityConfig {

    //attributes
    @Autowired
    private UsuarioDetailsService uds;


    //configs
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // request permissions
        http
            .authorizeHttpRequests()
                .requestMatchers("/css/**", "img/**").permitAll()           
                .requestMatchers("/js/**").permitAll()
                .requestMatchers("/usuario-cadastrar").permitAll()
                .anyRequest().authenticated() // todas as outras rotas/requests vao ter permissao total
                .and()
                .csrf().disable();

            //login config
            http
                .formLogin()
                    .loginPage("/")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/home")
                    .failureUrl("/login-error") // se o usuário errar o login, vamos redirecionar para uma outra rota/view. Nesse caso, essa rota vai exibir uma copia da view de login, com a diferenca de ter 1 <div> adicional, contendo a mensagem de erro de login.
                    .permitAll(); // depois de logado, ele terá permissao total

            //logout config
            http
                .logout()
                    .logoutRequestMatcher(
                        new AntPathRequestMatcher("/logout", "GET")
                    )
                .logoutSuccessUrl("/"); // sempre que fizer logout, chamamos a rota "/" .
        
        return http.build();    
    }           


        

    //method para conferir a senha digitada com a senha hash
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        //primeiro tipo de usuário:
        auth.userDetailsService(uds).passwordEncoder(new BCryptPasswordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncouEncoder(){
        return new BCryptPasswordEncoder();
    }
}
