public class exercicio02_A {

    public static void maiorMenor(int[] array){
        int maior = array[0];
        int menor = array[0];

        for(int i = 1; i < array.length; i++){
            if(array[i] > maior){
                maior = array[i];
            }
            if(array[i] < menor){
                menor = array[i];
            }
        }
        System.out.println("Maior número: " + maior);
        System.out.println("Menor número: " + menor);
    }

    public static void main(String[] args) {
        int[] numeros = {10, 20, 30, 40, 5 , -2, 7};

        maiorMenor(numeros);
    }
}