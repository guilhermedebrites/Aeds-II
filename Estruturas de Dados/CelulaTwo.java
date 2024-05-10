public class CelulaTwo {
    public int elemento;
    public CelulaTwo prox;

    public CelulaTwo(){
        this(0);
    }

    public CelulaTwo(int elemento){
        this.elemento = elemento;
        this.prox = null;
    }
}
