package br.com.lGabrielDev.projeto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lGabrielDev.projeto.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    //queries
    
    //procurar usuario pelo - "username"
    public Optional<Usuario> findByName(String name);


    //procurar usuario pelo - "email"
    public Optional<Usuario> findByEmail(String email);
    
}
