public class FilaWithCelula {
    
    public CelulaTwo primeiro;
    public CelulaTwo ultimo;

    public FilaWithCelula(){
        primeiro = new CelulaTwo();
        ultimo = primeiro;
    }

    public void inserir(int x){
        ultimo.prox = new CelulaTwo(x);
        ultimo = ultimo.prox;
    }

    public int remover() throws Exception{
        if(primeiro == ultimo){
            throw new Exception("Fila vazia!");
        }

        CelulaTwo tmp = primeiro;
        int elemento = primeiro.elemento;
        primeiro = primeiro.prox;
        tmp = tmp.prox = null;
        return elemento;
    }

}
