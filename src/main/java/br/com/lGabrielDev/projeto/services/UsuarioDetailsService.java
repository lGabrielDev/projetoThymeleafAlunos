package br.com.lGabrielDev.projeto.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.lGabrielDev.projeto.models.Usuario;
import br.com.lGabrielDev.projeto.models.UsuarioDetails;
import br.com.lGabrielDev.projeto.repositories.UsuarioRepository;

public class UsuarioDetailsService implements UserDetailsService{

    //attributes injetados
    @Autowired
    private UsuarioRepository ur;

    //method implementado da "UserDetailsService"
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<Usuario> uOptional = this.ur.findByName(name);

        if(uOptional.isEmpty()){
            throw new UsernameNotFoundException("Usuario não encontrado.");
        }
        else{
            Usuario usuario = uOptional.get(); // criamos um usuario de fato
            UsuarioDetails usuarioDetails = new UsuarioDetails(usuario); // meu "user details" vai receber esse usuario no constructor
            return usuarioDetails;
        }
    }
    
}
