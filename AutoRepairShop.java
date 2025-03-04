import java.util.ArrayList;

public class AutoRepairShop<T extends Car> {
    private final int maxCars;
    ArrayList<T> cars = new ArrayList<>();
    protected String pic;
    int x, y;

    protected AutoRepairShop(int x, int y, int maxCars, String pic){
        this.maxCars = maxCars;
        this.pic = pic;
        this.x = x;
        this.y = y;
    }

    public int getMaxCars() {
        return this.maxCars;
    }

    public void addCar(T car) {
        if (cars.size() < maxCars) {
            cars.add(car);
            return;
        }
        throw new IllegalArgumentException("AutoRepairShop is full! Chill out!");
    }

    public T getCar(T car) {
        if (cars.contains(car)) {
            cars.remove(car);
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

    public static void main(String[] args) {
        /*
        AutoRepairShop<Volvo240> rs = new AutoRepairShop<>(100);
        rs.addCar(new Volvo240());
        Volvo240 volvo = new Volvo240();
        rs.addCar(new Volvo240());
        System.out.println(rs.getCar(volvo) instanceof Volvo240); // true
        // Dessa ger errors förty rs är av typen Volvo240
        // rs.addCar(new CarTransportTruck());
        // rs.addCar(new Saab95());

        // Denna AutoRepairShop tar vilka cars som helst
        AutoRepairShop<Car> rs2 = new AutoRepairShop<>(100);
        rs2.addCar(new Volvo240());
        Saab95 saab = new Saab95();
        rs2.addCar(saab);
        System.out.println(rs2.getCar(saab) instanceof Volvo240); // false

         */
    }
}
