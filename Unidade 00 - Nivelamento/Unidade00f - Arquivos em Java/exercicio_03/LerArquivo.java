import java.util.Scanner;
import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LerArquivo {
	
	public static void main(String[] Args)throws FileNotFoundException, IOException {
		Scanner scanner = new Scanner(System.in);
		String str = "";
		
		System.out.println("Digite o nome do arquivo: ");
		String name = scanner.nextLine();
		
		RandomAccessFile raf = new RandomAccessFile(name, "r");
		raf.seek(0);
		str += (char) raf.readByte();
		
		System.out.println(str);
        raf.close();
		scanner.close();
	}
}