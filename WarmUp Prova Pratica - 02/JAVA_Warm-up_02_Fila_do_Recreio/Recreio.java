import java.util.Scanner;

class Recreio {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for(int i = 0; i < n; i++){
            int numeroDeAlunos = sc.nextInt();
            int[] notas = new int[numeroDeAlunos];
            for(int j = 0; j < numeroDeAlunos; j++){
                notas[j] = sc.nextInt();
            }
            int[] notasCloned = notas.clone();
            for(int m = 0; m < numeroDeAlunos - 1; m++){
                for(int k = 0; k < numeroDeAlunos - 1; k++){
                    if(notas[k] < notas[k+1]){
                        int aux = notas[k];
                        notas[k] = notas[k+1];
                        notas[k+1] = aux;
                    }
                }
            }
            int swapCounts = 0;
            for(int u = 0; u < numeroDeAlunos; u++){
                if(notas[u] == notasCloned[u]){
                    swapCounts++;
                }
            }


            System.out.println(swapCounts);
        }
    }
}