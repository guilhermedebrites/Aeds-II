public class Ciframento {

	public static void main(String[] args){
		String palavra = MyIO.readLine();
		while(!palavra.equals("FIM")){
			criptografa(palavra);
			palavra = MyIO.readLine();		
		}
	
	}

	/*
	 * Função para criptografar uma string recebida por parametro, 
	 * caracteres sao substituidos por 3 posições a frente em relação a tabela ASCII
	 */
	static void criptografa(String palavra){
		String result = "";
		for(int i = 0; i < palavra.length(); i++){
			int character = (int) palavra.charAt(i);
			character += 3;
			result += (char) character;
		}
		MyIO.println(result);
	}

}
