import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController <ACar extends Car> {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 20;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<ACar> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController<Car> cc = new CarController<Car>();

        cc.cars.add(CarFactory.createSaab95(100, 0));
        cc.cars.add(CarFactory.createVolvo240(200,0));
        cc.cars.add(CarFactory.createScania(300, 0));


        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);
        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (ACar car : cars) {
                car.move();
                // stod innan car.getPosition().getX() men aja
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                frame.drawPanel.updateCars(cars);

                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    protected void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (ACar car : cars) {
            car.gas(gasAmount);
        }
    }
    protected void brake(int amount) {
        double brakeAmount = ((double) amount) / 100;
        for (ACar car : cars) {
            car.brake(brakeAmount);
        }
    }
    protected void startEngine() {
        for (ACar car : cars) {
            car.startEngine();
        }
    }
    protected void stopEngine() {
        for (ACar car : cars) {
            car.stopEngine();
        }
    }
    protected void turnRight() {
        for (ACar car : cars) {
            car.turnRight();
        }
    }
    protected void turnLeft() {
        for (ACar car : cars) {
            car.turnLeft();
        }
    }
    protected void turboOff() {
        for (ACar car : cars) {
            if (car instanceof Saab95) ((Saab95) car).setTurboOff();
        }
    }
    protected void turboOn() {
        for (ACar car : cars) {
            if (car instanceof Saab95) ((Saab95) car).setTurboOn();
        }
    }
    protected void liftBed() {
        for (ACar car : cars) {
            if (car instanceof Scania) ((Scania) car).tiltFlatbed(0);
        }
    }
    protected void lowerBed() {
        for (ACar car : cars) {
            if (car instanceof Scania) ((Scania) car).tiltFlatbed(70);
        }
    }
}
