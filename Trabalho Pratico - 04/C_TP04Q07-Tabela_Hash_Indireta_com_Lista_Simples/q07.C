#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

#define MAX_LENGTH 200
#define TAM_TAB 21
#define MAX_TAM 404

typedef struct LocalDate {
    int dia, mes, ano;
} LocalDate;

typedef struct Personagem {
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
    LocalDate dateOfBirth;
    int yearOfBirth;
    char eyeColour[MAX_LENGTH];
    char gender[MAX_LENGTH];
    char hairColour[MAX_LENGTH];
    bool wizard;
    struct Personagem* next;
} Personagem;

typedef struct Celula {
    Personagem elemento;
    struct Celula* prox;
} Celula;

typedef struct {
    Celula* head;
} Lista;

Lista tabela[TAM_TAB];
int numComparacoes = 0;
double tempo = 0.0;
clock_t inicio, fim;

void inicializarTabela() {
    for (int i = 0; i < TAM_TAB; i++) {
        tabela[i].head = NULL;
    }
}

Celula* novaCelula(Personagem elemento) {
    Celula* nova = (Celula*)malloc(sizeof(Celula));
    nova->elemento = elemento;
    nova->prox = NULL;
    return nova;
}

int calcularHash(char *str) {
    int asciiSum = 0;
    for (int i = 0; str[i] != '\0'; i++) {
        asciiSum += str[i];
    }
    return asciiSum % TAM_TAB;
}

void inserir(Personagem p) {
    int pos = calcularHash(p.nome);
    Celula* nova = novaCelula(p);
    nova->prox = tabela[pos].head;
    tabela[pos].head = nova;
}

void pesquisar(char* nome) {
    int pos = calcularHash(nome);
    Celula* tmp = tabela[pos].head;
    bool encontrado = false;
    while (tmp != NULL) {
        numComparacoes++;
        if (strcmp(tmp->elemento.nome, nome) == 0) {
            encontrado = true;
            break;
        }
        tmp = tmp->prox;
    }
    if (encontrado) {
        printf("%s (pos: %d) SIM\n", nome, pos);
    } else {
        printf("%s NAO\n", nome);
    }
}

void inicializarPersonagem(Personagem* p) {
    strcpy(p->id, "");
    strcpy(p->nome, "");
    strcpy(p->alternate_names, "");
    strcpy(p->house, "");
    strcpy(p->ancestry, "");
    strcpy(p->species, "");
    strcpy(p->patronus, "");
    p->hogwartsStaff = false;
    p->hogwartsStudent = false;
    strcpy(p->actorName, "");
    p->alive = false;
    p->dateOfBirth.dia = -1;
    p->dateOfBirth.mes = -1;
    p->dateOfBirth.ano = -1;
    p->yearOfBirth = -1;
    strcpy(p->eyeColour, "");
    strcpy(p->gender, "");
    strcpy(p->hairColour, "");
    p->wizard = false;
    p->next = NULL;
}

LocalDate converterData(char data[]) {
    LocalDate localDate;
    sscanf(data, "%d-%02d-%04d", &localDate.dia, &localDate.mes, &localDate.ano);
    return localDate;
}

