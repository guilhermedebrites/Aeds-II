#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

#define MAX_LENGTH 200
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
} Personagem;

typedef struct No {
    Personagem data;
    struct No* esquerda;
    struct No* direita;
    int altura;
} No;

No* raiz = NULL;
int numComparacoes = 0;
double tempo = 0.0;
clock_t inicio, fim;

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

No* criarNo(Personagem data) {
    No* no = (No*)malloc(sizeof(No));
    no->data = data;
    no->esquerda = NULL;
    no->direita = NULL;
    no->altura = 1;
    return no;
}

int altura(No* N) {
    if (N == NULL)
        return 0;
    return N->altura;
}

int max(int a, int b) {
    return (a > b) ? a : b;
}

No* rotacaoDireita(No* y) {
    No* x = y->esquerda;
    No* T2 = x->direita;

    x->direita = y;
    y->esquerda = T2;

    y->altura = max(altura(y->esquerda), altura(y->direita)) + 1;
    x->altura = max(altura(x->esquerda), altura(x->direita)) + 1;

    return x;
}

No* rotacaoEsquerda(No* x) {
    No* y = x->direita;
    No* T2 = y->esquerda;

    y->esquerda = x;
    x->direita = T2;

    x->altura = max(altura(x->esquerda), altura(x->direita)) + 1;
    y->altura = max(altura(y->esquerda), altura(y->direita)) + 1;

    return y;
}

int getBalance(No* N) {
    if (N == NULL)
        return 0;
    return altura(N->esquerda) - altura(N->direita);
}

No* inserirAVL(No* no, Personagem data) {
    if (no == NULL)
        return criarNo(data);

    if (strcmp(data.nome, no->data.nome) < 0)
        no->esquerda = inserirAVL(no->esquerda, data);
    else if (strcmp(data.nome, no->data.nome) > 0)
        no->direita = inserirAVL(no->direita, data);
    else
        return no;

    no->altura = 1 + max(altura(no->esquerda), altura(no->direita));

    int balance = getBalance(no);

    if (balance > 1 && strcmp(data.nome, no->esquerda->data.nome) < 0)
        return rotacaoDireita(no);

    if (balance < -1 && strcmp(data.nome, no->direita->data.nome) > 0)
        return rotacaoEsquerda(no);

    if (balance > 1 && strcmp(data.nome, no->esquerda->data.nome) > 0) {
        no->esquerda = rotacaoEsquerda(no->esquerda);
        return rotacaoDireita(no);
    }

    if (balance < -1 && strcmp(data.nome, no->direita->data.nome) < 0) {
        no->direita = rotacaoDireita(no->direita);
        return rotacaoEsquerda(no);
    }

    return no;
}

bool pesquisarAVL(No* no, char* nome, char* caminho) {
    if (no == NULL)
        return false;

    if (strcmp(nome, no->data.nome) == 0)
        return true;

    numComparacoes++;
    if (strcmp(nome, no->data.nome) < 0) {
        strcat(caminho, " esq");
        return pesquisarAVL(no->esquerda, nome, caminho);
    } else {
        strcat(caminho, " dir");
        return pesquisarAVL(no->direita, nome, caminho);
    }
}

void inserirNaAVL(Personagem personagens[]) {
    char id[100];
    scanf(" %[^\n\r]", id);
    while (strcmp(id, "FIM") != 0) {
        for (int i = 0; i < MAX_TAM; i++) {
            if (strcmp(personagens[i].id, id) == 0) {
                raiz = inserirAVL(raiz, personagens[i]);
                break;
            }
        }
        scanf(" %[^\n\r]", id);
    }
}

void procurarNaAVL() {
    char nome[100];
    char caminho[100];
    scanf(" %[^\n\r]", nome);
    inicio = clock();
    while (strcmp(nome, "FIM") != 0) {
        caminho[0] = '\0';
        if (pesquisarAVL(raiz, nome, caminho)) {
            printf("%s => raiz%s SIM\n", nome, caminho);
        } else {
            printf("%s => raiz%s NAO\n", nome, caminho);
        }
        scanf(" %[^\n\r]", nome);
    }
    fim = clock();
    tempo = ((double)(fim - inicio)) / CLOCKS_PER_SEC;
}

void registroLog() {
    FILE* logFile = fopen("808721_avl.txt", "w");
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
    inserirNaAVL(personagens);
    procurarNaAVL();
    registroLog();
    return 0;
}
