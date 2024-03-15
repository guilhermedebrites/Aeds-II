import java.util.*;

class Palindromo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();
        while (verificaFim(entrada)) {
            System.out.println(isPalindromo(entrada, 0) ? "SIM" : "NAO");
            entrada = sc.nextLine();
        }
        sc.close();
    }

    public static boolean isPalindromo(String texto, int pos) {
        boolean resp;

        if(pos == texto.length()/2)
            resp = true;
        else if(texto.charAt(pos) != texto.charAt(texto.length() - pos - 1))
            resp = false;
        else
            resp = isPalindromo(texto, pos + 1);
        return resp;
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