package br.com.lGabrielDev.projeto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.lGabrielDev.projeto.models.Aluno;

@Controller
public class HomeController {
    //attributes

    //requests
    
    //home page
    @GetMapping("/home")
    public ModelAndView paginaInicial(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("./home/index.html"); // esse é o caminho da view/html
        mv.addObject("aluno", new Aluno());
        return mv;
    }
}


