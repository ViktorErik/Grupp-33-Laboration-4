import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Application {
    CarView frame;
    CarController<Car> cc;
    static ArrayList<Car> cars;
    static ArrayList<AutoRepairShop> workshops;
    protected static final int X = 1000;
    protected static final int Y = 800;
    protected static final int paneY = Y - 240;
    private final int delay = 20;
    private Timer timer = new Timer(delay, new TimerListener());

    Application(){
        frame = new CarView("CarSim");
        cc = new CarController<>();
        cars = new ArrayList<>();
        workshops = new ArrayList<>();
        frame.add(cc.controlPanel);
        frame.add(cc.gasPanel);
        frame.add(cc.turnPanel);
        frame.add(cc.startButton);
        frame.add(cc.stopButton);
    }
    public static void main(String[] args) {
        Application app = new Application();
        app.cc.cars.add(CarFactory.createVolvo240(200,0));
        app.cc.cars.add(CarFactory.createScania(300, 0));
        app.cc.cars.add(CarFactory.createSaab95(100, 0));
        app.cc.workshops.add(RepairShopFactory.createSaabWorkshop(350, 350, 2));
        app.cc.workshops.add(RepairShopFactory.createScaniaWorkshop(550, 200, 1));
        app.timer.start();
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                for (AutoRepairShop workshop : workshops) {
                    car.move(workshop);
                }
                // stod innan car.getPosition().getX() men aja

            }
            System.out.println("HEJEJEJ");
            frame.drawPanel.updateInfo(cars, workshops);

            // repaint() calls the paintComponent method of the panel
            frame.drawPanel.repaint();
        }
    }

    // Calls the gas method for each car once
    protected static void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gasAmount);
            for (AutoRepairShop workshop : workshops) {
                if (workshop.cars.contains(car)) workshop.removeCar((Car) car);
            }
        }
    }
    protected static void brake(int amount) {
        double brakeAmount = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brakeAmount);
        }
    }
    protected static void startEngine() {
        for (Car car : cars) {
            car.startEngine();
        }
    }
    protected static void stopEngine() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }
    protected static void turnRight() {
        for (Car car : cars) {
            car.turnRight();
        }
    }
    protected static void turnLeft() {
        for (Car car : cars) {
            car.turnLeft();
        }
    }
    protected static void turboOff() {
        for (Car car : cars) {
            if (car instanceof Saab95) ((Saab95) car).setTurboOff();
        }
    }
    protected static void turboOn() {
        for (Car car : cars) {
            if (car instanceof Saab95) ((Saab95) car).setTurboOn();
        }
    }
    protected static void liftBed() {
        for (Car car : cars) {
            if (car instanceof Scania) ((Scania) car).tiltFlatbed(0);
        }
    }
    protected static void lowerBed() {
        for (Car car : cars) {
            if (car instanceof Scania) ((Scania) car).tiltFlatbed(70);
        }
    }
    protected static void addCar(){
        double num = Math.random();
        if(cars.size()<=10) {
            if (num < 1)
                cars.add((Car) CarFactory.createSaab95(500, 500));
            else if (num >= 0.33 && num < 0.66)
                cars.add((Car) CarFactory.createVolvo240(550, 500));
            else
                cars.add((Car) CarFactory.createScania(600, 500));
        }
    }
    protected static void removeCar(){
        int max = cars.size()-1;
        if (max <= 0) return;
        if(max>0){
            int num = (int)Math.floor(Math.random() * (max + 1));
            if (cars.get(num).isVisible) {
                cars.remove(num);
            }
            else {
                removeCar();
            }
        }
        else if(max==0)
            if (cars.get(max).isVisible) {
                cars.removeFirst();
            }
            else {
                removeCar();
            }
    }
}
