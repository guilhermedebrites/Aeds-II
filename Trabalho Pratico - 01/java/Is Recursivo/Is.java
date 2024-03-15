import java.io.BufferedReader;
import java.io.InputStreamReader;

class Is{
    public static void main(String args[]){
        InputStreamReader c = new InputStreamReader(System.in);
        BufferedReader cd = new BufferedReader(c);
        String vogais = "aeiouAEIOU";
        String consoantes = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
        String numeros = "0123456789";
        String numerosReais = "0123456789,.";

        try{
            String entrada = cd.readLine();
            while(!verificaFim(entrada)){
                System.out.print(isVogal(entrada, vogais, 0, 0) ? "SIM " : "NAO ");
                System.out.print(isConsoante(entrada, consoantes, 0, 0) ? "SIM " : "NAO ");
                System.out.print(isNumber(entrada, numeros, 0, 0) ? "SIM " : "NAO ");
                System.out.println(isNumberReal(entrada, numerosReais, 0 , 0) ? "SIM" : "NAO");
                entrada = cd.readLine();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static boolean isVogal(String texto, String vogais, int count, int pos){
        if(count == texto.length())
            return true;
        else if(pos == vogais.length())
            return false;
        else if(texto.charAt(count) == vogais.charAt(pos))
            return isVogal(texto, vogais, count + 1, 0);
        else
            return isVogal(texto, vogais, count, pos + 1);
    }

    public static boolean isConsoante(String texto, String consoantes, int count, int pos){

        if(count == texto.length())
            return true;
        else if(pos == consoantes.length())
            return false;
        else if(texto.charAt(count) == consoantes.charAt(pos))
            return isConsoante(texto, consoantes, count+1, pos);
        else
            return isConsoante(texto, consoantes, count, pos+1);
    }

    public static boolean isNumber(String texto, String numeros, int count, int pos){
        if(count == texto.length())
            return true;
        else if(pos == numeros.length())
            return false;
        else if(texto.charAt(count) == numeros.charAt(pos))
            return isNumber(texto, numeros, count+1, 0);
        else
            return isNumber(texto, numeros, count, pos+1);
    }

    public static boolean isNumberReal(String texto, String numeros, int count, int pos){
        if(count == texto.length())
            return true;
        else if(pos == numeros.length())
            return false;
        else if(texto.charAt(count) == numeros.charAt(pos))
            return isNumberReal(texto, numeros, count+1, 0);
        else
            return isNumberReal(texto, numeros, count, pos+1);
    }

    public static boolean verificaFim(String texto) {
        return (texto.length() >= 3 && texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M');
    }
}