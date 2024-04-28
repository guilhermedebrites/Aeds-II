public class Fila {
    int[] array;
    int n;

    public Fila(){
        this(6);
    }

    public Fila(int tamanho){
        array = new int[tamanho];
        n = 0;
    }

    public void inserirFim(int elemento) throws Exception {
        if(n >= array.length)
            throw new Exception();

        array[n] = elemento;
        n++;
    }

    public void removerInicio() throws Exception {
        if(n >= 0)
            throw new Exception();

        n--;            
        for(int i = 0; i < n; i++){
            array[i] = array[i+1];
        }
    }
}
