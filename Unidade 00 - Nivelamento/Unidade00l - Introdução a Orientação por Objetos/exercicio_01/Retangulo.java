import java.lang.Math;

class Retangulo{
   private int base;
   private int altura;

   public Retangulo(){
      base = 0;
      altura = 0;
   }
   
   public Retangulo(int base, int altura){
      this.base = base;
      this.altura = altura;
   }

   public double getArea(){
      return (base*altura)/2;
   }
   
   public double getPerimetro(){
      return (base+altura) * 2;
   }
   
   public double getDiagonal(){
      return Math.sqrt(base * base + altura * altura);
   }
}

