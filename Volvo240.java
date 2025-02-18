import java.awt.*;
import java.lang.*;

public class Volvo240 extends Car{
    private final static double trimFactor = 1.25;


    public Volvo240(){
        super(4, Color.black, 100, "Volvo240");
        /*
        nrDoors = 4;
        color = Color.black;
        enginePower = 100;
        modelName = "Volvo240";
        stopEngine();
         */
    }

    @Override
    protected double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }
}

