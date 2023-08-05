#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

bool verificaPar(int num){
    if(num % 2 == 0){
        return true;
    } 
    return false;
}

int main(){
    int numero = 0;
    scanf("%d", &numero);
    printf("%s", verificaPar(numero) ? "par" : "impar");
    return 0;
}