#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define MAX_LENGTH 100
#define MAX_TAM 6

typedef struct
{
    int id;
    char nome[MAX_LENGTH];
    int altura;
    int peso;
    char universidade[MAX_LENGTH];
    int anoNascimento;
    char cidadeNascimento[MAX_LENGTH];
    char estadoNascimento[MAX_LENGTH];
} Jogador;


Jogador array[MAX_TAM];

typedef struct Celula
{
    Jogador elemento;
    struct Celula *prox;
} Celula;

Celula *novaCelula(Jogador elemento)
{
    Celula *nova = (Celula *)malloc(sizeof(Celula));
    nova->elemento = elemento;
    nova->prox = NULL;
    return nova;
}

Celula *primeiro;
Celula *ultimo;

typedef struct Pilha
{
    struct Celula *topo;
    struct Celula *fim;
} Pilha;

Pilha *novaPilha()
{
    Pilha *nova = (Pilha *)malloc(sizeof(Pilha));
    nova->topo = NULL;
    nova->fim = NULL;
    return nova;
}

void inserir(Jogador x) {
    Celula *tmp = novaCelula(x);
    if (primeiro == NULL) {
        primeiro = ultimo = tmp;
    } else {
        ultimo->prox = tmp;
        ultimo = tmp;
    }
}

Jogador remover() {
    if (primeiro == NULL) {
        printf("Erro ao remover!");
        exit(1);
    }

    Celula *i;
    Jogador resp;
    
    if (primeiro == ultimo) {
        resp = primeiro->elemento;
        free(primeiro);
        primeiro = ultimo = NULL;
    } else {
        for (i = primeiro; i->prox != ultimo; i = i->prox);
        resp = ultimo->elemento;
        free(ultimo);
        ultimo = i;
        i->prox = NULL;
    }

    return resp;
}

void mostrar()
{
    int j = 0;
    for (Celula *i = primeiro->prox; i != NULL; i = i->prox)
    {
        printf("[%i] ## %s ## %i ## %i ## %i ## %s ## %s ## %s ##\n", j, i->elemento.nome, i->elemento.altura, i->elemento.peso, i->elemento.anoNascimento, i->elemento.universidade, i->elemento.cidadeNascimento, i->elemento.estadoNascimento);
        j++;
    }
}

void imprimir(Jogador *jogador)
{
    printf("[%i ## %s ## %i ## %i ## %i ## %s ## %s ## %s]\n", jogador->id, jogador->nome, jogador->altura, jogador->peso, jogador->anoNascimento, jogador->universidade, jogador->cidadeNascimento, jogador->estadoNascimento);
}

void replaceVirgula(char *str)
{
    int tamanho = strlen(str);
    char tmp[3 * tamanho];
    int j = 0;

    for (int i = 0; i < tamanho; i++)
    {
        if (str[i] == ',' && str[i + 1] == ',')
        {
            tmp[j++] = ',';
            tmp[j++] = 'n';
            tmp[j++] = 'a';
            tmp[j++] = 'o';
            tmp[j++] = ' ';
            tmp[j++] = 'i';
            tmp[j++] = 'n';
            tmp[j++] = 'f';
            tmp[j++] = 'o';
            tmp[j++] = 'r';
            tmp[j++] = 'm';
            tmp[j++] = 'a';
            tmp[j++] = 'd';
            tmp[j++] = 'o';
            tmp[j++] = ',';

            i++;
        }
        else
        {
            tmp[j++] = str[i];
        }
    }

    if (tmp[j - 2] == ',')
    {
        tmp[j - 1] = 'n';
        tmp[j++] = 'a';
        tmp[j++] = 'o';
        tmp[j++] = ' ';
        tmp[j++] = 'i';
        tmp[j++] = 'n';
        tmp[j++] = 'f';
        tmp[j++] = 'o';
        tmp[j++] = 'r';
        tmp[j++] = 'm';
        tmp[j++] = 'a';
        tmp[j++] = 'd';
        tmp[j++] = 'o';
    }

    tmp[j] = '\0';
    strcpy(str, tmp);
}

void clone(Jogador *jogador, Jogador *novo)
{
    novo->id = jogador->id;
    strcpy(novo->nome, jogador->nome);
    novo->altura = jogador->altura;
    novo->peso = jogador->peso;
    strcpy(novo->universidade, jogador->universidade);
    novo->anoNascimento = jogador->anoNascimento;
    strcpy(novo->cidadeNascimento, jogador->cidadeNascimento);
    strcpy(novo->estadoNascimento, jogador->estadoNascimento);
}

void ler(Jogador *jogador, char str[300])
{
    replaceVirgula(str);
    str[strcspn(str, "\n")] = '\0';

    char *token;
    token = strtok(str, ",");
    int i = 0;

    while (token != NULL)
    {
        if (i % 8 == 0)
        {
            jogador->id = atoi(token);
        }
        else if (i % 8 == 1)
        {
            strcpy(jogador->nome, token);
        }
        else if (i % 8 == 2)
        {
            jogador->altura = atoi(token);
        }
        else if (i % 8 == 3)
        {
            jogador->peso = atoi(token);
        }
        else if (i % 8 == 4)
        {
            strcpy(jogador->universidade, token);
        }
        else if (i % 8 == 5)
        {
            jogador->anoNascimento = atoi(token);
        }
        else if (i % 8 == 6)
        {
            strcpy(jogador->cidadeNascimento, token);
        }
        else if (i % 8 == 7)
        {
            strcpy(jogador->estadoNascimento, token);
        }
        i++;

        token = strtok(NULL, ",");
    }
}

int main()
{

    Jogador players[3923];
    char a[5];

    FILE *arq = fopen("/tmp/players.csv", "r");

    primeiro = novaCelula(players[0]);
    ultimo = primeiro;

    if (arq == NULL)
    {
        printf("File not found\n");
        return 0;
    }

    char str[300];
    fgets(str, sizeof(str), arq);
    int i = 0;
    while (fgets(str, sizeof(str), arq))
    {
        ler(&players[i], str);
        i++;
    }

    scanf("%s", a);
    int cont = 0;
    while (1)
    {
        if (strcmp(a, "FIM") == 0)
            break;
        inserir(players[atoi(a)]);
        scanf("%s", a);
    }

    scanf("%s", a);
    int qtd = atoi(a);

    for (int i = 0; i < qtd; i++)
    {
        scanf(" %[^\n]", a);
        char str[MAX_LENGTH][MAX_LENGTH];
        char *token;
        token = strtok(a, " ");

        for (int i = 0; token != NULL; i++)
        {
            strcpy(str[i], token);
            token = strtok(NULL, " ");
        }
        if (strcmp(str[0], "I") == 0)
        {
            inserir(players[atoi(str[1])]);
        }
        else if (strcmp(str[0], "R") == 0)
        {
            Jogador player = remover();
            printf("(R) %s\n", player.nome);
        }
    }
    mostrar();

    fclose(arq);

    return 0;
}
