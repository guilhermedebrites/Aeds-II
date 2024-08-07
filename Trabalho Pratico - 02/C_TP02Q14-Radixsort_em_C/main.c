#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

#define MAX_LENGTH 200

typedef struct {
    char id[MAX_LENGTH];
    char nome[MAX_LENGTH];
    char alternate_names[MAX_LENGTH];
    char house[MAX_LENGTH];
    char ancestry[MAX_LENGTH];
    char species[MAX_LENGTH];
    char patronus[MAX_LENGTH];
    bool hogwartsStaff;
    bool hogwartsStudent;
    char actorName[MAX_LENGTH];
    bool alive;
    char dateOfBirth[10];
    int yearOfBirth;
    char eyeColour[MAX_LENGTH];
    char gender[MAX_LENGTH];
    char hairColour[MAX_LENGTH];
    bool wizard;
} Personagem;

void imprimir(Personagem *personagem) {
    int len = strlen(personagem->alternate_names);
    for (int i = 0; i < len; i++) {
        if (personagem->alternate_names[i] == '[') {
            personagem->alternate_names[i] = '{';
        } else if (personagem->alternate_names[i] == ']') {
            personagem->alternate_names[i] = '}';
        } else if (personagem->alternate_names[i] == '\'') {
            memmove(&personagem->alternate_names[i], &personagem->alternate_names[i + 1], strlen(personagem->alternate_names) - i);
            i--;
        }
    }

    printf("[%s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %d ## %s ## %s ## %s ## %s]\n",
           strcmp(personagem->id, "-1") != 0 ? personagem->id : "", 
           strcmp(personagem->nome, "-1") != 0 ? personagem->nome : "", 
           strcmp(personagem->alternate_names, "-1") != 0 ? personagem->alternate_names : "", 
           strcmp(personagem->house, "-1") != 0 ? personagem->house : "",
           strcmp(personagem->ancestry, "-1") != 0 ? personagem->ancestry : "", 
           strcmp(personagem->species, "-1") != 0 ? personagem->species : "", 
           strcmp(personagem->patronus, "-1") != 0 ? personagem->patronus : "",
           personagem->hogwartsStaff == 1 ? "true" : "false", 
           personagem->hogwartsStudent == 1 ? "true" : "false",
           strcmp(personagem->actorName, "-1") != 0 ? personagem->actorName : "", 
           personagem->alive == 1 ? "true" : "false", 
           strcmp(personagem->dateOfBirth, "-1") != 0 ? personagem->dateOfBirth : "",
           personagem->yearOfBirth, 
           strcmp(personagem->eyeColour, "-1") != 0 ? personagem->eyeColour : "", 
           strcmp(personagem->gender, "-1") != 0 ? personagem->gender : "", 
           strcmp(personagem->hairColour, "-1") != 0 ? personagem->hairColour : "",
           personagem->wizard == 1 ? "true" : "false");
}

void replaceDoubleViruglas(char *str) {
    int tamanho = strlen(str);
    char tmp[3 * tamanho];
    int j = 0; 

    for (int i = 0; i < tamanho; i++) {
        if (str[i] == ';' && str[i+1] == ';') {
            tmp[j++] = ';'; 
            tmp[j++] = '-';
            tmp[j++] = '1';
            tmp[j++] = ';';
            
            i++;
        } else {
            tmp[j++] = str[i];
        }
        
    }
    tmp[j] = '\0';
    strcpy(str, tmp);
}

