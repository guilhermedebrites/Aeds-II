#include <err.h>
#include <stdlib.h>
#include <stdio.h>
#include "arvorebinaria.h"

typedef Struct{
    int numero;
    Celula *prox;
} Celula;

typedef Struct{
    int numero;
    CelulaMatriz *prox, *ant;
    CelulaMatriz *sup, *inf;
} CelulaMatriz;

typedef Struct{
    int numero;
    No *esq, *dir;
} No;

No* novoNo(int elemento) {
   No* novo = (No*) malloc(sizeof(No));
   novo->elemento = elemento;
   novo->esq = NULL;
   novo->dir = NULL;
   return novo;
}

No* raiz;

void start() {
   raiz = NULL;
}


Celula* encontrarRepetidos(No* raiz, CelulaMatriz* inicio) {
    Celula* primeiro;
    caminharCentral(primeiro, raiz, inicio);
    return primeiro;
}

void caminharCentral(Celula* primeiro, No* i, CelulaMatriz* inicio) {
    if (i != NULL) {

        if(containsMatriz(inicio, i->elemento)){
            ListaInserirInicio(primeiro, i->elemento)
        }
        caminharCentral(i->esq);
        caminharCentral(i->dir);
    }
}

bool containsMatriz(CelulaMatriz* primeiro, int elemento) {

    bool resultado = false;

    for(CelulaMatriz* i = inicio; i != NULL; i = i->inf){
        for(CelulaMatriz* j = i; j != NULL; i = j->prox){
            if(j->numero == numero){
                resultado = true;
                i = j = NULL;
            }
        }
    }
    return resultado;
}

void ListaInserirInicio(Celula* primeiro, int elemento){
    Celula* nova = (Celula*)malloc(sizeof(Celula*));
    nova->prox = primeiro->prox;
    primeiro->prox = nova;
}