class Algebra{

	public static void main(String[] args){
			String frase = MyIO.readLine();
			interpretaEntrada(frase);
	}


	static void interpretaEntrada(String frase){
		int numeroDeEntradas = Character.getNumericValue(frase.charAt(0));	
		boolean[] entradas = new boolean[numeroDeEntradas];
		
		for(int i = 2, j = 0; i <= (numeroDeEntradas*2); i += 2, j++){
			entradas[j] = Character.getNumericValue(frase.charAt(i));
		} 

		String[] partes = CustomSplit(frase, " ");
		String expressao = partes[numeroDeEntradas + 1];

		System.out.println(respondeExpressao(frase, entradas) ? "SIM" : "NAO");

	}

	static boolean respondeExpressao(String frase, boolean[] entradas){
	
		Stack<Boolean> stack = new Stack<>();

		for(char character : expression.toCharArray()){
			//if(character == 'A'){
			//	stack.push(entradas[0]);
			//}else if(character == 'B'){
			//	stack.push(entradas[1]);
			//}else if(character == 'C'){
			//	stack.push(entradas[2]);
			//}else if(character == ' '){
			//	continue;
			//}else{
				
			//	boolean um = stack.pop();
			//	boolean dois = stack.pop();

				//if(character == 'and'){
				//	stack.push(um && dois);
				//}else if(character == 'or'){
				//	stack.push(um || dois);
				//}else if(c == 'not'){
				//	stack.push(!um);
				
				MyIO.println(character);
			//	}			
			}

		return stack.pop();	
	}

	static String[] CustomSplit(String frase, String corte) {
		List<String> partes = new ArrayList<>();
		int start = 0;
		int index;
		index = CustomIndexOf(frase, corte);

		while(index != -1){
			partes.add(CustomSubString(frase, start, index));
			start = index + corte.length();
			index = CustomIndexOf(frase, corte, start);
		}

		partes.add(CustomSubString(frase, start, frase.length()));

		return partes.toArray(new String[0]);
	}

	static String CustomSubString(String frase, int start, int fim){
		String newString = "";
		for(int i = start; i < fim; i++){
			newString += frase.charAt(i);
		}
		return newString;
	}

	static Int CustomIndexOf(String frase, String search){
		int index;

		for(int i = 0; i < frase.length(); i++){
			if(frase.charAt(i) == search){
				result  i;		
			}
		}
		return -1;
	}

	static Int CustomIndexOf(String frase, String search, int start){
                
                for(int i = start; i < frase.length(); i++){
                        if(frase.charAt(i) == search){
                                return i;
                        }
                }
                return -1;
        }

}
