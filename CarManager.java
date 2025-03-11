import java.util.ArrayList;

public class CarManager {

    /* Ändringar:
    * Lägg cars o workshops i denna klass
    * updateCars funktion som uppdaterar data som Application kan använda
    *
     */
    private static ArrayList<Car> cars = new ArrayList<>();

    static ArrayList<Car> updateCars() {
        return cars;
    }

    // Calls the gas method for each car once
    protected static void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gasAmount);
            for (AutoRepairShop workshop : Application.workshops) {
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
            if (car instanceof HasTurbo) ((HasTurbo) car).setTurboOff();
        }
    }
    protected static void turboOn() {
        for (Car car : cars) {
            if (car instanceof HasTurbo) ((HasTurbo) car).setTurboOn();

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
            if (num < 0.33)
                cars.add((Car) CarFactory.createSaab95(500, 500));
            else if (num >= 0.33 && num < 0.66)
                cars.add((Car) CarFactory.createVolvo240(550, 500));
            else
                cars.add((Car) CarFactory.createScania(600, 500));
        }

    }
    protected static void removeCar(){
        int max = cars.size()-1;
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
