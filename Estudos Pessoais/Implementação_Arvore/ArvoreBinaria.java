package Implementação_Arvore;

public class ArvoreBinaria {
    private No raiz;
    
    public ArvoreBinaria(){
        this.raiz = null;
    }
    
    
    public int count = 0;

    public int contarPalavras(char primeiro, char ultimo){
        count = 0;
        contarPalavras(primeiro, ultimo, raiz);
        return count;
    }

    public void contarPalavras(char primeiro, char ultimo, No i){
        if( i != null){
            if(i.elemento.charAt(0) == primeiro && i.elemento.endsWith(String.valueOf(ultimo))){
                count++;
            }
            contarPalavras(primeiro, ultimo, i.esq);
            contarPalavras(primeiro, ultimo, i.dir);
        }
    }


}




