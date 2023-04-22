package br.com.lGabrielDev.projeto.models;

import br.com.lGabrielDev.projeto.enums.Curso;
import br.com.lGabrielDev.projeto.enums.Status;
import br.com.lGabrielDev.projeto.enums.Turno;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_aluno")
public class Aluno {
    //attributes
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Size(min = 5, message = "O \"name\" deve ter no mínimo 5 caracteres.")
    @NotBlank(message = "O campo\"name\" não pode ser nulo.")
    private String name;

    @Column(name = "curso")
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Esse campo não pode ser nulo.")
    private Curso curso;

    @Column(name = "matricula")
    @NotBlank(message = "Clique no botão \"Gerar\" para obter a matrícula")
    private String matricula;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Esse campo não pode ser nulo.")
    private Status status;

    @Column(name = "turno")
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Esse campo não pode ser nulo.")
    private Turno turno;



    //constructors
    public Aluno(){}

    public Aluno(String name, Curso curso, String matricula, Status status, Turno turno){
        this.name = name;
        this.curso = curso;
        this.matricula = matricula;
        this.status = status;
        this.turno = turno;
    }


    //getters and setters
    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Turno getTurno() {
        return this.turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }



    //toString()
    @Override
    public String toString() {
        return "Aluno [id=" + id + ", name=" + name + ", curso=" + curso.name() + ", matricula=" + matricula + ", status="
                + status.name() + ", turno=" + turno + "]";
    }
}
