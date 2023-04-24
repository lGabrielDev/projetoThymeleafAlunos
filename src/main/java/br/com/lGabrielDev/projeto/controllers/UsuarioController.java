package br.com.lGabrielDev.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.lGabrielDev.projeto.models.Usuario;
import br.com.lGabrielDev.projeto.services.UsuarioService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@Controller
public class UsuarioController {
    
    //attributes injetados
    @Autowired
    private UsuarioService us;




    // ***********  Login page  ***********
    @GetMapping("/")
    public ModelAndView loginPage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("./usuario/login.html");
        return mv;
    }




    // ***********  Register page  ***********
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
            ModelAndView mv = new ModelAndView();
            mv.setViewName("./usuario/cadastroUsuario.html");
            mv.addObject("mensagemErroUsername", "Username já existe.");
            return mv;
        }
       
    }
}