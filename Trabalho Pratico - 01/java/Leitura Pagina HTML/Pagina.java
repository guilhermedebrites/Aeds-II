import java.io.*;
import java.net.*;

class Pagina {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        try{
            char[] c = {
                '\u0061', '\u0065', '\u0069', '\u006F', '\u0075', 
                '\u00E1', '\u00E9', '\u00ED', '\u00F3', '\u00FA',
                '\u00E0', 
                '\u00E8', '\u00EC', '\u00F2', '\u00F9',
                '\u00E3', '\u00F5', 
                '\u00E2', '\u00EA', '\u00EE', '\u00F4', '\u00FB',
            };                    
            
            String titulo = br.readLine();
            String endereco = br.readLine();
            while(!verificaFim(titulo)){
                String html = getHtml(endereco);
                for(int i = 0; i < c.length; i++){
                    countChar(html, c[i]);
                }
                countConsoante(html);
                countTag(html, "<br>");
                countTag(html, "<table>");
                System.out.println(titulo);
                titulo = br.readLine();
                endereco = br.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static String getHtml(String endereco) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;

        try {
            url = new URL(endereco);
            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                resp += line + "\n";
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            is.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return resp;
    }

    public static void countChar(String html, char word) {
        int count = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == word) {
                count++;
            }
        }
        System.out.print(word + "(" + count + ") ");
    }

    public static void countTag(String html, String tag) {
        int count = 0;
        String[] tags = html.split(" ");
        for (String w : tags) {
            if (w.equals(tag)) {
                count++;
            }
        }
        System.out.print(tag + "(" + count + ") ");
    }

    public static void countConsoante(String html) {
        int count = 0;
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) >= 'a' && html.charAt(i) <= 'z') {
                if (html.charAt(i) != 'a' && html.charAt(i) != 'e' && html.charAt(i) != 'i' && html.charAt(i) != 'o'
                        && html.charAt(i) != 'u') {
                    count++;
                }
            }
        }
        System.out.print("consoante(" + count + ") ");
    }

    public static boolean verificaFim(String texto) {
        return (texto.length() >= 3 && texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M');
    }
}