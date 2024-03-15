import java.util.Random;

class Alteracao{

    public static void main(String args[]){
        MyIO.setCharset("UTF-8");

        Random gerador = new Random();
        gerador.setSeed(4);

        String entrada = MyIO.readLine();
        while(!verificaFim(entrada)){
            char primeiro = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
            char segundo = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
            MyIO.println(alteracaoAleatoria(entrada, primeiro, segundo));
            entrada = MyIO.readLine();
        }
    }

    public static String alteracaoAleatoria(String texto, char primeiro, char segundo){
        String newPalavra = "";

        for(int i = 0; i < texto.length(); i++){
            if(texto.charAt(i) == primeiro){
                newPalavra += segundo;
            }else{
                newPalavra += texto.charAt(i);
            }
        }

        return newPalavra;
    }

    public static boolean verificaFim(String texto) {
        return (texto.length() >= 3 && texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M');
    }
}