import java.awt.*;

public class Saab95 extends Car implements HasTurbo{
    private boolean turboOn = false;


    public Saab95() {
        super(2, Color.red, 125, "Saab95", 0, 0, "pics/Saab95.jpg");
    }
    public Saab95(int x, int y){
        super(2, Color.red, 125, "Saab95", x, y, "pics/Saab95.jpg");
        /*
        nrDoors = 2;
        color = Color.red;
        enginePower = 125;
	    turboOn = false;
        modelName = "Saab95";
        stopEngine();
         */
    }

    public void setTurboOn(){
        turboOn = true;
    }

    public void setTurboOff(){
        turboOn = false;
    }

    @Override
    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 2;
        return enginePower * 0.01 * turbo;
    }
}
