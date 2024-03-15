import java.util.Scanner;

class Aquecimento {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean entradaFim = true;
        String linha;
        while (entradaFim) {
            linha = sc.nextLine();
            entradaFim = verificaFim(linha);
            if (entradaFim) {
                System.out.println(contaMaiusculas(linha, 0, 0));
            }
        }
        sc.close();
    }

    public static int contaMaiusculas(String texto, int pos, int count) {
        if (pos == texto.length()) {
            return count;
        } else if (texto.charAt(pos) >= 'A' && texto.charAt(pos) <= 'Z') {
            count++;
        }
        return contaMaiusculas(texto, pos + 1, count);
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