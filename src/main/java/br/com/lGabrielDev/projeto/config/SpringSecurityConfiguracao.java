package br.com.lGabrielDev.projeto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import br.com.lGabrielDev.projeto.util.PasswordUtil;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguracao  {

    //attributes injetados
   


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //todas as configs
        http
            .httpBasic()
            .and()
            //configurando permissoes de rotas
            .authorizeHttpRequests()
                .requestMatchers("/home").permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("/img/**").permitAll()
                .requestMatchers("/js/**").permitAll()
                .requestMatchers("/css/**").permitAll()
            
            .anyRequest().authenticated() // todas as outras requisicoes serão autenticadas


            .and()

            .formLogin()
                .defaultSuccessUrl("/home")
                .permitAll();
                
                
            return http.build();
    }


    @Bean
    public PasswordUtil passwordUtil(){
        return new BCryptPasswordEncoder();
    }

}