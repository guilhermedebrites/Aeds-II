public class FilaCircular {
    int[] array;
    int primeiro, ultimo;

    FilaCircular(){
        this(10);
    }

    FilaCircular(int tamanho){
        array = new int[tamanho+1];
        primeiro = ultimo = 0;
    }

    public void inserir(int elemento) throws Exception{
        if((ultimo + 1) % array.length == primeiro)
            throw new Exception();

        array[ultimo] = elemento;
        ultimo = ultimo+1 % array.length;
    }

    public int remove() throws Exception{
        if(primeiro == ultimo)
            throw new Exception();

        int tmp = array[primeiro];
        primeiro = primeiro+1 % array.length;
        return tmp;
    }

    public void mostrar(){
        int i = primeiro;

        while(i != ultimo){
            System.out.println(array[i]);
            i = i+1 % array.length;
        }
    }
}
