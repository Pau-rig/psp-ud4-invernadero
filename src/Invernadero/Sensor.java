package Invernadero;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class Sensor implements Runnable{
//Atributos He utilizado la IA para tener ejemplos de como se realiza
    protected String nombre;
    protected String unidad;
    protected int periodo;
    protected int numLectura;
    protected double umMin;
    protected double umMax;

//Privados para guardar sus propias estadísticas
    private double sumaValores = 0;
    private double min = Double.MAX_VALUE;
    private double max = -Double.MAX_VALUE;
    private int conAlert = 0;
    private int lecRealizada = 0;

//Constructor
    public Sensor(String nombre, String unidad, int periodo, int numLectura, double umMin, double umMax) {
        this.nombre = nombre;
        this.unidad = unidad;
        this.periodo = periodo;
        this.numLectura = numLectura;
        this.umMin = umMin;
        this.umMax = umMax;
    }

//Metodos
    public abstract double medir();

@Override
    public void run() {
    //Para mostrar la hora en milisegundos en el log
    DateTimeFormatter Hora = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    //Como utilizamos Runnable tenemos que usar Thread.currentThread() para los hilos que ejecutaremos
    String nomHilo = Thread.currentThread().getName();

        //Bucle para realizar el número de lecturas
        for (int i = 0; i < numLectura; i++) {
            //Tomamos la lectura
            double valor = medir();
            lecRealizada++;
            sumaValores += valor;

            //Actualizar mínimo y máximo
            if (valor < min) min = valor;
            if (valor > max) max = valor;

            //Alerta
            boolean Alerta = (valor < umMin || valor > umMax);
            String texAlerta = "";
            if (Alerta) {
                conAlert++;
                texAlerta = " ⚠ ALERTA ⚠ ";
            }

            //Muestra el log hora
            String horaActual = LocalTime.now().format(Hora);
            System.out.printf("[%s] [%s] %s: %.1f%s%s%n",
                    horaActual, nomHilo, nombre, valor, unidad, texAlerta);

            //Pausamos el hilo para simular el intervalo de medicion
            try {
                Thread.sleep(periodo);
            }catch (InterruptedException e){
                return;
            }
        }
    }

    //Método informe
    public String informe() {
    double media = (lecRealizada > 0 ) ? (sumaValores / lecRealizada) : 0.0;
        //El texto en el formato deseado
        return String.format("%s | Lecturas: %d | Media: %.2f%s | Mín: %.1f%s | Máx: %.1f%s | Alertas: %d",
                nombre, lecRealizada, media, unidad, min, unidad, max, unidad, conAlert);

    }

}
