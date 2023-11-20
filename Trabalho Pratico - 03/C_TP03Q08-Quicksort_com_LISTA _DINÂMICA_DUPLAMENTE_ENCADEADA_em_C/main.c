#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

typedef struct {
    int id;
    char nome[50];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[50];
    char estadoNascimento[50];
} Jogador;

typedef struct CelulaDupla {
    Jogador dados;           
    struct CelulaDupla* prox; 
    struct CelulaDupla* ant;
} CelulaDupla;

CelulaDupla* novaCelulaDupla(Jogador dados) {
    CelulaDupla* nova = (CelulaDupla*) malloc(sizeof(CelulaDupla));
    nova->dados = dados;
    nova->prox = NULL;
    nova->ant = NULL;
    return nova;
} 

float inicioTempo, fimTempo;
int comp = 0, mov = 0;
CelulaDupla* primeiro;
CelulaDupla* ultimo;

void imprimir(Jogador *jogador) {
    printf("[%i ## %s ## %i ## %i ## %i ## %s ## %s ## %s]\n", jogador->id, jogador->nome, jogador->altura, jogador->peso, jogador->anoNascimento, jogador->universidade, jogador->cidadeNascimento, jogador->estadoNascimento);
}

void substituirVirgula(char *str) {
    int tamanho = strlen(str);
    char tmp[3 * tamanho];
    int j = 0; 

    for (int i = 0; i < tamanho; i++) {
        if (str[i] == ',' && str[i+1] == ',') {
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
        } else {
            tmp[j++] = str[i];
        }
    }

    if (tmp[j - 2] == ',') {
        tmp[j-1] = 'n';
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

void ler(char str[300], Jogador *jogador) {
    substituirVirgula(str);
    str[strcspn(str, "\n")] = '\0'; 
    char *tmp;
    int i = 0;
    tmp = strtok(str, ",");

    while(tmp != NULL) {
        if(i % 8 == 0) {
            jogador->id = atoi(tmp);
        } else if(i % 8 == 1) {
            strcpy(jogador->nome, tmp);
        } else if(i % 8 == 2) {
            jogador->altura = atoi(tmp);
        } else if(i % 8 == 3) {
            jogador->peso = atoi(tmp);
        } else if(i % 8 == 4) {
            strcpy(jogador->universidade, tmp);
        } else if(i % 8 == 5) {
            jogador->anoNascimento = atoi(tmp);
        } else if(i % 8 == 6) {
            strcpy(jogador->cidadeNascimento, tmp);
        } else if(i % 8 == 7) {
            strcpy(jogador->estadoNascimento, tmp);
        }
        i++;
        tmp = strtok(NULL, ",");
    }   
}

void clonar(Jogador *jogador, Jogador *novo) {
    novo->id = jogador->id;
    strcpy(novo->nome, jogador->nome);
    novo->altura = jogador->altura;
    novo->peso = jogador->peso;
    strcpy(novo->universidade, jogador->universidade);
    novo->anoNascimento = jogador->anoNascimento;
    strcpy(novo->cidadeNascimento, jogador->cidadeNascimento);
    strcpy(novo->estadoNascimento, jogador->estadoNascimento);
}

void trocar(Jogador *j1, Jogador *j2) {
    mov += 3;
    Jogador tmp;
    clonar(j1, &tmp);
    clonar(j2, j1);
    clonar(&tmp, j2);
    mov += 3;
}

void inserirNoFim(Jogador jogador) {
    ultimo->prox = novaCelulaDupla(jogador);
    ultimo->prox->ant = ultimo;
    ultimo = ultimo->prox;
}

void mostrarLista() {
    CelulaDupla* i = primeiro->prox;
    while(i != NULL) {
        imprimir(&i->dados);
        i = i->prox;
    }
}

int calcularTamanho() {
   int tamanho = 0; 
   CelulaDupla* i;
   for(i = primeiro; i != ultimo; i = i->prox, tamanho++);
   return tamanho;
}

void inicializarLista() {
    Jogador tmp;
    tmp.id = 0;
    strcpy(tmp.nome, "");
    tmp.altura = 0;
    tmp.peso = 0;
    strcpy(tmp.universidade, "");
    tmp.anoNascimento = 0;
    strcpy(tmp.cidadeNascimento, "");
    strcpy(tmp.estadoNascimento, "");

    primeiro = novaCelulaDupla(tmp);
    ultimo = primeiro;
}

void ordenarQuickSortRecursivo(CelulaDupla* esquerda, CelulaDupla* direita) {
    CelulaDupla* i = esquerda;
    CelulaDupla* j = direita;
    CelulaDupla* pivo = direita;

    while(i->ant != j && i->ant != j->prox) {
        comp++;
        while(strcmp(i->dados.estadoNascimento, pivo->dados.estadoNascimento) < 0 ||
              (strcmp(i->dados.estadoNascimento, pivo->dados.estadoNascimento) == 0 &&
               strcmp(i->dados.nome, pivo->dados.nome) < 0)) {
            i = i->prox;
            comp++;
        }

        comp++;
        while(strcmp(j->dados.estadoNascimento, pivo->dados.estadoNascimento) > 0 ||
              (strcmp(j->dados.estadoNascimento, pivo->dados.estadoNascimento) == 0 &&
               strcmp(j->dados.nome, pivo->dados.nome) > 0)) {
            j = j->ant;
            comp++;
        }

        if(i->ant != j && i->ant != j->prox) {
            trocar(&i->dados, &j->dados);
            i = i->prox;
            j = j->ant;
        }
    }

    if(j != esquerda && esquerda->ant != j) {
        ordenarQuickSortRecursivo(esquerda, j);
    }
    if(i != direita && direita->prox != i) {
        ordenarQuickSortRecursivo(i, direita);
    }
}

void ordenarQuickSort() {
    ordenarQuickSortRecursivo(primeiro->prox, ultimo);
} 

int main() {
    Jogador jogadores[3923];
    char entrada[5];

    FILE *arquivo;
    arquivo = fopen("/tmp/players.csv", "r");

    if(arquivo == NULL) {
        printf("Erro ao abrir o arquivo\n");
        return 1;
    }

    char linha[300];
    fgets(linha, sizeof(linha), arquivo); 
    int i = 0;
    while (fgets(linha, sizeof(linha), arquivo)) {
        ler(linha, &jogadores[i]);
        i++;
    }

    fclose(arquivo);

    scanf("%s", entrada);
    int j = 0;
    Jogador temporario;
    inicializarLista();
    while(strcmp(entrada, "FIM") != 0) {
        clonar(&jogadores[atoi(entrada)], &temporario);
        inserirNoFim(temporario);
        scanf("%s", entrada);
    }
    
    inicioTempo = clock();
    ordenarQuickSort();
    fimTempo = clock();

    mostrarLista();

    float tempo = fimTempo - inicioTempo;  

    arquivo = fopen("808721_quicksort2.txt", "w");
    fprintf(arquivo, "808721\t %i\t %i\t %fs", comp, mov, tempo/1000.0);
    fclose(arquivo);

    return 0;
}
