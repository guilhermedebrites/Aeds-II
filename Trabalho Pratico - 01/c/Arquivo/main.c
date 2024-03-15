#include <stdio.h>
#include <stdlib.h>

void writeNumbers(int amount, FILE *file);
void readNumbers(int amount, char* fileName);

int main() {
    char* nameFile = "Questao08.txt";

    FILE *ptr = fopen(nameFile, "wb");
    if (ptr == NULL) {
        puts("ERROR! Erro ao abrir o arquivo.");
        exit(1);
    }

    int count = 0;
    scanf("%d", &count);

    writeNumbers(count, ptr);
    fclose(ptr);

    readNumbers(count, nameFile);
    return 0;
}

void writeNumbers(int amount, FILE *file) {
    double number;

    for (int i = 0; i < amount; i++) {
        scanf("%lf", &number);
        fwrite(&number, sizeof(double), 1, file);
    }
}

void readNumbers(int amount, char* fileName) {
    FILE *ptr = fopen(fileName, "rb");
    if (ptr == NULL) {
        puts("ERROR! Erro ao abrir o arquivo para leitura.");
        exit(1);
    }

    double number;
    for (int i = 1; i <= amount; i++) {
        fseek(ptr, -i * sizeof(double), SEEK_END);
        fread(&number, sizeof(double), 1, ptr);
        if (number == (int) number) {
            printf("%d\n", (int)number);
        } else {
            printf("%g\n", number);
        }
    }

    fclose(ptr);
}
