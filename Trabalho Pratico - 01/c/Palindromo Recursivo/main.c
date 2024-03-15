#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

int isPalindromo(char texto[], int pos){
    int len = strlen(texto);
    int resp;

    if(pos == len/2){
        resp = 1;
    }else if(texto[pos] != texto[len-1 - pos - 1]){
        resp = 0;
    }else{
        resp = isPalindromo(texto, pos + 1);
    }
    return resp;
}

int isFim(char texto[]){
    return (toupper(texto[0]) == 'F' && toupper(texto[1]) == 'I' && toupper(texto[2]) == 'M');
}

int main() {
    char *entrada;
    entrada = (char *)malloc(100 * sizeof(char));

    while(fgets(entrada, 100, stdin) && !isFim(entrada)){
        isPalindromo(entrada, 0) ? puts("SIM") : puts("NAO");
    }

    free(entrada);
    return 0;
}
