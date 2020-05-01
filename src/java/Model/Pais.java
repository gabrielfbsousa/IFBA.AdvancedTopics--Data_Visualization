package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabrielferreira
 */
public class Pais {
    public String nome;
    public double area;
    public double populacao;
    public double densidade_demografica;
    public double pib;
    public double idh;
    
    public Pais(){
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPopulacao() {
        return populacao;
    }

    public void setPopulacao(double populacao) {
        this.populacao = populacao;
    }

    public double getDensidade_demografica() {
        return densidade_demografica;
    }

    public void setDensidade_demografica(double densidade_demografica) {
        this.densidade_demografica = densidade_demografica;
    }

    public double getPib() {
        return pib;
    }

    public void setPib(double pib) {
        this.pib = pib;
    }

    public double getIdh() {
        return idh;
    }

    public void setIdh(double idh) {
        this.idh = idh;
    }
    
    public Double getAtributo(String atributo){
        
        switch (atributo){
                case "densidade_demografica" : return this.getDensidade_demografica(); 
                    case "idh" : return this.getIdh(); 
                        case "pib" : return this.getPib(); 
                            case "populacao" : return this.getPopulacao(); 
                                case "area" : return this.getArea(); 
                                default : return 0.0; 
            }
        
        
    }
    
}
