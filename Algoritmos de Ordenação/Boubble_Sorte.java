class Boubble_Sorte{
    public static void main(String args[]){
        int[] array = {4, 2, 1, 6, 8, 0, 5, 7, 9, 3};

        for(int j = 0; j < array.length-1; j--){
            for(int i = 0; i < array.length-1; i++){
                if(array[i] > array[i+1]){
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                }
            }
        }

        for(int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
    }    
}