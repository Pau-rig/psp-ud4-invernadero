package Invernadero;

public class Fase1_Secuencial {

    public static void main(String[] args) {
        System.out.println("--- FASE 1 ---");
        System.out.println("");

        // 1. Guardamos el tiempo de inicio
        long Inicio = System.currentTimeMillis();

        // 2. Creación de los objetos
        Temperatura temperatura = new Temperatura();
        Humedad humedad = new Humedad();
        Luz luz = new Luz();
        ProgramadorRiego riego = new ProgramadorRiego();

        // 3. Ejecutamos llamado a run()

        temperatura.run();
        humedad.run();
        luz.run();
        riego.run();

        // 4. Guardamos el tiempo final
        long Fin = System.currentTimeMillis();
        long tiempoTotal = Fin - Inicio;

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
