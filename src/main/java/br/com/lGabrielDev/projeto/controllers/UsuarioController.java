package br.com.lGabrielDev.projeto.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.lGabrielDev.projeto.models.Usuario;
import br.com.lGabrielDev.projeto.services.UsuarioService;
import jakarta.validation.Valid;

@Controller
public class UsuarioController {
    
    //attributes injetados
    @Autowired
    private UsuarioService us;



    // ********************** Login page  **********************
    @GetMapping("/")
    public ModelAndView loginPage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("./usuario/login.html");
        return mv;
    }



    // Login Page Error - Se o usuario errar o login e/ou password, ele será redirecionado para essa rota.
    @GetMapping("/login-error")
    public ModelAndView loginPageError(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("./usuario/login.html");
        mv.addObject("mensagemErroLogin", "Usuário ou senha incorreto");
        return mv;
    }





    // **********************  Register page  **********************
    @GetMapping("/usuario-cadastrar")
    public ModelAndView registerPage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("./usuario/cadastroUsuario.html");
        mv.addObject("usuario", new Usuario());
        return mv;
    }



    @PostMapping("/usuario-cadastrar")
    public ModelAndView receberNovoUsuario(@Valid Usuario novoUsuario, BindingResult br){

        //try catch para tratar a "UNIQUE" constraint - usuario já existente
        try{
            if(br.hasErrors()){
                ModelAndView mv = new ModelAndView();
                mv.setViewName("./usuario/cadastroUsuario.html");
                return mv;
            }
            else{
                this.us.cadastrar(novoUsuario);
                ModelAndView mv = new ModelAndView();
                mv.setViewName("redirect:/");
                return mv;
            }
        }
        //se der erro de Unique constraint, criamos um objeto para usarmos como mensagem de erro
        catch(Exception e){

            //verificamos se o "name" e/ou o "email" já existem no banco
            Optional<Usuario> uOptionalName =  this.us.findAlunoByName(novoUsuario.getName());
            Optional<Usuario> uOptionalEmail =  this.us.findAlunoByEmail(novoUsuario.getEmail());


            //se o "username" and "email" já existirem, mostramos as duas mensagens
            if(uOptionalName.isPresent() && uOptionalEmail.isPresent()){
                ModelAndView mv = new ModelAndView();
                mv.setViewName("./usuario/cadastroUsuario.html");
                mv.addObject("mensagemErroUsername", "Username já existe.");
                mv.addObject("mensagemErroEmail", "E-mail já existe.");
                return mv;
            }
            //se apenas o "username" já existir
            else if(uOptionalName.isPresent() && uOptionalEmail.isEmpty()){
                ModelAndView mv = new ModelAndView();
                mv.setViewName("./usuario/cadastroUsuario.html");
                mv.addObject("mensagemErroUsername", "Username já existe.");
                return mv;
            }

            //se apenas o "email" já existir
            else if(uOptionalName.isEmpty() && uOptionalEmail.isPresent()){
                ModelAndView mv = new ModelAndView();
                mv.setViewName("./usuario/cadastroUsuario.html");
                mv.addObject("mensagemErroEmail", "E-mail já existe.");
                return mv;
            }
            
            //se tiver tudo ok, cadastramos esse novo usuário
            this.us.cadastrar(novoUsuario);
            ModelAndView mv = new ModelAndView();
            mv.setViewName("redirect:/");
            return mv;
        }
    }
}