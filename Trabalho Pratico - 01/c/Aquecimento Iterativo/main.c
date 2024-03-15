#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int countUppercase(char* texto);
int isEnd(char* texto);

int main(){
    char *entrada;
    entrada = (char*)malloc(100 * sizeof(char));

    while(fgets(entrada, 100, stdin) && isEnd(entrada) == 0){
        printf("%d\n", countUppercase(entrada));
    }

    free(entrada);
    return 0;
}

int countUppercase(char* texto){
    int count = 0;

    for(int i = 0; i < strlen(texto) - 1; i++){
        if(texto[i] >= 'A' && texto[i] <= 'Z'){
            count++;
        }
    }

    return count;
}

int isEnd(char* texto){
    return (strlen(texto) >= 3 && texto[0] == 'F' && texto[1] == 'I' && texto[2] == 'M');
}