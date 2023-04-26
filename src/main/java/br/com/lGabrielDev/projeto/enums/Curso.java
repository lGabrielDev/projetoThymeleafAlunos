package br.com.lGabrielDev.projeto.enums;

public enum Curso {
    //CONSTANTS
    PROGRAMACAO("programacao"),
    NUTRICAO("nutricao"),
    ENGENHARIA("engenharia"),
    MATEMATICA("matematica");

    //attributes
    private String name;

    public String getName() {
        return name;
    }

    //getters and setters
    public void setName(String name) {
        this.name = name;
    }

    //constructors
    private Curso(String name){
        this.name = name;
    }
}
