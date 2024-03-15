class Ciframento {

    public static void main(String args[]) {
        String entrada = MyIO.readLine();
        
        while (!verificaFim(entrada)) {
            String vazia = "";
            MyIO.println(coding(entrada, vazia, 0));
            entrada = MyIO.readLine();
        }
    }

    public static String coding(String texto, String newTexto, int pos) {

        if(texto.length() == pos)
            return newTexto;

        char unique = texto.charAt(pos);
        unique += 3;
        newTexto += unique;

        return coding(texto,newTexto,pos+1);
    }

    public static boolean verificaFim(String texto) {
        return (texto.length() >= 3 && texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M');
    }
}