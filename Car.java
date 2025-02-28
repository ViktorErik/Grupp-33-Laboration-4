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
        this.pic=pic;
        stopEngine();
    }

    /*
    private void borderCollide(double x, double y) {
        collide(x, y);
        if (x < 0) this.x = 1;
        if (x + 100 > CarView.X) this.x = CarView.X - 101;
        if (y < 0) this.y = 1;
        if (y + 60 > CarView.paneY) this.y = CarView.paneY - 61;
    }

    private void workshopCollide(double x, double y) {
        if (this.isVisible && this instanceof Volvo240) {
            this.isVisible = false;
        }

        if (direction == 0) this.y = DrawPanel.volvoWorkshopPoint.getY() - 60;
        if (direction == 1) this.x = DrawPanel.volvoWorkshopPoint.getX() - 100;
        if (direction == 2) this.y = DrawPanel.volvoWorkshopPoint.getY() + 96;
        if (direction == 3) this.x = DrawPanel.volvoWorkshopPoint.getX() + 101;
        collide(x, y);

    }

    private void collide(double x, double y) {
        direction = (direction + 2) % 4; // Change direction
        stopEngine();
        startEngine();
    }

    public void move() {
        switch (direction) {
            case 0:
                y += getCurrentSpeed();
                break;
            case 1:
                x += getCurrentSpeed();
                break;
            case 2:
                y -= getCurrentSpeed();
                break;
            case 3:
                x -= getCurrentSpeed();
                break;
        }
        if (x<0 || y<0 || x+100 > CarView.X || y+60>CarView.paneY) {
            borderCollide(x, y);
        }
        if (
                this.x <= DrawPanel.volvoWorkshopPoint.getX() + 101 && DrawPanel.volvoWorkshopPoint.getX() <= this.x + 100 &&
                this.y <= DrawPanel.volvoWorkshopPoint.getY() + 96 && DrawPanel.volvoWorkshopPoint.getY() <= this.y + 60) {
            workshopCollide(x, y);
        }
    }

     */

//ändrade på turn grejerna för de åkte åt fel håll annars
    public void turnLeft() {
        direction = (direction + 1) % 4;
    }

    public void turnRight() {
        direction = (direction + 3) % 4;
    }

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor() {
        return color;
    }

    public double[] getPosition(){
        double[] tuple = new double[2];
        tuple[0]=x;
        tuple[1]=y;
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

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    abstract double speedFactor();

    public void gas(double amount) {
        if (0 <= amount && amount <= 1 && !this.isLoaded) {
            incrementSpeed(amount);
            if (!this.isVisible && this instanceof Volvo240) {
                workshopCollide(x, y);
                this.isVisible = true;
            }
        }
        else if(this.isLoaded){
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
