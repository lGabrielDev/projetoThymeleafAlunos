package br.com.lGabrielDev.projeto.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriptografarSenha {
    
    //method para criptografar senha
    public static String criptografar(String senha){
        BCryptPasswordEncoder senhaHash = new BCryptPasswordEncoder();
        //transformamos a senha String em uma "Hash"
        return senhaHash.encode(senha);
    }
}
