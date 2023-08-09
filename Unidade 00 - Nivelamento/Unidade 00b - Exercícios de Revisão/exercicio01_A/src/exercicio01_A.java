import java.util.Arrays;

public class exercicio01_A {

    public static boolean estaContido ( int[] array, int numero )
    {
        return Arrays.stream(array).anyMatch((i) -> i == numero);
    }

    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        if (estaContido(numeros, 21)) {
            System.out.println("SIM");
        } else {
            System.out.println("NÃO");
        }
    }
}