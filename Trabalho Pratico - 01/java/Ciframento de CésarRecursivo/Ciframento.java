public class Ciframento {

	public static void main(String[] args){
		String palavra = MyIO.readLine();
		while(!isFim(palavra)){
			criptografa(palavra, 0, "");
			palavra = MyIO.readLine();		
		}
	
	}

	/*
	 * Função para criptografar uma string recebida por parametro, 
	 * caracteres sao substituidos por 3 posições a frente em relação a tabela ASCII
	 */
	static void criptografa(String palavra, int index, String result){
		
		if(index == palavra.length()){
			MyIO.println(result);
			return;
		}

		int character = (int) palavra.charAt(index);
		character += 3;
		result += (char) character;

		criptografa(palavra, index + 1, result);
	}

	static boolean isFim(String palavra){
		if(palavra.charAt(0) == 'F' && palavra.charAt(1) == 'I' && palavra.charAt(2) == 'M'){
			return true;
		}
		return false;
	}
}
