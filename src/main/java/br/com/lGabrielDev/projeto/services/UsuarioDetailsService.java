package br.com.lGabrielDev.projeto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.lGabrielDev.projeto.models.Usuario;
import br.com.lGabrielDev.projeto.models.UsuarioDetails;
import br.com.lGabrielDev.projeto.repositories.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    UsuarioRepository ur;

    //method implementado da interface "UserDetailsService". Esse method é chamado automaticamente pelo Spring security para autenticar.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> uOptional = this.ur.findByName(username);

        if(uOptional.isEmpty()){
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }
        else{
            Usuario usuarioDeFato = uOptional.get();
            UsuarioDetails ud = new UsuarioDetails(usuarioDeFato);
            return ud;
        }
        
    }
    
}
