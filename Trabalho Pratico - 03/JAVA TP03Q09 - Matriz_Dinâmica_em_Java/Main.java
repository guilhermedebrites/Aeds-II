class Celula{
	int elemento;
	Celula prox;
	Celula ant;
	Celula sup;
	Celula inf;


	public Celula(int elemento){
		this.elemento = elemento;
		this.prox = null;
		this.ant = null;
		this.sup = null;
		this.inf = null;
	}

	
	public Celula(){
		this.elemento = 0;
		this.prox = null;
		this.ant = null;
		this.sup = null;		
	}
}


class Matriz{
	private Celula inicio;
	

	public Matriz(int ln, int col){
		Celula tmp = null;
		for(int i = 1; i <= col; i++){
			Celula celula = new Celula();
			if(isVazia()){
				tmp = celula;
				this.inicio = celula;
			}
			else{
				tmp = this.inicio;
				while(tmp.inf != null){
					tmp = tmp.inf;
				}	
				tmp.inf = celula;
				celula.sup = tmp;
				tmp = tmp.inf;
			}
			for(int j = 1; j < ln; j++){
				Celula nova = new Celula();
				tmp.prox = nova;
				nova.ant = tmp;
				tmp = nova;
			}
			if(i >= 2){
				Celula cima = this.inicio;
				Celula baixo = this.inicio;
				while(baixo.inf != null) baixo = baixo.inf;
				while(cima.inf != baixo) cima = cima.inf;
				while(cima.prox != null && baixo.prox != null){
					cima.prox.inf = baixo.prox;
					baixo.prox.sup = cima.prox;
					cima = cima.prox;
					baixo = baixo.prox;
				}
				cima = baixo = null;
			}
			tmp = null;
		}
	}

	
	public void inserir(int elemento,int col,int ln) throws Exception{
		int i = 1;
		int j = 1;
		Celula tmp = this.inicio;
		while(j < col){
			tmp = tmp.inf;
			j++;
		}
		while(i < ln){
			tmp = tmp.prox;
			i++;
		}
		tmp.elemento = elemento;
	}

	
	public boolean isVazia(){
		return this.inicio == null ? true : false;
	}

	
	public int lengthLn(){
		int tam = 1;
		for(Celula i = this.inicio; i != null; i = i.prox, tam++);
		return tam;
	}

	
	public int lengthCol(){
		int tam = 1;
		for(Celula i = this.inicio; i != null; i = i.inf, tam++);
		return tam;
	}

	
	public void mostrarDiagonalPrincipal(){
		Celula j = this.inicio;
		while(j != null){
			MyIO.print(j.elemento + " ");
			if(j.prox != null){
				j = j.prox.inf;
			}
			else{
				j = null;
			}
		}
				MyIO.println("");
	}

		
	public void mostrarDiagonalSecundaria(){
		Celula j = this.inicio;
		while(j.prox != null) j = j.prox;
		while(j != null){
			MyIO.print(j.elemento + " ");
			if(j.ant != null){
				j = j.ant.inf;
			}
			else{
				j = null;
			}
		}
			MyIO.println("");
	}

	
	public void mostrar(){
		Celula indice = this.inicio;
		while(indice != null){
			for(Celula i = indice; i != null; i = i.prox){
				MyIO.print(i.elemento + " ");
			}
			MyIO.println("");
			indice = indice.inf;
		}
	}

	
	public Matriz somaMatriz(Matriz matriz) throws Exception{
		if(matriz.lengthCol() != lengthCol() || matriz.lengthLn() != lengthLn()){
			throw new Exception("erro");
		}
		Matriz nova = new Matriz(lengthLn()-1,lengthCol()-1);
		Celula i = this.inicio;
		Celula j = matriz.inicio;
		Celula l = null;
		Celula lne = null;
		for(int col = 1; col <=  lengthCol()-1; col++){
			l = i;
			lne = j;
			for(int ln = 1; ln <= lengthLn()-1; ln++){
				nova.inserir((l.elemento+lne.elemento),col,ln);
				l = l.prox;
				lne = lne.prox;
			}
			i = i.inf;
			j = j.inf;
		}
		return nova;
	}

	
	public Matriz multiplicacao(Matriz matriz1, Matriz matriz2) throws Exception{
		int soma = 0;
		Matriz matriz3 = null;
		if(matriz1.lengthCol() == matriz2.lengthLn()){
			matriz3 = new Matriz(matriz1.lengthLn()-1,matriz2.lengthCol()-1);
			Celula ln = matriz1.inicio;
			Celula col = matriz2.inicio;
			Celula lne = null;
			Celula colu = null;
			for(int linha = 1; linha <= lengthLn()-1; linha++){
				for(int coluna = 1; coluna <= lengthCol()-1; coluna++){
					lne = ln;
					colu = col;
					while(lne != null && colu != null){
						soma += (lne.elemento*colu.elemento);
						lne = lne.prox;
						colu = colu.inf;
					}
					matriz3.inserir(soma,coluna,linha);
					ln = ln.inf;
					soma = 0;
				}
				soma = 0;
				ln = this.inicio;
				col = col.prox;
			}
		}
		else throw new Exception("erro");
			return matriz3;
	}
}

public class Main{
	public static void main(String[] args) throws Exception{
		MyIO.setCharset("UTF-8");
		int i = 0;
		Matriz m1 = null;
		Matriz m2 = null;
		Matriz m3 = null;
		int ocorrencia = MyIO.readInt();
		while(i < ocorrencia){
			int ln =  MyIO.readInt();
			int col = MyIO.readInt();
			m1 = new Matriz(ln,col);
			for(int coluna = 1; coluna <= col; coluna++){
				for(int linha = 1; linha <= ln; linha++){
					int x = MyIO.readInt();
					m1.inserir(x,coluna,linha);
				}
			}
			int lne = MyIO.readInt();
			int colu = MyIO.readInt();
			m2 = new Matriz(lne,colu);
			for(int coluna = 1; coluna <= colu; coluna++){
				for(int linha = 1; linha <= lne; linha++){
					int x = MyIO.readInt();
					m2.inserir(x,coluna,linha);
				}
			}
			m1.mostrarDiagonalPrincipal();
			m1.mostrarDiagonalSecundaria();
			m3 = m1.somaMatriz(m2);
			m3.mostrar();
			Matriz m4 = m1.multiplicacao(m1,m2);
			m4.mostrar();
			i++;
		}
	}
}