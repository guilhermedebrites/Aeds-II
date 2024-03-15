import java.io.RandomAccessFile;

class Principal {
    public static void main(String args[]){
        try {
            String fileName = "Questao08.txt";
            RandomAccessFile file = new RandomAccessFile(fileName, "rw");
            Arquivo classArquivo = new Arquivo();

            int amount = MyIO.readInt();
            classArquivo.writeNumbers(amount, file);
            classArquivo.readNumbers(amount, fileName);

            file.close();

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Erro ao criar o arquivo!");
        }
        
    }
}

class Arquivo{

    public void writeNumbers(int count, RandomAccessFile file){
        try {
            double number;
    
            for(int i = 0; i < count; i++){
                number = MyIO.readDouble();
                file.writeDouble(number);
            }

            file.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Erro ao escrever no arquivo!");
        }
    }    

    public void readNumbers(int count, String fileName){

        try{
            RandomAccessFile file = new RandomAccessFile(fileName, "rw");
            long fileLength = file.length();
            double number;

            for(long i = fileLength - 8; i >= 0; i-= 8){
                file.seek(i);
                number = file.readDouble();

                if(number == (int) number)
                    System.out.println((int)number);
                else
                    System.out.println(number);
            }
            
            file.close();

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Erro ao ler o arquivo!");
        }

    }
}