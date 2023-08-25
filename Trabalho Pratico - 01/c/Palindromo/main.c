#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define TAMANHO_ARRAY 200

bool verifyPalindrome(char palavra[]){
	int i, j;
	for(i=0,j=(strlen(palavra)-1);i<(strlen(palavra)/2);i++,j--){
		if(palavra[i] != palavra[j]){
			return false;
		}
	}
	return true;
}

int main(){

	char* palavra = (char*)malloc(TAMANHO_ARRAY * sizeof(char));

	scanf(" %[^\r\n]%*c", palavra);
	while(strcmp(palavra, "FIM")){
		printf("%s\n", verifyPalindrome(palavra) ? "SIM" : "NAO");
		scanf(" %[^\r\n]%*c", palavra);
	}

return 0;
}
