class Ciframento {

    public static void main(String args[]) {
        String entrada = MyIO.readLine();

        while (!verificaFim(entrada)) {
            MyIO.println(coding(entrada));
            entrada = MyIO.readLine();
        }
    }

    public static String coding(String texto) {
        String newPalavra = "";

        for (int i = 0; i < texto.length(); i++) {
            char unique = texto.charAt(i);
            unique += 3;
            newPalavra += unique;
        }

        return newPalavra;
    }

    public static boolean verificaFim(String texto) {
        return (texto.length() >= 3 && texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M');
    }
}