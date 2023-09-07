#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define TAMANHO_ARRAY 200

//Retorna true no caso de palindromos ou false no caso de não palindromos
bool verifyPalindrome(char palavra[], int inicio, int fim){
	
	if(inicio == fim){
		return true;
	}
	if(palavra[inicio] != palavra[fim]){
		return false;
	}

	return verifyPalindrome(palavra, inicio + 1, fim - 1);
}


//Verifica a entrada e retorna false quando o parâmetro é igual a FIM 
bool isFim(char palavra[]){
	if(palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M'){
		return false;
	}
	return true;
}

int main(){

	char* palavra = (char*)malloc(TAMANHO_ARRAY * sizeof(char));

	scanf(" %[^\r\n]%*c", palavra);
	while(isFim(palavra)){
		printf("%s\n", verifyPalindrome(palavra, 0, strlen(palavra)) ? "SIM" : "NAO");
		scanf(" %[^\r\n]%*c", palavra);
	}

return 0;
}
