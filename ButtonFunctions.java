public class ButtonFunctions {
    // Calls the gas method for each car once


    protected static void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (Car car : Application.cars) {
            car.gas(gasAmount);
            for (AutoRepairShop workshop : Application.workshops) {
                if (workshop.cars.contains(car)) workshop.removeCar((Car) car);
            }
        }
    }
    protected static void brake(int amount) {
        double brakeAmount = ((double) amount) / 100;
        for (Car car : Application.cars) {
            car.brake(brakeAmount);
        }
    }
    protected static void startEngine() {
        for (Car car : Application.cars) {
            car.startEngine();
        }
    }
    protected static void stopEngine() {
        for (Car car : Application.cars) {
            car.stopEngine();
        }
    }
    protected static void turnRight() {
        for (Car car : Application.cars) {
            car.turnRight();
        }
    }
    protected static void turnLeft() {
        for (Car car : Application.cars) {
            car.turnLeft();
        }
    }
    protected static void turboOff() {
        for (Car car : Application.cars) {
            if (car instanceof HasTurbo) ((HasTurbo) car).setTurboOff();
        }
    }
    protected static void turboOn() {
        for (Car car : Application.cars) {
            if (car instanceof HasTurbo) ((HasTurbo) car).setTurboOn();
        }
    }
    protected static void liftBed() {
        for (Car car : Application.cars) {
            if (car instanceof Scania) ((Scania) car).tiltFlatbed(0);
        }
    }
    protected static void lowerBed() {
        for (Car car : Application.cars) {
            if (car instanceof Scania) ((Scania) car).tiltFlatbed(70);
        }
    }
    protected static void addCar(){
        double num = Math.random();
        if(Application.cars.size()<=10) {
            if (num < 0.33)
                Application.cars.add((Car) CarFactory.createSaab95(500, 500));
            else if (num >= 0.33 && num < 0.66)
                Application.cars.add((Car) CarFactory.createVolvo240(550, 500));
            else
                Application.cars.add((Car) CarFactory.createScania(600, 500));
        }
    }
    protected static void removeCar(){
        int max = Application.cars.size()-1;
        if(max>0){
            int num = (int)Math.floor(Math.random() * (max + 1));
            if (Application.cars.get(num).isVisible) {
                Application.cars.remove(num);
            }
            else {
                removeCar();
            }
        }
        else if(max==0)
            if (Application.cars.get(max).isVisible) {
                Application.cars.removeFirst();
            }
            else {
                removeCar();
            }
    }
}
