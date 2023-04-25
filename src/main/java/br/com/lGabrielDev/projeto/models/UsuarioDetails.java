package br.com.lGabrielDev.projeto.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioDetails implements UserDetails {

    //attribute
    private Usuario usuario;

    //constructor
    public UsuarioDetails(Usuario usuario){
        this.usuario = usuario;
    }


    //methods implementados da interface "UserDetails"
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
        
    }


    @Override
    public String getUsername() {
        return this.usuario.getName(); // vamos autenticar através do "name" do usuário
    }

    @Override
    public String getPassword() {
        return this.usuario.getPassword();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
