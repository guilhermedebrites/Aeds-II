import java.util.Scanner;
import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Arquivos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do arquivo: ");
        String nomeArquivo = scanner.nextLine();

        System.out.print("Digite uma frase: ");
        String frase = scanner.nextLine();

        try {
            RandomAccessFile raf = new RandomAccessFile(nomeArquivo, "rw");
            raf.seek(raf.length());

            raf.writeBytes(frase);

            raf.close();
            System.out.println("Frase salva no arquivo com sucesso.");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro de entrada/saída: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
