class Is {

	public static void main(String[] args){
		
		String entrada = MyIO.readLine();
		while(isFim(entrada)){
			boolean vogal = isVogal(entrada, 0);
			boolean consoante = isConsoante(entrada, 0);
			boolean inteiro = isInt(entrada, 0);
			boolean real = isReal(entrada, 0, 0);
			
			

			MyIO.print(vogal ? "SIM " : "NAO ");
			MyIO.print(consoante ? "SIM " : "NAO ");
			MyIO.print(inteiro ? "SIM " : "NAO ");
			MyIO.println(real ? "SIM " : "NAO ");
			entrada = MyIO.readLine();

		}
	}

	static boolean isVogal(String frase, int inicio){
		
		boolean isVogal = true;
		if(frase.charAt(inicio) != 'A' && frase.charAt(inicio) != 'E' && frase.charAt(inicio) != 'I' && frase.charAt(inicio) != 'O' && frase.charAt(inicio) != 'U' && frase.charAt(inicio) != ' ' && frase.charAt(inicio) != 'a' && frase.charAt(inicio) != 'e' && frase.charAt(inicio) != 'i' && frase.charAt(inicio) != 'o' && frase.charAt(inicio) != 'u'){
				isVogal = false;
			}
		else {
			isVogal(frase, inicio + 1);
		}
		return isVogal;
		}

	static boolean isConsoante(String frase, int inicio){
                boolean isConsoante = true;
                if( (int)frase.charAt(inicio) >= 66 && (int)frase.charAt(inicio) <= 122 && frase.charAt(inicio) == 'A' && frase.charAt(inicio) == 'E' && frase.charAt(inicio) == 'I' && frase.charAt(inicio) == 'O' && frase.charAt(inicio) == 'U' && frase.charAt(inicio) = 'a' && frase.charAt(inicio) == 'e' && frase.charAt(inicio) == 'i' && frase.charAt(inicio) == 'o' && frase.charAt(inicio) == 'u'){
                                isConsoante = false;
                        }
		else {
			isConsoante(frase, inicio + 1);
		}
                return isConsoante;
        }

	static boolean isInt(String frase, int inicio){
		boolean isInt = true;
		if((int)frase.charAt(inicio) != 0 && (int)frase.charAt(inicio) != 1 && (int)frase.charAt(inicio) != 2 && (int)frase.charAt(inicio) != 3 && (int)frase.charAt(inicio) != 4 && (int)frase.charAt(inicio) != 5 && (int)frase.charAt(inicio) != 6 && (int)frase.charAt(inicio) != 7 && (int)frase.charAt(inicio) != 8 && (int)frase.charAt(inicio) != 9){
			isInt = false;
			}
		else{
			isInt(frase, inicio + 1);
		}
		return isInt;
	}
	
	static boolean isReal(String frase, int inicio, int quantPontos){
		boolean isReal = true;
		if(inicio < frase.length()){
			if((int)frase.charAt(inicio) >= 48 && (int)frase.charAt(inicio) <= 57){
				isReal = isReal(frase, inicio+1, quantPontos);
			}
			else if(frase.charAt(inicio) == '.' || frase.charAt(inicio) == ','){
				isReal = isReal(frase, inicio+1, quantPontos+1);
			}
			else{
				isReal = false;
			}
		}
		else if(quantPontos > 1){
			isReal = false;
		}
                return isReal;
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
