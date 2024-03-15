class Insercao {
    public static void main(String args[]){
        int[] array = {4, 2, 1, 6, 8, 0, 5, 7, 9, 3};
        String[] names = {"Guilherme", "Arthur", "Jonathan", "Jose", "Ignacio"};

        for(int i = 1; i < array.length; i++){
            int tmp = array[i];
            int j = i - 1;

            while((j >= 0) && (array[j] > tmp)){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = tmp;
        }

        for(int i = 1; i < names.length; i++){
            String temp = names[i];
            int j = i - 1;

            while((j >= 0) && (names[j].compareTo(temp) > 0)){
                names[j+1] = names[j];
                j--;
            }

            names[j+1] = temp;
        }
        
        for(int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }

        for(int i = 0; i < names.length; i++){
            System.out.println(names[i]);
        }
    }    
}
