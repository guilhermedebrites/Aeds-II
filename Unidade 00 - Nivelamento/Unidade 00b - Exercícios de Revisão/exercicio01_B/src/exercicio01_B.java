public class exercicio01_B {

    public static boolean estaContido ( int[] array, int numero )
    {
        int esquerda = 0;
        int direita = array.length - 1;

        while(esquerda <= direita){
            int meio = esquerda + (direita - esquerda) / 2;

            if(array[meio] == numero){
                return true;
            }

            if(array[meio] < numero){
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        if (estaContido(numeros, 6)) {
            System.out.println("SIM");
        } else {
            System.out.println("NÃO");
        }
    }
}