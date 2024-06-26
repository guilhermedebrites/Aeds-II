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

void constroi(Personagem *personagem, int tamHeap, int i) {
    while (i > 1) {
        int pai = i / 2;
        if (strcmp(personagem[i].hairColour, personagem[pai].hairColour) > 0 || 
           (strcmp(personagem[i].hairColour, personagem[pai].hairColour) == 0 &&
            strcmp(personagem[i].nome, personagem[pai].nome) > 0)) {
            swap(personagem, i, pai);
            i = pai;
        } else {
            break;
        }
    }
}

int getMaiorFilho(Personagem *personagem, int i, int tamHeap) {
    int filhoEsq = 2 * i;
    int filhoDir = 2 * i + 1;
    if (filhoDir > tamHeap) return filhoEsq; // Sem filho direito
    if (strcmp(personagem[filhoEsq].hairColour, personagem[filhoDir].hairColour) > 0 || 
       (strcmp(personagem[filhoEsq].hairColour, personagem[filhoDir].hairColour) == 0 &&
        strcmp(personagem[filhoEsq].nome, personagem[filhoDir].nome) > 0)) {
        return filhoEsq;
    } else {
        return filhoDir;
    }
}

void reconstroi(Personagem *personagem, int tamHeap) {
    int i = 1;
    while (i <= (tamHeap / 2)) {
        int filho = getMaiorFilho(personagem, i, tamHeap);
        if (strcmp(personagem[i].hairColour, personagem[filho].hairColour) < 0 ||
           (strcmp(personagem[i].hairColour, personagem[filho].hairColour) == 0 &&
            strcmp(personagem[i].nome, personagem[filho].nome) < 0)) {
            swap(personagem, i, filho);
            i = filho;
        } else {
            break;
        }
    }
}

void heapsortParcial(Personagem *personagem, int n) {
    Personagem* tmp = (Personagem*) malloc((n+1) * sizeof(Personagem));
    for (int i = 0; i < n; i++) {
        tmp[i+1] = personagem[i];
    }

    int k = 10;
    for (int tamHeap = 2; tamHeap <= k; tamHeap++) {
        constroi(tmp, tamHeap, tamHeap);
    }

    for (int i = k + 1; i <= n; i++) {
        if (strcmp(tmp[i].hairColour, tmp[1].hairColour) < 0) {
            swap(tmp, i, 1);
            reconstroi(tmp, 10);
        }
    }

    int tamHeap = k;
    while (tamHeap > 1) {
        swap(tmp, 1, tamHeap);
        tamHeap--;
        reconstroi(tmp, tamHeap);
    }

    for (int i = 0; i < n; i++) {
        personagem[i] = tmp[i+1];
    }
    free(tmp);
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

    heapsortParcial(arrayPersonagem, tamArray);

    for (int i = 0; i < 10; i++) {
        imprimir(&arrayPersonagem[i]);
    }

    return 0;
}