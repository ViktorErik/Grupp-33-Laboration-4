import java.util.ArrayList;

public class AutoRepairShop<T extends Car> {
    private final int maxCars;
    ArrayList<T> cars = new ArrayList<>();
    protected String pic;
    int x, y;
    private final String brandName;

    protected AutoRepairShop(int x, int y, int maxCars, String pic, String brandName){
        this.maxCars = maxCars;
        this.pic = pic;
        this.x = x;
        this.y = y;
        this.brandName = brandName;
    }

    public int getMaxCars() {
        return this.maxCars;
    }

    public ArrayList<T> getCars() {
        return cars;
    }

    public int getAmountOfCars() {
        return this.cars.size();
    }

    public String getCarType() {
        return brandName;
    }

    public void addCar(T car) {
        if (this.cars.size() < maxCars) {
            this.cars.add(car);
            return;
        }
        throw new IllegalArgumentException("AutoRepairShop is full! Chill out!");
    }

    public Car removeCar(Car car) {
        if (this.cars.contains(car)) {
            this.cars.remove(car);
            return car;
        }
        throw new IllegalArgumentException("Car is not in the AutoRepairShop object");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPic() {
        return this.pic;
    }

}
