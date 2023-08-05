#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

bool verificaPalindromo(char texto[]){
    int i, j, tam = strlen(texto);
    for(i = 0,j = tam-1; i < tam / 2; i++, j--){
        if(texto[i] != texto[j]){
            return false;
        }
    }
    return true;
}

int main() {
    char* texto = (char*)malloc(200 * sizeof(char));

    scanf(" %[^\r\n]%*c", texto);
    while(strcmp(texto, "FIM")){
        printf("%s\n", verificaPalindromo(texto) ? "SIM" : "NAO");
        scanf(" %[^\r\n]%*c", texto);
    }
    return 0;
}