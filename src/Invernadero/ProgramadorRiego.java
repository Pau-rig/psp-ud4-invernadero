package Invernadero;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ProgramadorRiego extends Thread{

    ///Privados para guardar sus propias estadísticas
    private int ciclosCompletados = 0;
    private double litrosTotales = 0.0;

    //Constructor
    public ProgramadorRiego(){
        super("riego");
    }

    //Métodos
    @Override
    public void run(){
        //Para mostrar la hora en milisegundos en el log
        DateTimeFormatter Hora = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

        //Bucle para realizar el número de lecturas
        for (int i = 1; i <= 5; i++) {
            ciclosCompletados++;
            String estadoValvula;

            //ciclos impares: ABIERTA | Ciclos pares: Cerrada
            if (i % 2 != 0) {
                estadoValvula = "ABIERTA";
                litrosTotales += 2.5;
            } else {
                estadoValvula = "CERRADA";
            }

            //Muestra el log hora
            String horaActual = LocalTime.now().format(Hora);
            System.out.printf("[%s] [%s] Válvula de riego %s | Litros acumulados: %.1f L%n",
                    horaActual, getName(), estadoValvula, litrosTotales);

            //Pausamos el hilo para simular el intervalo de medicion
            try {
                Thread.sleep(700);
            } catch (InterruptedException e){
                return;
            }

        }

    }

    //Método informe
    public String informe(){
        //El texto en el formato deseado
        return String.format("ProgramadorRiego | Ciclos: %d | Litros regados: %.1f L",
                ciclosCompletados, litrosTotales);
    }

}
