import java.util.Scanner;

public class StringVerifica{
	
	public static void main(String[] Args){
		
		Scanner scanner = new Scanner(System.in);
		int count = 0;
		
		System.out.println("Digite uma frase: ");
		String frase = scanner.nextLine();
		
		System.out.println("Digite uma letra: ");
		String letra = scanner.nextLine();
		
		for(int i = 0; i < frase.length(); i++){
			if(frase.charAt(i) == letra.charAt(0)){
				count++;
			}
		}
		
		System.out.println(count);
		
	}
	
}