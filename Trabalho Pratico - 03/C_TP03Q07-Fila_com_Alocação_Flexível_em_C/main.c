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
int qt = 0;

Jogador array[MAX_TAM];

int mediaAlturas(){
    float media = 0;
    int i = 0;
    Celula* j;
    for (j = primeiro->prox; j != NULL; j = j->prox) {
       media += j->elemento.altura;
       i++;
    }
    media = (float)media / i;
    media += 0.5;
    media = (int)media;
    return media;
}

Jogador remover()
{   
    if (primeiro == ultimo)
    {
        printf("Erro ao remover!\n");
        exit(1);
    }
    Celula *tmp = primeiro;
    primeiro = primeiro->prox;
    Jogador resp = primeiro->elemento;
    tmp->prox = NULL;
    free(tmp);
    tmp = NULL;
    qt--;
    return resp;
}

void inserir(Jogador x)
{
    if(qt == 5){
        remover();
    }
    ultimo->prox = novaCelula(x);
    ultimo = ultimo->prox;
    qt++;

    int somaAltura = 0;
    int numJogadores = 0;
    Celula *i;

    for (i = primeiro->prox; i != NULL; i = i->prox)
    {
        somaAltura += i->elemento.altura;
        numJogadores++;
    }

    if (numJogadores == 0)
    {
        printf("Erro ao calcular a média!\n");
        exit(1);
    }

    int media = mediaAlturas();

    printf("%i\n", media);
}

void mostrar()
{
    Celula *i;
    int count = 0;
    for (i = primeiro->prox; i != NULL; i = i->prox, count++)
    {
        printf("[%i] ## %s ## %i ## %i ## %i ## %s ## %s ## %s ##\n", count, i->elemento.nome, i->elemento.altura, i->elemento.peso, i->elemento.anoNascimento, i->elemento.universidade, i->elemento.cidadeNascimento, i->elemento.estadoNascimento);
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
