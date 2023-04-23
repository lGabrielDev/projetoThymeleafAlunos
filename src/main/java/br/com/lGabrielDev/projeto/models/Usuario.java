package br.com.lGabrielDev.projeto.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(
    name = "tb_usuario"
    //,
    // uniqueConstraints = {
    //         @UniqueConstraint(name="unique_name", columnNames = "name")                                                         
    //         } 
        )

public class Usuario {
    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;


    @Column(name = "name")
    @Size(min = 3, max = 20, message = "O nome de usuário deve conter entre 3 e 20 caracteres")
    private String name;


    @Column(name = "email")
    @Email(message = "Insira um e-mail válido")
    @NotBlank(message = "Seu e-mail não pode ser NULL")
    String email;


    @Column(name = "password")
    @NotBlank(message = "Seu password não pode ser NULL")
    String password;



    //constructors
    public Usuario(){}

    public Usuario(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    //toString()
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
    }
}
