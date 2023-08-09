public class OpenAndCopy {

	public static void main(String[] Args){
		Arq.openRead("exemplo.txt");
		
		String str = "";
		
		while(Arq.hasNext() == true){
			str += Arq.readAll();
		}
		
		Arq.close();
		
		Arq.openWrite("copy.txt");
		Arq.print(str);
		Arq.close();
		
	}

}