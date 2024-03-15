import java.util.Scanner;

class Aquecimento {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean entradaFim = true;
        String linha;
        int contador;
        while(entradaFim){
            linha = sc.nextLine();
            entradaFim = verificaFim(linha);
            if(entradaFim){
                contador = contaMaiusculas(linha);
                System.out.println(contador);
            }
        }
        sc.close();
    }

    public static int contaMaiusculas(String texto) {
        int count = 0;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                count++;
            }
        }
        return count;
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