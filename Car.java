import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


abstract class Car implements Movable {
    protected int nrDoors;
    protected double enginePower;
    protected double currentSpeed;
    protected Color color;
    protected String modelName;
    int direction = 0;
    protected double x, y = 0;
    protected boolean isLoaded;
    protected String pic;
    protected boolean isVisible = true;

    protected Car(int nrDoors, Color color, int enginePower, String modelName, int x, int y, String pic) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.isLoaded = false;
        this.x = x;
        this.y = y;
        this.pic = pic;
        stopEngine();
    }

    @Override
    public void move(AutoRepairShop workshop) {
        Movement.move(this, workshop);
    }

    public String getModelName() {
        return this.modelName;
    }


    //ändrade på turn grejerna för de åkte åt fel håll annars
    public void turnLeft() {
        direction = (direction + 1) % 4;
    }

    public void turnRight() {
        direction = (direction + 3) % 4;
    }

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public double[] getPosition() {
        double[] tuple = new double[2];
        tuple[0] = x;
        tuple[1] = y;
        return tuple;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getPic() {
        return this.pic;
    }

    public void setColor(Color clr) {
        color = clr;
    }

    public void startEngine() {
        if (this.getCurrentSpeed() == 0) {
            currentSpeed = 0.1;
        }
    }

    public void stopEngine() {
        currentSpeed = 0;
    }

    abstract double speedFactor();

    public void gas(double amount) {
        if (0 <= amount && amount <= 1 && !this.isLoaded) {
            incrementSpeed(amount);
            if (!this.isVisible) {
                this.isVisible = true;
                // Anropa removeCar på nåt sätt

            }
        }
        else if(this.isLoaded) {
        throw new IllegalArgumentException("Can't gas while loaded");
    }
        else {
        throw new IllegalArgumentException("Inappropriate value");
        }
    }


    public void brake(double amount){
        if (0 <= amount && amount <= 1) {
            decrementSpeed(amount);
        }
        else {
            throw new IllegalArgumentException("Inappropriate value");
        }
    }

    protected void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    protected void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    // public static void main(String[] args) {
        /*
        Volvo240 v = new Volvo240();
        v.move();
        v.gas(1);
        System.out.println(v.getCurrentSpeed());
        v.move();
        System.out.println(v.x + " " + v.y);
        v.turnRight();
        v.move();
        System.out.println(v.x + " " + v.y);
        v.turnRight();
        v.move();
        System.out.println(v.x + " " + v.y);
        */
    // }
}
