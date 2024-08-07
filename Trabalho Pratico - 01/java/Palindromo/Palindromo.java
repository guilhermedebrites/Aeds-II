import java.util.*;

class Palindromo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();
        while (verificaFim(entrada)) {
            System.out.println(isPalindromo(entrada) ? "SIM" : "NAO");
            entrada = sc.nextLine();
        }
        sc.close();
    }

    public static boolean isPalindromo(String texto) {
        for (int i = 0, j = texto.length()-1; i < texto.length() / 2; i++, j--) {
            if (texto.charAt(i) != texto.charAt(j))
                return false;
        }
        return true;
    }

    public static boolean verificaFim(String texto) {
        if (texto.charAt(0) == 'F' &&
                texto.charAt(1) == 'I' &&
                texto.charAt(2) == 'M') {
            return false;
        } else {
            return true;
        }
    }
}