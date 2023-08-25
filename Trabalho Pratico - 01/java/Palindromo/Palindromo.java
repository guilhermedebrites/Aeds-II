public class Palindromo{
	public static void main(String[] args){
		String userInput = MyIO.readLine();
		while(!userInput.equals("FIM")){
			if(verifyPalindrome(userInput))
			       	System.out.println("SIM"); 
			else 
			       	System.out.println("NAO");
			userInput = MyIO.readLine();
		}
	}

	static boolean verifyPalindrome(String palavra){
		int i, j;
		for(i=0,j=(palavra.length()-1); i<(palavra.length()/2);i++,j--){
			if(palavra.charAt(i) != palavra.charAt(j)){
				return false;
			}
		}
		return true;	
	}
}
