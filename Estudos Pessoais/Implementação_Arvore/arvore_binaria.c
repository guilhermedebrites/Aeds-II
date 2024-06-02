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


Celula* encontrarRepetidos(No* raiz, CelulaMatriz* inicio) {
    Celula* primeiro;
    for (CelulaMatriz i = inicio; i != null; i = i.inf) {
            for (CelulaMatriz j = i; j != null; j = j.dir) {
                contaisArvore(i.elemento, raiz, primeiro);
            }
        }
}

No* contaisArvore(int elemento, No* i, Celula* primeiro) {
    if(elemento == i.elemento){
        adicionarLista(i.elemento, primeiro);
    }
    contaisArvore(elemento, i.esq);
    contaisArvore(elemento, i.dir);
}

void adicionarLista(int elemento, Celula* primeiro) {
    Celula* nova = (Celula*)malloc(sizeof(Celula*));
    nova-prox = primeiro->prox;
    primeiro.prox = nova;
}