package br.com.lGabrielDev.projeto.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriptografarSenha {
    
    //method para criptografar senha. Ao invés de salvar a String do usuario no banco, salvamos a senha hash.
    public static String criptografar(String senha){
        BCryptPasswordEncoder senhaHash = new BCryptPasswordEncoder();
        //transformamos a senha String em uma "Hash"
        return senhaHash.encode(senha);
    }
}
