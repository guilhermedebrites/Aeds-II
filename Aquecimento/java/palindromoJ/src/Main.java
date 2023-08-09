import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
            Scanner entrada = new Scanner(System.in);
            String texto = entrada.nextLine();

            while(!texto.equals("FIM")){
                if(verificaPalindromo(texto)){
                    System.out.println("SIM");
                }else{
                    System.out.println("NAO");
                }
                texto = entrada.nextLine();
            }

    }

    static boolean verificaPalindromo(String texto){
        int i;
        int j;
        for(i = 0, j = (texto.length() - 1) ; i < texto.length() / 2; i++, j--){
            if(texto.charAt(i) != texto.charAt(j)){
                return false;
            }
        }
        return true;
    }

}