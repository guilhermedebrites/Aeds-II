#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Estudante{
    char nome[50];
    char regiao;
    int distancia;
}typedef Estudante;

int main(){

    int n;
    scanf("%d", &n);

    Estudante estudantes[n];

    for(int i = 0; i < n; i++) {
        scanf("%s %c %d", estudantes[i].nome, &estudantes[i].regiao, &estudantes[i].distancia);
    }

    for(int i = 0; i < n; i++) {
        int posicaoMenor = i;
        Estudante menor = estudantes[i];

        for(int j = (i + 1); j < n; j++) {
            if(menor.distancia > estudantes[j].distancia) {
                menor = estudantes[j];
                posicaoMenor = j;
            }else if(menor.distancia == estudantes[j].distancia) {
                if(menor.regiao > estudantes[j].regiao){
                    menor = estudantes[j];
                    posicaoMenor = j;
                }else if(menor.regiao == estudantes[j].regiao) {
                    if( strcmp(menor.nome, estudantes[j].nome) < 0){
                        menor = estudantes[j];
                        posicaoMenor = j;
                    }
                }
            }
        }

        Estudante aux = estudantes[i];
        estudantes[i] = menor;
        estudantes[posicaoMenor] = aux;
    }

    for(int i = 0; i < n; i++) {
        printf("%s\n", estudantes[i].nome);
    }

    return 0;
}