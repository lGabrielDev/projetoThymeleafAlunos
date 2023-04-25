package br.com.lGabrielDev.projeto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lGabrielDev.projeto.models.Usuario;
import br.com.lGabrielDev.projeto.repositories.UsuarioRepository;
import br.com.lGabrielDev.projeto.util.PasswordUtil;

@Service
public class UsuarioService {
    
    //attributes injetados
    @Autowired
    private UsuarioRepository ur;

    

    // *************** CADASTRAR *******************
    public void cadastrar(Usuario u){
        //criptografamos a senha do usuario em uma senha hash
        String senhaHash = PasswordUtil.encoder(u.getPassword());
        u.setPassword(senhaHash);
        this.ur.save(u);
    }


    // *************** READ *******************

    //procurar usuario pelo - "username"
    public Optional<Usuario> findAlunoByName(String name){
        Optional<Usuario> uOptional = this.ur.findByName(name);
        return uOptional;
    }

    //procurar usuario pelo - "email"
    public Optional<Usuario> findAlunoByEmail(String email){
        Optional<Usuario> uOptional = this.ur.findByEmail(email);
        return uOptional;
    }
}
