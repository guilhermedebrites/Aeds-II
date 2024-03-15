#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

int isEnd(char* texto);
char* alteracaoAleatoria(char* texto, char primeiro, char segundo,char* newPalavra, int tamanho, int pos);

int main(){
    srand(4);
    char* entrada;
    int tamanho;
    entrada = (char*)malloc(100 * sizeof(char));

    while(fgets(entrada, 100, stdin) && isEnd(entrada) == 0){
        char primeiro = 'a' + (abs(rand()) % 26);
        char segundo = 'a' + (abs(rand()) % 26);
        tamanho = strlen(entrada);
        char* newPalavra = (char*)malloc((tamanho+1) * sizeof(char));
        char* resultado = alteracaoAleatoria(entrada, primeiro, segundo, newPalavra, tamanho, 0);
        printf("%s", resultado);
        free(resultado);
    }
    free(entrada);
    return 0;
}

char* alteracaoAleatoria(char* texto, char primeiro, char segundo,char* newPalavra, int tamanho, int pos){

    if(tamanho == pos){
        newPalavra[tamanho] = '\0';
        return newPalavra;
    }
    else if(texto[pos] == primeiro)
        newPalavra[pos] = segundo;
    else
        newPalavra[pos] = texto[pos];
    return alteracaoAleatoria(texto, primeiro, segundo, newPalavra, tamanho, pos+1);
}

int isEnd(char* texto){
    return (strlen(texto) >= 3 && texto[0] == 'F' && texto[1] == 'I' && texto[2] == 'M');
}