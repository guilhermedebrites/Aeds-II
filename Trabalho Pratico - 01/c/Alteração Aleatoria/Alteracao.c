#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

void alteraString(char *palavra, char a, char b) {
    char newPalavra[1000];
    int i;

    for (i = 0; palavra[i] != '\0'; i++) {
        if (palavra[i] == a) {
            newPalavra[i] = b;
        } else {
            newPalavra[i] = palavra[i];
        }
    }
    newPalavra[i] = '\0';

    printf("%s\n", newPalavra);
}

int main() {
    srand(4);

    char palavra[1000];

    while (1) {
        scanf("%s", palavra);
        if (strcmp(palavra, "FIM") == 0) {
            break;
        }

        char firstLetter = 'a' + (rand() % 26);
        char secondLetter = 'a' + (rand() % 26);
        alteraString(palavra, firstLetter, secondLetter);
    }

    return 0;
}
