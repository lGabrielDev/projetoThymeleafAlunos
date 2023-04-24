package br.com.lGabrielDev.projeto.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.lGabrielDev.projeto.enums.Curso;
import br.com.lGabrielDev.projeto.enums.Status;
import br.com.lGabrielDev.projeto.enums.Turno;
import br.com.lGabrielDev.projeto.models.Aluno;
import br.com.lGabrielDev.projeto.services.AlunoService;
import jakarta.validation.Valid;

@Controller
public class AlunoController {
    //attributes

    @Autowired
    private AlunoService as;

    //requests

    // ************************ CREATE ************************
    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(Aluno a){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("./aluno/cadastro/formulario_cadastro.html");
        mv.addObject("aluno", new Aluno());
        mv.addObject("cursos", Curso.values()); // todos as constants da enum "Curso"
        mv.addObject("statusBolado", Status.values()); // // todos as constants da enum "Status"
        mv.addObject("turnos", Turno.values()); // // todos as constants da enum "Turno"
        return mv;
    }


    @PostMapping("/cadastrar")
    public ModelAndView receberCadastro(@Valid Aluno a, BindingResult br){

        if(br.hasErrors()){
            ModelAndView mv = new ModelAndView();
            mv.setViewName("./aluno/cadastro/formulario_cadastro.html");
            mv.addObject("cursos", Curso.values()); // todos as constants da enum "Curso"
            mv.addObject("statusBolado", Status.values()); // // todos as constants da enum "Status"
            mv.addObject("turnos", Turno.values()); // // todos as constants da enum "Turno"
            
            return mv;
        }
        else{
            this.as.cadastrar(a);
            ModelAndView mv = new ModelAndView();
            mv.setViewName("./aluno/listarAlunos/listarAlunos.html");
            mv.addObject("listaAlunos", this.as.listarTodos());
            mv.addObject("aluno", new Aluno());
            mv.addObject("mensagemCadastradoComSucesso", "Aluno cadastrado com sucesso!"); // mensagem de sucesso
            return mv;
        }
    }








    // ************************ READ ************************
    
    // READ ALL -- Todos os alunos
    @GetMapping("/alunos")
    public ModelAndView listarAlunos(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("./aluno/listarAlunos/listarAlunos.html");
        mv.addObject("listaAlunos", this.as.listarTodos());
        mv.addObject("aluno", new Aluno());
        mv.addObject("listaTitle", "Alunos:"); // <h2> title
        
        return mv;
    }

    

    // READ - alunos ativos
    @GetMapping("/alunos-ativos")
    public ModelAndView listarAlunosAtivos(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("./aluno/listarAlunos/listarAlunos.html");
        mv.addObject("listaAlunos", this.as.listarAlunosAtivos());
        mv.addObject("aluno", new Aluno());
        mv.addObject("listaTitle", "Alunos Ativos:"); // <h2> title
        return mv;
    }


    // READ - alunos inativos
    @GetMapping("/alunos-inativos")
    public ModelAndView listarAlunosInativos(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("./aluno/listarAlunos/listarAlunos.html");
        mv.addObject("listaAlunos", this.as.listarAlunosInativos());
        mv.addObject("aluno", new Aluno());
        mv.addObject("listaTitle", "Alunos Inativos:"); // <h2> title
        return mv;
    }



    // READ - alunos cancelados
    @GetMapping("/alunos-cancelados")
        public ModelAndView listarAlunosCancelados(){
            ModelAndView mv = new ModelAndView();
            mv.setViewName("./aluno/listarAlunos/listarAlunos.html");
            mv.addObject("listaAlunos", this.as.listarAlunosCancelados());
            mv.addObject("aluno", new Aluno());
            mv.addObject("listaTitle", "Alunos Cancelados:"); // <h2> title
            return mv;
        }



    // READ - página de pesquisa. Aqui temos todas as opcoes de pesquisa - (alunos ativos, alunos inativos, alunos cancelados)
    @GetMapping("/alunos/pesquisas")
    public ModelAndView AllPesquisas(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("./aluno/pesquisa/pesquisar_aluno.html");
        mv.addObject("aluno", new Aluno());
        return mv;
    }


    
    // READ - alunos filtrando por "name"
    @PostMapping("/alunos/pesquisas")
    public ModelAndView recebendoName(Aluno a){
        List<Aluno> alunos;

        //se o usuario nao digitar nada, enviamos uma lista com todos os registros
        if(a.getName() == null || a.getName().isBlank()){
            alunos = this.as.listarTodos();
        }
        else{
            alunos = this.as.listarAlunosByName(a.getName());
        }

        ModelAndView mv = new ModelAndView();
            mv.setViewName("./aluno/listarAlunos/listarAlunos.html");
            mv.addObject("listaAlunos", alunos);
            mv.addObject("aluno", new Aluno());
            mv.addObject("listaTitle", "Alunos:"); // <h2> title
        return mv;
    }








    // ************************ UPDATE ************************
    @GetMapping("/alunos/{id}")
    public ModelAndView editarAluno(@PathVariable(value = "id") Long id, Aluno a ){

        Optional<Aluno> aOptional = this.as.procurarAluno(id);
        if(aOptional.isEmpty()){
            ModelAndView mv1 = new ModelAndView("redirect:/home");
            return mv1;
        }
        else{
            ModelAndView mv2 = new ModelAndView();
            mv2.setViewName("./aluno/editar/formulario_editar.html");
            mv2.addObject("aluno", aOptional.get());
            mv2.addObject("cursos", Curso.values()); // todos as constants da enum "Curso"
            mv2.addObject("statusBolado", Status.values()); // // todos as constants da enum "Status"
            mv2.addObject("turnos", Turno.values()); // // todos as constants da enum "Turno"
            return mv2;
        }  
    }



    @PostMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable(value = "id") Long id , @Valid Aluno alunoNovo, BindingResult br){

        if(br.hasErrors()){
            ModelAndView mv = new ModelAndView();
            mv.setViewName("./aluno/formulario_editar.html");
            mv.addObject("cursos", Curso.values()); // todos as constants da enum "Curso"
            mv.addObject("statusBolado", Status.values()); // // todos as constants da enum "Status"
            mv.addObject("turnos", Turno.values()); // // todos as constants da enum "Turno"
            return mv;
        }
        else{
            this.as.editar(id, alunoNovo);

            ModelAndView mv = new ModelAndView();
            mv.setViewName("./aluno/listarAlunos/listarAlunos.html");
            mv.addObject("listaAlunos", this.as.listarTodos());
            mv.addObject("aluno", new Aluno());
            mv.addObject("mensagemEditadoComSucesso", "Aluno atualizado com sucesso!"); // objeto para usarmos como mensagem de sucesso
            return mv;
        }
    }








// ************************ DELETE ************************

    @GetMapping("/alunos/excluir/{id}")
    public ModelAndView excluir(@PathVariable(value = "id") Long id){
        
       Boolean deletadoComSucesso = this.as.deletar(id);

        if(deletadoComSucesso){
            this.as.deletar(id);
            ModelAndView mv = new ModelAndView();
            mv.setViewName("./aluno/listarAlunos/listarAlunos.html");
            mv.addObject("listaAlunos", this.as.listarTodos());
            mv.addObject("aluno", new Aluno());
            mv.addObject("mensagemDeletadoComSucesso", "Registro deletado com sucesso!"); // objeto para usarmos como mensagem de sucesso
            return mv;
        }
        else{
            ModelAndView mv = new ModelAndView();
            mv.setViewName("redirect:/home");
            return mv;
        }
    }
}


