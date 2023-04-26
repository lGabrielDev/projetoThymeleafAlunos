package br.com.lGabrielDev.projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lGabrielDev.projeto.models.Aluno;
import br.com.lGabrielDev.projeto.repositories.AlunoRepository;

@Service
public class AlunoService {

    //attributes injetados
    @Autowired
    private AlunoRepository ar;


    // ************************** CREATE **************************
    public void cadastrar(Aluno a){
        this.ar.save(a);
    }




    // ************************** READ **************************

    // ***** READ ALL ****
    public List<Aluno> listarTodos(){
        List<Aluno> alunos = this.ar.findAll();
        return alunos;
    }


    // ***** READ aluno pelo ID ****
    public Optional<Aluno> procurarAluno(Long id) {
        Optional<Aluno> aOptional = this.ar.findById(id);
        return aOptional;
    }



    // ***** READ - Alunos ATIVOS ****
    public List<Aluno> listarAlunosAtivos(){
        List<Aluno> alunos = this.ar.findAlunosAtivos();
        return alunos;
    }


    // ***** READ - Alunos INATIVOS ****
    public List<Aluno> listarAlunosInativos(){
        List<Aluno> alunos = this.ar.findAlunosInativos();
        return alunos;
    }


    // ***** READ - Alunos CANCELADOS ****
    public List<Aluno> listarAlunosCancelados(){
        List<Aluno> alunos = this.ar.findAlunosCancelados();
        return alunos;
    }



     // ***** READ - filtrando alunos pelo "name" ****
     public List<Aluno> listarAlunosByName(String name){
        List<Aluno> alunos = this.ar.findByNameContainingIgnoreCase(name);
        return alunos;
     }




    // ************************** UPDATE **************************
    public void editar(Long id, Aluno alunoNovo) {
        alunoNovo.setId(id);
        this.ar.save(alunoNovo);
    }




    // ************************** DELETE **************************
    public Boolean deletar(Long id) {
        Optional<Aluno> aOptional = this.ar.findById(id);

        if(aOptional.isPresent()){
            this.ar.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }
}
