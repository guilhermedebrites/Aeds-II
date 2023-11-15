#include <stdio.h>
#include <stdlib.h>

int main(){

    int n, k, u;

    scanf("%d", &n);
    scanf("%d", &k);
    scanf("%d", &u);

    int cartelas[n][k];

    for(int i = 0; i < n; i++){
        for(int j = 0; j < k; j++){
            scanf("%d", &cartelas[i][j]);
        }
    }

    int sorteado;
    for (int i = 0; i < u; i++){
        scanf("%d", &sorteado);
        for(int linha = 0; linha < n; linha++){
            int count = 0;
            for(int coluna = 0; coluna < k; coluna++){
                if (cartelas[linha][coluna] == sorteado){
                    cartelas[linha][coluna] = 0;
                }
                if(cartelas[linha][coluna] == 0){
                    count++;
                }
            }
            if(count == k){
                printf("%d", linha+1);
                exit(1);
            }
        }
    }

    return 0;
}