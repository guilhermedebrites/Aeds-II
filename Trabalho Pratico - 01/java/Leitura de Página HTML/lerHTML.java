import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class lerHTML{
	
	public static void main(String[] args){
		boolean issFim;
		MyIO.setCharset("UTF-8");
		String name;
		String endereco;
		String HTML;
		do{
			name = MyIO.readLine();
			issFim = isFim(name);
			if(issFim){
				endereco = MyIO.readLine();		
				HTML = getHtml(endereco);
				getCounts(HTML, name);
			}
		}while(issFim);
	}

	static void getCounts(String html, String name){
		MyIO.setCharset("UTF-8");
		int[] letras = {97, 101, 105, 111, 117, 225, 233, 237, 243, 250, 224, 232, 236, 242, 249, 227, 245, 226, 234, 238, 244, 251};
		int[] letrasConsoantes = {98, 99, 100, 102, 103, 104, 106, 107, 108, 109, 110, 112, 113, 114, 115, 116, 118, 119, 120, 121, 122};

		int[] valores = new int[25];
	
		for(int i = 0; i < letras.length; i++){
			for(int j = 0; j < html.length(); j++){
				if((int)html.charAt(j) == letras[i]){
					valores[i] += 1;
				}
				if(html.charAt(i) == '<' && html.charAt(i + 1) == 't' && html.charAt(i + 2) == 'a' && html.charAt(i + 3) == 'b' && html.charAt(i + 4) == 'l' && html.charAt(i + 5) == 'e' && html.charAt(i + 6) == '>'){
					valores[24] += 1;
				}
				if(html.charAt(i) == '<' && html.charAt(i + 1) == 'b' && html.charAt(i + 1) == 'r' && html.charAt(i + 2) == '>'){
					valores[23] += 1;
				}
			}
		}
		
		for(int i = 0; i < letrasConsoantes.length; i++){
			for(int j = 0; j < html.length(); j++){
				if((int)html.charAt(j) == letrasConsoantes[i]){
					valores[22] += 1;
				}
			}
		}
		
		for(int i = 0; i < 21; i++){
			System.out.print((char)letras[i]+"(" + valores[i] + ") ");
		}
		MyIO.print("consoante(" + valores[22] + ")");
		MyIO.print(" <br>(" + valores[23] + ")");
		MyIO.print(" <table>(" + valores[24] + ") ");
		MyIO.println(name);
	}

	static String getHtml(String endereco){
	  URL url;
	  InputStream is = null;
	  BufferedReader br;
	  String resp = "", line;

	  try {
		 url = new URL(endereco);
		 is = url.openStream();  // throws an IOException
		 br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

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
		 // nothing to see here

	  }

	  return resp;
   }
   
   public static boolean isFim(String string)
	{
		boolean isFim = false;

		if(string.charAt(0) != 'F' || string.charAt(1) != 'I' || string.charAt(2) != 'M'){
			isFim = true;
		}

		return isFim;
	}

}