package Invernadero;

public class Fase4_Vigilante {

    public static void main(String[] args){
        System.out.println("--- FASE 4 ---");

        //1. Objetos
        Temperatura temperatura = new Temperatura();
        Humedad humedad = new Humedad();
        Luz luz = new Luz();
        ProgramadorRiego riego = new ProgramadorRiego();

        //2. Creamos los hilos
        Thread hiloTemp = new Thread(temperatura, "sensor-Temperatura");
        Thread hiloHum = new Thread(humedad, "sensor-Humedad");
        Thread hiloLuz = new Thread(luz, "sensor-Luz");

        //3. Que muestre los estados antes de empezar
        System.out.println("");
        System.out.println(" --- ESTADOS ANTES DE ARRANCAR ---");
        imprimirEstados(hiloTemp, hiloHum, hiloLuz, riego);
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("");

        //4. Crear hilo Vigilante
        Thread vigilante = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(300);
                    System.out.printf("[VIGILANTE] Temperatura: %s | Humedad: %s | Luz: %s | Riego: %s%n",
                            hiloTemp.getState(), hiloHum.getState(), hiloLuz.getState(), riego.getState());
                }catch (InterruptedException e){
                    break;
                }
            }
        }, "vigilante");
        vigilante.setDaemon(true);

        //5. Arrancamos los 5 hilos
        vigilante.start();
        hiloTemp.start();
        hiloHum.start();
        hiloLuz.start();
        riego.start();

        //6. El hilo espera a que termine
        try {
            hiloTemp.join();
            hiloHum.join();
            hiloLuz.join();
            riego.join();
        } catch (InterruptedException e){
            System.out.println("EL hilo esta interrumpido");
            System.out.println("Mensaje de excepción: " + e.getMessage());
        }

        //7. Mostrar estados despues de empezar
        System.out.println("");
        System.out.println("--- ESTADOS DESPUES DE ARRANCAR ---");
        imprimirEstados(hiloTemp, hiloHum, hiloLuz, riego);
        System.out.println("----------------------------------------------------------------------------------------------------");
    }

    private static void imprimirEstados(Thread t1, Thread t2, Thread t3, Thread t4){

        System.out.printf("[%s]: %s | [%s]: %s | [%s]: %s | [%s]: %s%n",
                t1.getName(), t1.getState(),
                t2.getName(), t2.getState(),
                t3.getName(), t3.getState(),
                t4.getName(), t4.getState(),
        "----------------------------------------------------------------------------------------------------");

    }

}
