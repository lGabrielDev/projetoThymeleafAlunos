package br.com.lGabrielDev.projeto.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    //method para codificar/camuflar um password

    public static String encoder(String password){
        BCryptPasswordEncoder senhaHash = new BCryptPasswordEncoder();
        return senhaHash.encode(password);
    }
}
