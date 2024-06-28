package Implementação_Arvore;

public class ArvoreBinaria {
    private No raiz;
    
    public ArvoreBinaria(){
        this.raiz = null;
    }
    
    
    public void inserir(int x) throws Exception{
        raiz = inserir(x, raiz);
    }

    private No inserir(int x, No i) throws Exception{
        if(i == null){
            i = new No(x);
        }else if( x < i.elemento){
            i.esq = inserir(x, i.esq);
        }else if( x > i.elemento) {
            i.dir = inserir(x, i.dir);
        }else {
            throw new Exception("Erro!");
        }

        return i;
    }

    public boolean pesquisar(int x) {
        return pesquisar(x, raiz);
    }

    private boolean pesquisar(int x, No i) {
        boolean resp;
        if(i == null){
            resp = false;
        }else if(x < i.elemento) {
            resp = pesquisar(x, i.esq);
        }else if( x > i.elemento) {
            resp = pesquisar(x, i.dir);
        }else{
            resp = true;
        }
        
        return resp;
    }

    public void caminharCentral(No i) {
        if(i != null) {
            caminharCentral(i.esq);
            System.out.println(i.elemento);
            caminharCentral(i.dir);
        }
    }

    public void caminharPos(No i) {
        if(i != null) {
            caminharPos(i.dir);
            caminharPos(i.esq);
            System.out.println(i.elemento);
        }
    }

    public void caminharPre(No i) {
        if(i != null) {
            System.out.println(i.elemento);
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }

    public No rotacionarEsq(No no) {
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;

        return noDir;
    }

    public No rotacionarDir(No no) {
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

}




