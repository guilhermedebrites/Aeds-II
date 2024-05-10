public class PilhaWithCelula {
    public CelulaTwo topo;

    public PilhaWithCelula(){
        topo = null;
    }

    public void inserir(int x){
        CelulaTwo tmp = new CelulaTwo(x);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;
    }

    public int remover() throws Exception{
        
        if(topo == null){
            throw new Exception("Pilha vazia!");
        }

        int elemento = topo.elemento;
        CelulaTwo tmp = topo;
        topo = tmp.prox;
        tmp = tmp.prox = null;
        return elemento;
    }
}