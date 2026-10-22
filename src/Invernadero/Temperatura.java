package Invernadero;

import java.util.concurrent.ThreadLocalRandom;

public class Temperatura extends Sensor{

    public Temperatura() {
        super("Temperatura", "ºC", 400 , 5, 18.0, 30.0);
    }

    @Override
    public double medir() {
        return ThreadLocalRandom.current().nextDouble(15.0, 30.0);
    }
}
