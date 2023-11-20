import java.util.Scanner;

class Matrizes{
    public static void main(String[] args){
        try{
            Scanner sc = new Scanner(System.in);
            int numeroDeCasos = sc.nextInt();

            for(int i = 0; i < numeroDeCasos; i++){
                int linha = sc.nextInt();
                int coluna = sc.nextInt();
                Matriz matriz1 = new Matriz(linha, coluna);
                matriz1.preencherMatriz(sc, linha, coluna);
                matriz1.mostrarDiagonalPrincipal();
                matriz1.mostrarDiagonalSecundaria();


                linha = sc.nextInt();
                coluna = sc.nextInt();
                Matriz matriz2 = new Matriz(linha, coluna);
                matriz2.preencherMatriz(sc, linha, coluna);

                Matriz matriz3 = matriz1.soma(matriz2);
                Matriz matriz4 = matriz1.multiplicacao(matriz2);

                matriz3.mostrarMatriz();
                matriz4.mostrarMatriz();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

class Matriz {
    private Celula inicio;
    private int linha, coluna;

    public Matriz() {
        this(3, 3);
    }

    public Matriz(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
        
        this.alocarCelulas();
    }

    public void preencherMatriz(Scanner sc,int linha, int coluna){
        Celula aux = this.inicio;
        Celula auxLinha = this.inicio;

        for(int i = 0; i < linha; i++){
            for(int j = 0; j < coluna; j++){
                aux.elemento = sc.nextInt();
                aux = aux.dir;
            }
            auxLinha = auxLinha.baixo;
            aux = auxLinha;
        }
    }

    private void alocarCelulas(){
        Celula aux = inicio = new Celula();
        Celula auxLinha = inicio;

        for(int i = 0; i < linha; i++){
            for(int j = 0; j < coluna - 1; j++){
                aux.dir = new Celula();
                aux.dir.esq = aux;
                aux = aux.dir;
            }
            auxLinha.baixo = new Celula();
            auxLinha.baixo.cima = auxLinha;
            auxLinha = aux = auxLinha.baixo;
        }
    }

    public Matriz soma (Matriz m) {
      Matriz resp = null;

      if(this.linha == m.linha && this.coluna == m.coluna){
        resp = new Matriz(this.linha, this.coluna);
        
        Celula auxThis = this.inicio;
        Celula auxM = m.inicio;
        Celula auxC = resp.inicio;

        for(int i = 0; i < this.linha; i++){
            for(int j = 0; j < this.coluna; j++){
                auxC.elemento = auxThis.elemento + auxM.elemento;

                auxThis = auxThis.dir;
                auxM = auxM.dir;
                auxC = auxC.dir;
            }
            auxThis = this.inicio.baixo;
            auxM = m.inicio.baixo;
            auxC = resp.inicio.baixo;
        }
      }

      return resp;
    }

    public Matriz multiplicacao(Matriz m) {
        Matriz resp = null;

        if (this.coluna == m.linha) {
            resp = new Matriz(this.linha, m.coluna);

            Celula auxThisLinha = this.inicio;
            Celula auxThisColuna = this.inicio;
            Celula auxMLinha = m.inicio;
            Celula auxMColuna = m.inicio;
            Celula auxC = resp.inicio;
            Celula auxCColuna = resp.inicio;

            for (int i = 0; i < this.linha; i++) {
                for (int j = 0; j < m.coluna; j++) {
                    int result = 0;

                    for (int k = 0; k < this.coluna; k++) {
                        result += auxThisLinha.elemento * auxMLinha.elemento;
                        if(auxMLinha.baixo != null){
                            auxThisLinha = auxThisLinha.dir;
                            auxMLinha = auxMLinha.baixo;
                        }
                    }

                    auxC.elemento = result;

                    if(auxC.dir != null){
                        auxC = auxC.dir;
                    }

                    auxThisLinha = auxThisColuna;
                    auxMLinha = auxMColuna.dir;
                }

                if(auxThisColuna.baixo != null){
                    auxThisLinha = auxThisColuna.baixo;
                    auxThisColuna = auxThisColuna.baixo;
                }
                if(auxCColuna.baixo != null){
                    auxC = auxCColuna.baixo;
                    auxCColuna = auxCColuna.baixo;
                }
                
                auxMLinha = auxMColuna;
            }
        }

        return resp;
    }

    public boolean isQuadrada(){
        if(this.linha == this.coluna)
            return true;
        return false;
    }

    public void mostrarDiagonalPrincipal() {
        if (isQuadrada()) {
            Celula aux = this.inicio;

            while (aux != null) {
                System.out.print(aux.elemento + " ");
                aux = aux.baixo != null ? aux.baixo.dir : null;
            }

            System.out.println();
        }
    }

  

    public void mostrarDiagonalSecundaria() {
        if (isQuadrada()) {
            Celula aux = this.inicio;

            while (aux.dir != null) {
                aux = aux.dir;
            }

            while (aux != null) {
                System.out.print(aux.elemento + " ");
                aux = aux.esq != null ? aux.esq.baixo : null;
            }

            System.out.println();
        }
    }


    public void mostrarMatriz() {
        Celula auxLinha = this.inicio;

        for (int i = 0; i < this.linha; i++) {
            Celula auxColuna = auxLinha;

            for (int j = 0; j < this.coluna; j++) {
                System.out.print(auxColuna.elemento + " ");
                auxColuna = auxColuna.dir;
            }

            System.out.println();
            auxLinha = auxLinha.baixo;
        }
    }

}

class Celula {
    public int elemento;
    public Celula esq, cima, dir, baixo;

    public Celula(){
        this(0);
    }

    public Celula(int elemento){
        this.elemento = elemento;
        this.esq = this.cima = this.dir = this.baixo = null;
    }

    public Celula(int elemento, Celula esq, Celula cima, Celula dir, Celula baixo){
        this.elemento = elemento;
        this.esq = esq;
        this.cima = cima;
        this.dir = dir;
        this.baixo = baixo;
    }
}