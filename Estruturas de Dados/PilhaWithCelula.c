#include <stdio.h>
#include <stdlib.h>

typedef struct Celula {
    int elemento;
    struct Celula* prox;
}Celula;

Celula* new_celula(int x){
    Celula tmp* = (Celula*)malloc(sizeof(Celula));
    tmp->elemento = x;
    tmp->prox = NULL;
    return tmp;
}

typedef struct Pilha(){
    struct Celula *topo;
}

Celula* new_pilha(int x){
    Pilha tmp;
    tmp->topo = NULL;
    return tmp;
}

void push(Pilha *p,int x){
    Celula tmp* = new_celula(x);
    tmp->prox = p->topo;
    p->topo = tmp;
}

int pop(Pilha *p){
    Celula tmp* = p->topo;
    p->topo = p->topo->prox;
    int elemento = tmp->elemento;
    free(tmp);
    return elemento;
}

int main(){


    return 0;
}