void definirPersonagem(Personagem* p, char* linha) {
    char str[300], date[11], year[5];
    strcpy(str, linha);
    int x = 0, y = 0;

    while (str[x] != ';'){p->id[y] = str[x];x++;y++;}p->id[y] = '\0';x++;y = 0;
    while (str[x] != ';'){p->nome[y] = str[x];x++;y++;}p->nome[y] = '\0';x++;y = 0;
    while (str[x] != ';'){if (str[x] != '[' && str[x] != ']' && str[x] != 39){p->alternate_names[y] = str[x];y++;}x++;}p->alternate_names[y] = '\0';x++;y = 0;
    while (str[x] != ';'){p->house[y] = str[x];x++;y++;}p->house[y] = '\0';x++;y = 0;
    while (str[x] != ';'){p->ancestry[y] = str[x];x++;y++;}p->ancestry[y] = '\0';x++;y = 0;
    while (str[x] != ';'){p->species[y] = str[x];x++;y++;}p->species[y] = '\0';x++;y = 0;
    while (str[x] != ';'){p->patronus[y] = str[x];x++;y++;}p->patronus[y] = '\0';x++;y = 0;
    while (str[x] != ';'){if (str[x] == 'V'){p->hogwartsStaff = true;x += 10;}else{p->hogwartsStaff = false;x += 5;}}x++;y = 0;
    while (str[x] != ';'){if (str[x] == 'V'){p->hogwartsStudent = true;x += 10;}else{p->hogwartsStudent = false;x += 5;}}x++;y = 0;
    while (str[x] != ';'){p->actorName[y] = str[x];x++;y++;}p->actorName[y] = '\0';x++;y = 0;
    while (str[x] != ';'){if (str[x] == 'V'){p->alive = true;x += 10;}else{p->alive = false;x += 5;}}x++;y = 0;
    while (str[x] != ';'){date[y] = str[x];x++;y++;}LocalDate data = converterData(date);p->dateOfBirth = data;x++;y = 0;
    while (str[x] != ';'){year[y] = str[x];x++;y++;}p->yearOfBirth = atoi(year);x++;y = 0;
    while (str[x] != ';'){p->eyeColour[y] = str[x];x++;y++;}p->eyeColour[y] = '\0';x++;y = 0;
    while (str[x] != ';'){p->gender[y] = str[x];x++;y++;}p->gender[y] = '\0';x++;y = 0;
    while (str[x] != ';'){p->hairColour[y] = str[x];x++;y++;}p->hairColour[y] = '\0';x++;y = 0;if (str[x] == 'V'){p->wizard = true;x += 10;}else{p->wizard = false;x += 5;}
}

void lerArquivo(char* nomeDoArquivo, Personagem personagens[]) {
    FILE* file = fopen(nomeDoArquivo, "r");
    if (file == NULL) {
        printf("Erro ao abrir o arquivo.\n");
        return;
    }

    char linha[300];
    fgets(linha, sizeof(linha), file); // Pular cabeçalho

    int i = 0;
    while (fgets(linha, sizeof(linha), file)) {
        inicializarPersonagem(&personagens[i]);
        definirPersonagem(&personagens[i], linha);
        i++;
    }

    fclose(file);
}

Personagem* encontrarPorId(Personagem personagens[], char* id) {
    for (int i = 0; i < MAX_TAM; i++) {
        if (strcmp(personagens[i].id, id) == 0) {
            return &personagens[i];
        }
    }
    return NULL;
}

void inserirNaHash(Personagem personagens[]) {
    char id[100];
    scanf(" %[^\n\r]", id);
    while (strcmp(id, "FIM") != 0) {
        Personagem* p = encontrarPorId(personagens, id);
        if (p != NULL) {
            inserir(*p);
        }
        scanf(" %[^\n\r]", id);
    }
}

void procurarNaHash() {
    char nome[100];
    scanf(" %[^\n\r]", nome);
    inicio = clock();
    while (strcmp(nome, "FIM") != 0) {
        pesquisar(nome);
        scanf(" %[^\n\r]", nome);
    }
    fim = clock();
    tempo = ((double)(fim - inicio)) / CLOCKS_PER_SEC;
}

void registroLog() {
    FILE* logFile = fopen("808721_hashIndireta.txt", "w");
    if (logFile != NULL) {
        fprintf(logFile, "808721\t%.2f ms\t%d comparacoes\n", tempo * 1000, numComparacoes);
        fclose(logFile);
    } else {
        printf("Erro ao criar o arquivo de log.\n");
    }
}

int main() {
    Personagem personagens[MAX_TAM];
    lerArquivo("/tmp/characters.csv", personagens);
    inicializarTabela();
    inserirNaHash(personagens);
    procurarNaHash();
    registroLog();
    return 0;
}
