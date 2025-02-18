import java.awt.*;
import java.util.Stack;

// Två näst sista punkterna på uppgift 2 är man lite tvehågsen på
// Behöver TESTER, lägg till throws på loadcar och tilt ig

public class CarTransportTruck extends Car implements NonLoadable {
    private boolean flatbedDown = false;
    private final int maxCars = 15;
    public final int xRange = 5;
    public final double yRange = 0.5;
    private final double trimFactor = 1.02;

    Stack<Car> loadedCars = new Stack<>();

    protected CarTransportTruck() {
        super(2, Color.blue, 999, "Lorry");
    }

    public void tiltFlatbed (boolean tilt) {
        if (getCurrentSpeed() == 0) {
            flatbedDown = tilt;
            return;
        }
        throw new InternalError("Your speed has to be zero to tilt the flatbed");
    }

    public boolean getFlatbedDown() {
        return flatbedDown;
    }

    public int getMaxCars() {
        return maxCars;
    }

    public Stack<Car> getLoadedCars() {
        return loadedCars;
    }

    public void loadCar(Car car) {
        if (!flatbedDown){
            throw new InternalError("Flatbed has to be tilted down to load cars!");
        }
        else if (!(Math.abs(car.x - x) <= xRange && Math.abs(car.y - y) <= yRange)) {
            throw new InternalError("Car location is out of range");
        }
        else if (car instanceof NonLoadable) {
            throw new InternalError("Can't load CarTransportTruck on CarTransportTruck");
        }
        else if (loadedCars.size() >= maxCars) {
            throw new InternalError("Truck is full of cars");
        }
        else if (car.isLoaded){
            throw new InternalError("Car is already loaded on a transport truck");
        }
        else {
            car.stopEngine();
            loadedCars.push(car);
            car.isLoaded = true;
        }
    }

    public void disengageCar() {
        if (flatbedDown) {
            Car car = loadedCars.pop();
            car.isLoaded = false;
            car.x -= xRange;
            car.y -= yRange;
            return;
        }
        throw new InternalError("Can't disengage car while flatbed is up!");
    }

    @Override
    public void move() {
        super.move();
        for (Car car: loadedCars) {
            car.x = x;
            car.y = y;
        }
    }

    @Override
    protected double speedFactor() {
        return trimFactor * enginePower;
    }

    @Override
    public void startEngine() {
        if (!flatbedDown) {
            super.startEngine();
            return;
        }
        throw new InternalError("Can't start engine with flatbed tilted");
    }

    // You can increase speed by just calling gas
    // (without having used startEngine) for some reason
    // so gas has to be overridden too I think
    @Override
    public void gas(double amount) {
        if (!flatbedDown) {
            super.gas(amount);
            return;
        }
        throw new InternalError("Can't gas with flatbed tilted");
    }
}