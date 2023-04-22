package br.com.lGabrielDev.projeto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    //attributes

    //requests
    
    //home page
    @GetMapping("/home")
    public ModelAndView paginaInicial(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("./home/index.html"); // esse é o caminho da view/html
        return mv;
    }
}
