#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

bool verificaPalindromo(char texto[]){
    bool verifica;
    int i, j, tam = strlen(texto);
    verifica = true;
    for(i = 0,j = tam-1; i < tam / 2; i++, j--){
        if(texto[i] != texto[j]){
            verifica = false;
            i = tam;
        }
    }
    return verifica;
}

int main() {
    char texto[50];
    int continuar = 1;

    while(continuar == 1){
        scanf(" %[^\n]", &texto);
        bool teste = verificaPalindromo(texto);

        if(strcmp(texto, "FIM") == 0){
            continuar = 0;
        }else{
            if(teste){
                puts("SIM");
            }else{
                puts("NAO");
            }
        }
    }
    return 0;
}
