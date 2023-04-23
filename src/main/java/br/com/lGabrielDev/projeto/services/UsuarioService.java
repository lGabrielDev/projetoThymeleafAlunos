package br.com.lGabrielDev.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lGabrielDev.projeto.models.Usuario;
import br.com.lGabrielDev.projeto.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    
    //attributes injetados
    @Autowired
    private UsuarioRepository ur;

    

    // *************** CADASTRAR *******************
    public void cadastrar(Usuario u){
        this.ur.save(u);
    }
}
