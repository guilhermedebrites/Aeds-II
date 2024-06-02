#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define MAX_LENGTH 200
#define MAX_TAM 3923

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

Personagem array[MAX_TAM];
int n;

void start(){
    n = 0;
}

void inserirInicio(Personagem x) {
   int i;

   if(n >= MAX_TAM){
      printf("Erro ao inserir inicio!");
      exit(1);
   } 

   for(i = n; i > 0; i--){
      array[i] = array[i-1];
   }

   array[0] = x;
   n++;
}


void inserirFim(Personagem x) {
   if(n >= MAX_TAM){
      printf("Erro ao inserir fim!");
      exit(1);
   }

   array[n] = x;
   n++;
}


void inserir(Personagem x, int pos) {
   int i;

   if(n >= MAX_TAM || pos < 0 || pos > n){
      printf("Erro ao inserir pos!");
      exit(1);
   }

   for(i = n; i > pos; i--){
      array[i] = array[i-1];
   }

   array[pos] = x;
   n++;
}


Personagem removerInicio() {
   int i;
   Personagem resp;

   if (n == 0) {
      printf("Erro ao remover inicio!");
      exit(1);
   }

   resp = array[0];
   n--;

   for(i = 0; i < n; i++){
      array[i] = array[i+1];
   }

   return resp;
}


Personagem removerFim() {

   if (n == 0) {
      printf("Erro ao remover fim!");
      exit(1);
   }

   return array[--n];
}

Personagem remover(int pos) {
   int i;
   Personagem resp;

   if (n == 0 || pos < 0 || pos >= n) {
      printf("Erro ao remover pos!");
      exit(1);
   }

   resp = array[pos];
   n--;

   for(i = pos; i < n; i++){
      array[i] = array[i+1];
   }

   return resp;
}

void mostrar(){
    for(int i = 0; i < n; i++){
        imprimir(&array[i], i);
    }
}

void imprimir(Personagem *personagem, int i) {
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



    printf("[%d ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %d ## %s ## %s ## %s ## %s]\n",
        i,  
        strcmp(personagem->id, "-1") != 0 ? personagem->id : "", 
        strcmp(personagem->nome, "-1") != 0 ? personagem->nome : "", 
        strcmp(personagem->alternate_names, "-1") != 0 ? personagem->alternate_names : "", 
        strcmp(personagem->house, "-1") != 0 ? personagem->house : "",
        strcmp(personagem->ancestry, "-1") != 0 ? personagem->ancestry : "", 
        strcmp(personagem->species, "-1") != 0 ? personagem->species : "", 
        strcmp(personagem->patronus, "-1") != 0 ? personagem->patronus : "",
        personagem->hogwartsStaff ? "true" : "false", 
        personagem->hogwartsStudent ? "true" : "false",
        strcmp(personagem->actorName, "-1") != 0 ? personagem->actorName : "", 
        personagem->alive ? "true" : "false", 
        strcmp(personagem->dateOfBirth, "-1") != 0 ? personagem->dateOfBirth : "",
        personagem->yearOfBirth, 
        strcmp(personagem->eyeColour, "-1") != 0 ? personagem->eyeColour : "", 
        strcmp(personagem->gender, "-1") != 0 ? personagem->gender : "", 
        strcmp(personagem->hairColour, "-1") != 0 ? personagem->hairColour : "",
        personagem->wizard ? "true" : "false");
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
            case 7: personagem->hogwartsStaff = (strcmp(token, "VERDADEIRO") == 0); break;
            case 8: personagem->hogwartsStudent = (strcmp(token, "VERDADEIRO") == 0); break;
            case 9: strcpy(personagem->actorName, token); break;
            case 10: personagem->alive = (strcmp(token, "VERDADEIRO") == 0); break;
            case 12: strcpy(personagem->dateOfBirth, token); break;
            case 13: personagem->yearOfBirth = atoi(token); break;
            case 14: strcpy(personagem->eyeColour, token); break;
            case 15: strcpy(personagem->gender, token); break;
            case 16: strcpy(personagem->hairColour, token); break;
            case 17: personagem->wizard = (strcmp(token, "VERDADEIRO") == 0); break;
            default: break;
        }
        fieldIndex++;
        token = strtok(NULL, ";");
    }
}

int main() {
    Personagem characters[406];
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

    scanf(" %[^\r\n]s", n);
    int cont = 0;

    while(strcmp(n, "FIM") != 0){
        for (int i = 0; i < 406; i++) {
            if (strcmp(characters[i].id, n) == 0) {
                inserirFim(characters[i]);
                break;
            }
        }
        scanf(" %[^\r\n]s", n);
    }


    scanf(" %[^\r\n]s", n);
    int qtd = atoi(n);

    for(int i = 0; i < qtd; i++){
        getchar();
        scanf(" %[^\r\n]s", n);
        char str[MAX_LENGTH][MAX_LENGTH];
        char* token;
        token = strtok(n, " ");

        for(int i = 0; token != NULL; i++) {
            strcpy(str[i], token);
            token = strtok(NULL, " ");
        }

        if(strcmp(str[0], "II") == 0){
            for (int i = 0; i < 406; i++) {
                if (strcmp(characters[i].id, str[1]) == 0) {
                    inserirInicio(characters[i]);
                    break;
                }
            }
        }
        else if(strcmp(str[0], "IF") == 0){
            for (int i = 0; i < 406; i++) {
                if (strcmp(characters[i].id, str[1]) == 0) {
                    inserirFim(characters[i]);
                    break;
                }
            }
        }
        else if(strcmp(str[0], "I*") == 0){
            for (int i = 0; i < 406; i++) {
                if (strcmp(characters[i].id, str[2]) == 0) {
                    inserir(characters[i], atoi(str[1]));
                    break;
                }
            }
        }
        else if(strcmp(str[0], "RI") == 0){
            Personagem jogador = removerInicio();
            printf("(R) %s\n", jogador.nome);
        }
        else if(strcmp(str[0], "RF") == 0){
            Personagem jogador = removerFim();
            printf("(R) %s\n", jogador.nome);
        }
        else if(strcmp(str[0], "R*") == 0){
            Personagem jogador = remover(atoi(str[1]));
            printf("(R) %s\n", jogador.nome);
        }
    }
    mostrar();  

    fclose(arq);

    return 0;
}