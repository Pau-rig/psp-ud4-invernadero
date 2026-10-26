package Invernadero;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ProgramadorRiego extends Thread{

    private int ciclosCompletados = 0;
    private double litrosTotales = 0.0;


    public ProgramadorRiego(){
        super("riego");
    }

    @Override
    public void run(){
        DateTimeFormatter Hora = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

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

            String horaActual = LocalTime.now().format(Hora);
            System.out.printf("[%s] [%s] Válvula de riego %s | Litros acumulados: %.1f L%n",
                    horaActual, getName(), estadoValvula, litrosTotales);

            try {
                Thread.sleep(700);
            } catch (InterruptedException e){
                return;
            }

        }

    }

    public String informe(){
        return String.format("ProgramadorRiego -> Ciclos: %d | Litros regados: %.1f L",
                ciclosCompletados, litrosTotales);
    }

}
