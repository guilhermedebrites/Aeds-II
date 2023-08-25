import java.util.*;

public class Alteracao{

	public static void main(String[] args){
		MyIO.setCharset("UTF-8");
		Random gerador = new Random();
		gerador.setSeed(4);

		String palavra = MyIO.readLine();
		while(!palavra.equals("FIM")){
			char firstLetter = (char) ('a' + (Math.abs(gerador.nextInt())) % 26);
			char secondLetter = (char) ('a' + (Math.abs(gerador.nextInt())) % 26);
			alteraString(palavra, firstLetter, secondLetter);
			palavra = MyIO.readLine();
		}		
	}

	static void alteraString(String palavra, char a, char b){
		String newPalavra = "";
		for(int i = 0; i < palavra.length(); i++){
			if(palavra.charAt(i) == a){
				newPalavra  += b;
			}
			else{
				newPalavra += palavra.charAt(i);
			}
		}
		MyIO.println(newPalavra);
	}

}
