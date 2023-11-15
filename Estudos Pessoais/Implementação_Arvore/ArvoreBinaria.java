package Implementação_Arvore;

public class ArvoreBinaria {
    private No raiz;

    public ArvoreBinaria(){
        this.raiz = null;
    }

    public No inserir(int x, No i) throws Exception{
        if(i == null){
            i = new No(x);
        }else if(x < i.elemento){
            i.esq = inserir(x, i.esq);
        }else if(x > i.elemento){
            i.dir = inserir(x, i.dir);
        }else{
            throw new Exception("Erro ao inserir!");
        }

        return i;
    }
}
