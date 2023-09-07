class Is {

	public static void main(String[] args){
		
		String entrada = MyIO.readLine();
		while(isFim(entrada)){
			boolean vogal = isVogal(entrada);
			boolean consoante = isConsoante(entrada);
			boolean inteiro = isInt(entrada);
			boolean real = isReal(entrada);
			
			

			MyIO.print(vogal ? "SIM " : "NAO ");
			MyIO.print(consoante ? "SIM " : "NAO ");
			MyIO.print(inteiro ? "SIM " : "NAO ");
			MyIO.println(real ? "SIM " : "NAO ");
			entrada = MyIO.readLine();

		}
	}

	static boolean isVogal(String frase){
		//Itera pela string, caso ache consoante retorna false;
		for(int i = 0; i < frase.length(); i++){
			if(frase.charAt(i) != 'A' && frase.charAt(i) != 'E' && frase.charAt(i) != 'I' && frase.charAt(i) != 'O' && frase.charAt(i) != 'U' && frase.charAt(i) != ' ' && frase.charAt(i) != 'a' && frase.charAt(i) != 'e' && frase.charAt(i) != 'i' && frase.charAt(i) != 'o' && frase.charAt(i) != 'u'){
				return false;
			}
		}
		return true;
	}

	static boolean isConsoante(String frase){
                //Itera pela string, caso ache vogal retorna false;
                for(int i = 0; i < frase.length(); i++){
                        if(frase.charAt(i) == 'A' && frase.charAt(i) == 'E' && frase.charAt(i) == 'I' && frase.charAt(i) == 'O' && frase.charAt(i) == 'U' && frase.charAt(i) == ' ' && frase.charAt(i) == 'a' && frase.charAt(i) == 'e' && frase.charAt(i) == 'i' && frase.charAt(i) == 'o' && frase.charAt(i) == 'u'){
                                return false;
                        }
                }
                return true;
        }

	static boolean isInt(String frase){
		//Itera pela string, caso encontre algo diferente de um numero retorn false;
		for(int i = 0; i < frase.length(); i++){
			if(frase.charAt(i) != "0" && frase.charAt(i) != "1" && frase.charAt(i) != "1" && frase.charAt(i) != "2" && frase.charAt(i) != "3" && frase.charAt(i) != "4" && frase.charAt(i) != "5" && frase.charAt(i) != "6" && frase.charAt(i) != "7" && frase.charAt(i) != "8" && frase.charAt(i) != "9"){
			return false;
			}
		}
		return true;
	}
	
	static boolean isReal(String frase){
		//Itera pela string, caso encontre alguma letra retorna false;
                for(int i = 0; i < frase.length(); i++){
                        if(frase.charAt(i) != "0" && frase.charAt(i) != "1" && frase.charAt(i) != "1" && frase.charAt(i) != "2" && frase.charAt(i) != "3" && frase.charAt(i) != "4" && frase.charAt(i) != "5" && frase.charAt(i) != "6" && frase.charAt(i) != "7" && frase.charAt(i) != "8" && frase.charAt(i) != "9" || frase.charAt(i) != "," || frase.charAt(i) != "."){
                        return false;
                        }
                }
                return true;
        }

	//Verifica se a entrada inserida = "FIM"
	static boolean isFim(String frase){
	
		boolean aux = false;

		if(frase.charAt(0) != 'F' || frase.charAt(1) != 'I' || frase.charAt(2) != 'M'){
			aux = true;
		}

		return aux;
	}
}
