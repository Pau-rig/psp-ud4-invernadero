package Invernadero;

public class Fase2_Concurrente {

    public static void main(String[] args) {
        System.out.println("--- FASE 2 ---");
        System.out.println("");

        // 1. Guardamos el tiempo de inicio
        long Inicio = System.currentTimeMillis();

        // 2. Creación de los objetos
        Temperatura temperatura = new Temperatura();
        Humedad humedad = new Humedad();
        Luz luz = new Luz();
        ProgramadorRiego riego = new ProgramadorRiego();

        // 3. Ejecutamos llamado a run()
        Thread hiloTemp = new Thread(temperatura, "sensor-Temperatura");
        Thread hiloHum = new Thread(humedad, "sensor-Humedad");
        Thread hiloLuz = new Thread(luz, "sensor-Luz");

        // 3. Ejecutamos llamado a run()

        hiloTemp.start();
        hiloHum.start();
        hiloLuz.start();
        riego.start();

        // 5. Esperar a que los cuatro hilos terminen
        try {
            hiloTemp.join();
            hiloHum.join();
            hiloLuz.join();
            riego.join();
        }catch (InterruptedException e){
            System.out.println("El hilo main fue interrumpido");
            System.out.println("Mensaje de excepción" + e.getMessage());
        }

        // 6. Guardamos el tiempo final
        long Fin = System.currentTimeMillis();
        long tiempoTotal = Fin - Inicio;

        // 7. Imprimir los informes
        System.out.println("");
        System.out.println("--- INFORMES ---");
        System.out.println(temperatura.informe());
        System.out.println(humedad.informe());
        System.out.println(luz.informe());
        System.out.println(riego.informe());

        System.out.println("------------------------------------------------");
        System.out.println("Tiempo Total: " + tiempoTotal + "ms (" + (tiempoTotal / 1000.0) + " s)");
        System.out.println("------------------------------------------------");


    }
}