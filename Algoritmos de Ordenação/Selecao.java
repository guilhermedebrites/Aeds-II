
public class Selecao {
    public static void main(String args[]){
        int[] array = {4, 2, 1, 6, 8, 0, 5, 7, 9, 3};
        String[] names = {"Guilherme", "Arthur", "Jonathan", "Jose", "Ignacio"};

        for(int i = 0; i < array.length-1; i++){
            int menor = i;
            for(int j = i + 1; j < array.length; j++){
                if(array[menor] > array[j])
                    menor = j;
            }
            int temp = array[menor];
            array[menor] = array[i];
            array[i] = temp;
        }

        for(int i = 0; i < array.length-1; i++){
            int menor = i;
        }

        for(int i = 0; i < names.length-1; i++){
            int menor = i;
            for(int j = i + 1; j < names.length; j++){
                if(names[menor].compareTo(names[j]) > 0)
                    menor = j;
            }
            String temp = names[menor];
            names[menor] = names[i];
            names[i] = temp;
        }

        for(int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }

        for(int i = 0; i < names.length; i++){
            System.out.println(names[i]);
        }
        
    }
}

