class Lista {

    private Celula primeiro, ultimo;

    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserirInicio(int x){
        Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;

        if(primeiro == ultimo){
            ultimo = tmp;
        }
        tmp = null;
    }

    public void inserirFim(int x){
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public int removerInicio() throws Exception{
        if(primeiro == ultimo){
            throw new Exception("Lista vazia!");
        }
        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        int elemento = tmp.elemento;

        tmp.prox = null;
        tmp = null;
        return elemento;
    }

    public int removerFim() throws Exception{
        if(primeiro == ultimo){
            throw new Exception("Lista vazia!");
        }
        Celula tmp = primeiro;
        while(primeiro.prox != ultimo){
            tmp = primeiro.prox;
        }
        int elemento = ultimo.elemento;
        ultimo = tmp;
        tmp.prox = null;


        return elemento;
    }

    public void inserir(int x, int pos){
        Celula novaCelula = new Celula(x);
        Celula tmp = primeiro;

        for(int i = 0; i < pos; i++){
            tmp = tmp.prox;
        }

        novaCelula.prox = tmp.prox;
        tmp.prox = novaCelula;
        
        tmp = novaCelula = null;
    }

    public void remover(int pos){
        Celula i = primeiro;

        for(int j = 0; j < pos; j++, i = i.prox);

        Celula tmp = i.prox;
        i.prox = tmp.prox;

        i = tmp = null;

    }
}
