#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int isPalindromo(char texto[]){
    int len = strlen(texto);

    for(int i = 0, j = len-2; i < j; i++, j--){
        if(texto[i] != texto[j])
            return 0;
    }
    return 1;
}

int isFim(char texto[]){
    return (texto[0] == 'F' && texto[1] == 'I' && texto[2] == 'M');
}

int main() {
    char *entrada;
    entrada = (char *)malloc(100 * sizeof(char));

    while(fgets(entrada, 100, stdin) && isFim(entrada) == 0){
        isPalindromo(entrada) ? puts("SIM") : puts("NAO");
    }

    free(entrada);
    return 0;
}