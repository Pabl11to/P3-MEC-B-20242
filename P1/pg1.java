/*Crear una clase llamada fracion con atributos correspondientes a numerador y denominador, aplicar encapsula, y los metodos set y get necesario*/
public class Fraccion {
    // Atributos privados
    private int numerador;
    private int denominador;

    // Constructor
    public Fraccion(int numerador, int denominador) {
        this.numerador = numerador;
        this.denominador = denominador;
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
