package Invernadero;

public class Fase3_Experimentos {

    public static void main(String[] args) {
        System.out.println("--- FASE 3 ---");
        System.out.println("");

        // EXPERIMENTO A
        System.out.println("------------------------------");
        System.out.println("EXPERIMENTO A");
        System.out.println("------------------------------");

        //1. Objeto y Hilo
        Temperatura tempExpA = new Temperatura();
        Thread hiloTempExpA = new Thread(tempExpA, "sensor-Temperatura");

        // 2. Comienza el hilo
        hiloTempExpA.start();

        //3. Interrumpe el hilo
        System.out.println("--- INFORMES SIN ESPERAR ---");
        System.out.println(tempExpA.informe());

        //4. Esperar el hilo
        try {
            hiloTempExpA.join();
        }catch (InterruptedException e){
            System.out.println("INTERRUMPIDO");
        }

        //5. Informe despúes de eperar el hilo
        System.out.println("");
        System.out.println("--- INFORMES ESPERANDO ---");
        System.out.println(tempExpA.informe());
        System.out.println("------------------------------");

        // EXPERIMENTO B
        System.out.println("");
        System.out.println("------------------------------");
        System.out.println("EXPERIMENTO B");
        System.out.println("------------------------------");

        //1. Iniciamos la medicion del tiempo
        long InicioExpB = System.currentTimeMillis();

        //2. Objeto
        ProgramadorRiego riegoExpB = new ProgramadorRiego();

        //3. Invocamos run
        riegoExpB.run();

        //4. Terminamos la medicion del Tiempo
        long FinExpB = System.currentTimeMillis();
        long tiempoTotal = FinExpB - InicioExpB;
        System.out.println("");
        System.out.println("------------------------------------------------");
        System.out.println("Tiempo Total: " + tiempoTotal + "ms (" + (tiempoTotal / 1000.0) + " s)");
        System.out.println("Estado de riego: " + riegoExpB.getState());
        System.out.println("------------------------------------------------");

        // EXPERIMENTO C
        System.out.println("");
        System.out.println("------------------------------");
        System.out.println("EXPERIMENTO C");
        System.out.println("------------------------------");

        // 1. objetos
        ProgramadorRiego riegoExpC = new ProgramadorRiego();

        // 2. comienza el Star
        riegoExpC.start();

        // 3. Complete su ciclo

        try {
            riegoExpC.join();
        }catch (InterruptedException e){
            System.out.println("Interrumido");
            System.out.println("Mensaje de excepción: " + e.getMessage());
        }

        System.out.println("Estado: " + riegoExpC.getState());

        //4. Segundo start
        System.out.println("");
        System.out.println("Segundo start");

        //5. Resultado
        try {
            riegoExpC.start();
        } catch (IllegalThreadStateException e){
            System.out.println("Interrumpido");
            System.out.println("Mensaje de excepción: " + e.getMessage());
        }

    }
}
