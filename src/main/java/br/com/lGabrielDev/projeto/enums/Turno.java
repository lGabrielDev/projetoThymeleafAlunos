package br.com.lGabrielDev.projeto.enums;


public  enum Turno {
    //CONSTANTS
    MANHA("manha"),
    TARDE("tarde"),
    NOITE("noite");

    //attributes
    private String name;

    //constructors
    private Turno(String name){
        this.name = name;
    }
}
