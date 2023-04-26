package br.com.lGabrielDev.projeto.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.lGabrielDev.projeto.models.Aluno;

@Controller
public class HomeController {
    //attributes

    //home page
    @GetMapping("/home")
    public ModelAndView paginaInicial(Authentication authentication){ // Passamos um "authentication" que é o usuario logado. Assim, conseguimos exibir o "username" dele na home page.
        ModelAndView mv = new ModelAndView();
        mv.setViewName("./home/index.html"); // esse é o caminho da view/html
        mv.addObject("aluno", new Aluno());
        mv.addObject("usuarioLogado", authentication); // Usaremos esse objeto para pegar as informacoes do usuario autenticado.
        
        return mv;
    }
}


