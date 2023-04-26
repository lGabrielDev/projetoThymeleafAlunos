package br.com.lGabrielDev.projeto.enums;

public enum Status {
    //CONSTANTS
    ATIVO("ativo"),
    INATIVO("inativo"),
    CANCELADO("cancelado");

    //attributes
    String name;

    //constructors
    private Status(String name){
        this.name = name;
    }
    


    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    } 
}
