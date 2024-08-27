//Con la misma clase crear un metodo puro o tambien llamado función pura , que me permita sumar dos fracciones y me devuelva su resultado

//Con la misma clase crear un metodo puro o tambien llamado función pura , que me permita sumar dos fracciones y me devuelva su resultado
//Metodos
public class Main {
    public static void main(String[] args) {
      
        Fraccion fraccion1 = new Fraccion(3, 4);

      
        Fraccion fraccion2 = new Fraccion(5);

      
        Fraccion fraccion3 = new Fraccion();
    }
}


public class Fraccion {
    // Atributos privados
    private int numerador;
    private int denominador;

    // Constructor con dos parámetros
    public Fraccion(int numerador, int denominador) {
        this.numerador = numerador;
        this.denominador = denominador;
    }

    // Constructor con un parámetro (numerador), denominador por defecto 1
    public Fraccion(int numerador) {
        this.numerador = numerador;
        this.denominador = 1;
    }

    // Constructor sin parámetros, valores por defecto 0/1
    public Fraccion() {
        this.numerador = 0;
        this.denominador = 1;
    }

    // Métodos get
    public int getNumerador() {
        return numerador;
    }

    public int getDenominador() {
        return denominador;
    }

    // Métodos set
    public void setNumerador(int numerador) {
        this.numerador = numerador;
    }

    public void setDenominador(int denominador) {
        this.denominador = denominador;
    }
    
    
    
}


