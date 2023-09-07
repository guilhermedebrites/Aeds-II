import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
            Scanner entrada = new Scanner(System.in);
            String texto = entrada.nextLine();

            while(!texto.equals("FIM")){
                if(verificaPalindromo(texto, 0, texto.length() - 1 )){
                    System.out.println("SIM");
                }else{
                    System.out.println("NAO");
                }
                texto = entrada.nextLine();
            }

    }

    static boolean verificaPalindromo(String texto, int inicio, int fim){
        if(inicio >= fim){
            return true;
        }

        if(texto.charAt(inicio) != texto.charAt(fim)){
            return false;
        }

        return verificaPalindromo(texto, inicio + 1, fim - 1);
    }

}