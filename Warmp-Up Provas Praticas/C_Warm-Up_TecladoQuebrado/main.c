#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* beijuText(char* texto);
int* findBrackets(int tamanho, char* texto);
char* resscreve(char* texto, char* newTexto, int* positions);

int main() {
    char* entrada = (char*)malloc(100 * sizeof(char));

    while (scanf("%99s", entrada) != EOF) {
        char* result = beijuText(entrada);
        printf("%s\n", result);
        free(result);
    }

    free(entrada);
    return 0;
}

char* beijuText(char* texto) {
    char* newTexto = (char*)malloc(100 * sizeof(char));
    if (!newTexto) {
        return NULL;
    }

    int* positions = findBrackets(strlen(texto), texto);
    while (positions && positions[0] != -1) {
        texto = resscreve(texto, newTexto, positions);
        free(positions); 
        positions = findBrackets(strlen(newTexto), newTexto);
    }

    free(positions);
    return newTexto;
}

int* findBrackets(int tamanho, char* texto) {
    int* positions = (int*)malloc(2 * sizeof(int));
    if (!positions) {
        return NULL;
    }

    positions[0] = -1;
    positions[1] = -1;

    for (int i = tamanho - 1; i >= 0; i--) {
        if (texto[i] == '[') {
            positions[0] = i;
            break;
        }
    }

    if (positions[0] == -1) {
        return positions;
    }

    for (int i = positions[0] + 1; i < tamanho; i++) {
        if (texto[i] == ']') {
            positions[1] = i;
            break;
        }
    }

    return positions;
}

char* resscreve(char* texto, char* newTexto, int* positions) {
    int i = 0;
    for (int a = positions[0] + 1; a < positions[1]; a++, i++) {
        newTexto[i] = texto[a];
    }

    for (int z = 0; z < positions[0]; z++, i++) {
        newTexto[i] = texto[z];
    }

    for (int j = positions[1] + 1; j < strlen(texto); j++, i++) {
        newTexto[i] = texto[j];
    }

    newTexto[i] = '\0';

    return newTexto;
}
