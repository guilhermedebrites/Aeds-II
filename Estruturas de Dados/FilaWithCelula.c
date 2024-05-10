#include <stdio.h>
#include <stdlib.h>

typedef struct Celula{
    int elemento;
    struct Celula *prox;
}

Celula* new_celula(int elemento){
    Celula tmp* = (Celula*)malloc(sizeof(Celula));
    tmp->elemento = elemento;
    tmp->prox = NULL;
    return tmp;
}

typedef struct Fila{
    struct Celula *primeiro;
    struct Celula *ultimo;
}

Celula* new_fila(int elemento){
    Fila tmp;
    tmp.primeiro = tmp.ultimo = new_celula(-1);
    return tmp;
}

void inserir(Fila* fila, int x){
    fila->ultimo->prox = new_celula(x);
    fila->ultimo = ultimo->prox;
}

int remover(Fila* fila){
    Celula *tmp = fila->primeiro;
    int elemento = tmp->elemento; 

    fila->primeiro = fila->primeiro->prox;
    free(tmp);
    return elemento;
}

int main(){

    return 0;
}