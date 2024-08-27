//Metodos
public class Main {
    public static void main(String[] args) {
      
        Fraccion fraccion1 = new Fraccion(3, 4);

      
        Fraccion fraccion2 = new Fraccion(5);

      
        Fraccion fraccion3 = new Fraccion();
    }
}


public class Fraccion {

    private int numerador;
    private int denominador;

  
    public Fraccion(int numerador, int denominador) {
        this.numerador = numerador;
        this.denominador = denominador;
    }

    
    public Fraccion(int numerador) {
        this.numerador = numerador;
        this.denominador = 1;
    }


    public Fraccion() {
        this.numerador = 0;
        this.denominador = 1;
    }

 
    public int getNumerador() {
        return numerador;
    }

    public int getDenominador() {
        return denominador;
    }

  
    public void setNumerador(int numerador) {
        this.numerador = numerador;
    }

    public void setDenominador(int denominador) {
        this.denominador = denominador;
    }
}
