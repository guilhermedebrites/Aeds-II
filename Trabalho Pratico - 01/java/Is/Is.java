import java.io.BufferedReader;
import java.io.InputStreamReader;

class Is{
    public static void main(String args[]){
        InputStreamReader c = new InputStreamReader(System.in);
        BufferedReader cd = new BufferedReader(c);

        try{
            String entrada = cd.readLine();
            while(!verificaFim(entrada)){
                System.out.print(isVogal(entrada) ? "SIM " : "NAO ");
                System.out.print(isConsoante(entrada) ? "SIM " : "NAO ");
                System.out.print(isNumber(entrada) ? "SIM " : "NAO ");
                System.out.println(isNumberReal(entrada) ? "SIM" : "NAO");
                entrada = cd.readLine();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static boolean isVogal(String texto){
        String vogais = "aeiouAEIOU";
        int count = 0;
        for(int i = 0; i < texto.length(); i++){
            for(int j = 0; j < vogais.length(); j++){
                if(texto.charAt(i) == vogais.charAt(j)){
                    count++;
                }
            }
        }
        if(count == texto.length())
            return true;
        return false;
    }

    public static boolean isConsoante(String texto){
        String consoantes = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
        int count = 0;
        for(int i = 0; i < texto.length(); i++){
            for(int j = 0; j < consoantes.length(); j++){
                if(texto.charAt(i) == consoantes.charAt(j)){
                    count++;
                }
            }
        }
        if(count == texto.length())
            return true;
        return false;
    }

    public static boolean isNumber(String texto){
        String numeros = "0123456789";
        int count = 0;
        for(int i = 0; i < texto.length(); i++){
            for(int j = 0; j < numeros.length(); j++){
                if(texto.charAt(i) == numeros.charAt(j)){
                    count++;
                }
            }
        }
        if(count == texto.length())
            return true;
        return false;
    }

    public static boolean isNumberReal(String texto){
        String numeros = "0123456789,.";
        int count = 0;
        for(int i = 0; i < texto.length(); i++){
            for(int j = 0; j < numeros.length(); j++){
                if(texto.charAt(i) == numeros.charAt(j)){
                    count++;
                }
            }
        }
        if(count == texto.length())
            return true;
        return false;
    }

    public static boolean verificaFim(String texto) {
        return (texto.length() >= 3 && texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M');
    }
}