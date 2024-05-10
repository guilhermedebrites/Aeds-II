package Implementação_Arvore;

public class No {
    public String elemento;
    public No esq, dir;

    public No(String elemento){
        this(elemento, null, null);
    }

    public No(String elemento, No esq, No dir){
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }

}
