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

int main(){

    return 0;
}