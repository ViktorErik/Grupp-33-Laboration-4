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
    ArrayList<AutoRepairShop> workshops = new ArrayList<>();

    public CarController(){
        frame = new CarView("CarSim");
        frame.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gas(frame.gasAmount);
            }
        });
        frame.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { turboOn();}
        });

        frame.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                brake(frame.gasAmount);
            }
        });
        frame.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turboOff();
            }
        });

        frame.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startEngine();
            }
        });
        frame.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopEngine();
            }
        });
        frame.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                liftBed();
            }
        });
        frame.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lowerBed();
            }
        });

        frame.rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnRight();
            }
        });
        frame.leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnLeft();
            }
        });
        frame.addCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCar();
            }
        });
        frame.removeCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCar();
            }
        });
    }

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController<Car> cc = new CarController<Car>();

        cc.cars.add(CarFactory.createSaab95(100, 0));
        cc.cars.add(CarFactory.createVolvo240(200,0));
        cc.cars.add(CarFactory.createScania(300, 0));
        // cc.workshops.add(new AutoRepairShop<Volvo240>(300, 300, 2, "pics/VolvoBrand.jpg", "Volvo240"));
        // cc.workshops.add(new AutoRepairShop<Car>(700, 300, 2, "pics/SaabBrand.jpg", "Car"));
        cc.workshops.add(RepairShopFactory.createSaabWorkshop(350, 350, 2));
        cc.workshops.add(RepairShopFactory.createScaniaWorkshop(550, 200, 1));
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (ACar car : cars) {
                for (AutoRepairShop workshop : workshops) {
                    car.move(workshop);
                }
                // stod innan car.getPosition().getX() men aja

            }
            frame.drawPanel.updateInfo(cars, workshops);

            // repaint() calls the paintComponent method of the panel
            frame.drawPanel.repaint();
        }
    }

    // Calls the gas method for each car once
    protected void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (ACar car : cars) {
            car.gas(gasAmount);
            for (AutoRepairShop workshop : workshops) {
                if (workshop.cars.contains(car)) workshop.removeCar((Car) car);
            }
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
    protected void addCar(){
        double num = Math.random();
        if(cars.size()<=10) {
            if (num < 1)
                this.cars.add((ACar) CarFactory.createSaab95(500, 500));
            else if (num >= 0.33 && num < 0.66)
                this.cars.add((ACar) CarFactory.createVolvo240(550, 500));
            else
                this.cars.add((ACar) CarFactory.createScania(600, 500));
        }
    }
    protected void removeCar(){
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
