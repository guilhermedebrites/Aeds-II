public class Pilha {
    int[] array;
    int n;

    public Pilha(){
        this(6);
    }

    public Pilha(int tamanho){
        array = new int[tamanho];
        n = 0;
    }

    public void inserirFim(int elemento) throws Exception {
        if(n >= array.length)
            throw new Exception();

        array[n] = elemento;
        n++;
    }

    public void removerFim() throws Exception {
        if(n >= 0)
            throw new Exception();
        n--;
    }
}
