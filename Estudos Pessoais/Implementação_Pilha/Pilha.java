package Implementação_Pilha;

//FILO = Estrutura básica de pilha garante que o primeiro a entrar será o ultimo a sair(FILO);
public class Pilha {
    public Celula topo;

    public Pilha(){
        topo = null;
    }

    public void inserir(int x){
        Celula tmp = new Celula(x);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;
    }

    public int remover() throws Exception{
        if(topo == null){
            throw new Exception("Pilha vazia, Erro ao remover!");
        }
        int resposta = topo.elemento;
        Celula tmp = topo;
        topo = topo.prox;
        tmp.prox = null;
        tmp = null;
        return resposta;
    }


}
