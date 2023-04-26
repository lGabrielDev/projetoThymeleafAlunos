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



    
    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    
}