void ler(Personagem *personagem, char *str) {
    replaceDoubleViruglas(str);
    char *token = strtok(str, ";");
    int fieldIndex = 0;

    while (token != NULL) {
        switch (fieldIndex) {
            case 0: strcpy(personagem->id, token); break;
            case 1: strcpy(personagem->nome, token); break;
            case 2: strcpy(personagem->alternate_names, token); break;
            case 3: strcpy(personagem->house, token); break;
            case 4: strcpy(personagem->ancestry, token); break;
            case 5: strcpy(personagem->species, token); break;
            case 6: strcpy(personagem->patronus, token); break;
            case 7: personagem->hogwartsStaff = (strlen(token) == 6 ? 0 : 1); break;
            case 8: personagem->hogwartsStudent = (strlen(token) == 6 ? 0 : 1); break;
            case 9: strcpy(personagem->actorName, token); break;
            case 10: personagem->alive = (strlen(token) == 6 ? 0 : 1); break;
            case 12: strcpy(personagem->dateOfBirth, token); break;
            case 13: personagem->yearOfBirth = atoi(token); break;
            case 14: strcpy(personagem->eyeColour, token); break;
            case 15: strcpy(personagem->gender, token); break;
            case 16: strcpy(personagem->hairColour, token); break;
            case 17: personagem->wizard = (strlen(token) == 6 ? 0 : 1); break;
            default: break;
        }
        fieldIndex++;
        token = strtok(NULL, ";");
    }
}

void clone(Personagem *personagem, Personagem *novo) {
    strcpy(novo->id, personagem->id);
    strcpy(novo->nome, personagem->nome);
    strcpy(novo->alternate_names, personagem->alternate_names);
    strcpy(novo->house, personagem->house);
    strcpy(novo->ancestry, personagem->ancestry);
    strcpy(novo->species, personagem->species);
    strcpy(novo->patronus, personagem->patronus);
    novo->hogwartsStaff = personagem->hogwartsStaff;
    novo->hogwartsStudent = personagem->hogwartsStudent;
    strcpy(novo->actorName, personagem->actorName);
    novo->alive = personagem->alive;
    strcpy(novo->dateOfBirth, personagem->dateOfBirth);
    novo->yearOfBirth = personagem->yearOfBirth;
    strcpy(novo->eyeColour, personagem->eyeColour);
    strcpy(novo->gender, personagem->gender);
    strcpy(novo->hairColour, personagem->hairColour);
    novo->wizard = personagem->wizard;
}

void swap(Personagem array[], int i, int j)
{
    Personagem temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}

int getMax(Personagem arr[], int n) {
    int max = strlen(arr[0].id);
    for (int i = 1; i < n; i++) {
        int len = strlen(arr[i].id);
        if (len > max) {
            max = len;
        }
    }
    return max;
}

void countSort(Personagem arr[], int n, int exp) {
    Personagem output[n]; 
    int count[256] = {0};

    for (int i = 0; i < n; i++) {
        int index = (strlen(arr[i].id) - exp >= 0) ? (arr[i].id[strlen(arr[i].id) - exp]) : 0;
        count[index]++;
    }

    for (int i = 1; i < 256; i++) {
        count[i] += count[i - 1];
    }

    for (int i = n - 1; i >= 0; i--) {
        int index = (strlen(arr[i].id) - exp >= 0) ? (arr[i].id[strlen(arr[i].id) - exp]) : 0;
        output[count[index] - 1] = arr[i];
        count[index]--;
    }

    for (int i = 0; i < n; i++) {
        arr[i] = output[i];
    }
}

void radixSort(Personagem arr[], int n) {
    int max = getMax(arr, n);

    for (int exp = 1; exp <= max; exp++) {
        countSort(arr, n, exp);
    }
}


int main() {
    Personagem characters[406];
    Personagem arrayPersonagem[30];
    int tamArray = 0;
    char n[50];

    FILE *arq = fopen("/tmp/characters.csv", "r");
    if (arq == NULL) {
        printf("File not found\n");
        return 0;
    }

    char str[300];
    fgets(str, sizeof(str), arq);
    int i = 0;
    while (fgets(str, sizeof(str), arq)) {
        ler(&characters[i], str);
        i++;
    }

    fclose(arq);
    scanf(" %[^\r\n]s", n);
    while(strcmp(n, "FIM") != 0){
        for (int i = 0; i < 406; i++) {
            if (strcmp(characters[i].id, n) == 0) {
                arrayPersonagem[tamArray] = characters[i];
                tamArray++;
                break;
            }
        }
        scanf(" %[^\r\n]s", n);
    }

    radixSort(arrayPersonagem, tamArray);

    for (int i = 0; i < tamArray; i++) {
        imprimir(&arrayPersonagem[i]);
    }

    return 0;
}