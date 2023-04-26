package br.com.lGabrielDev.projeto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.lGabrielDev.projeto.models.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository <Aluno, Long> {


    //queries

    //Querie - todos os alunos
    @Query(value = "SELECT a FROM Aluno a ORDER BY id DESC")
    public List<Aluno> findAll();


    //Querie - alunos ativos
    @Query(value = "SELECT a FROM Aluno a WHERE status = 'ATIVO' ORDER BY id ASC")
    public List<Aluno> findAlunosAtivos();


    //Querie - alunos inativos
    @Query(value = "SELECT a FROM Aluno a WHERE status = 'INATIVO' ORDER BY id ASC")
    public List<Aluno> findAlunosInativos();

    
    //Querie - alunos cancelados
    @Query(value = "SELECT a FROM Aluno a WHERE status = 'CANCELADO' ORDER BY id ASC")
    public List<Aluno> findAlunosCancelados();


    //Querie - filtrando por alunos que tenham tal "name"
    public List<Aluno> findByNameContainingIgnoreCase(String name);



}
