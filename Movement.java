public class Movement {

    public static void move(Car car, AutoRepairShop workshop) {
        // System.out.println(workshop.getAmountOfCars() + " " + workshop.getMaxCars());
        if (car.isVisible) {
            switch (car.direction) {
                case 0:
                    car.y += car.getCurrentSpeed();
                    break;
                case 1:
                    car.x += car.getCurrentSpeed();
                    break;
                case 2:
                    car.y -= car.getCurrentSpeed();
                    break;
                case 3:
                    car.x -= car.getCurrentSpeed();
                    break;
            }
        }
        borderCollide(car);
        workshopCollide(car, workshop);
    }

    private static void borderCollide(Car car) {
        if (car.x < 0)                       car.x = 1;
        else if (car.x + 100 > CarView.X)    car.x = CarView.X - 101;
        else if (car.y < 0)                  car.y = 1;
        else if (car.y + 60 > CarView.paneY) car.y = CarView.paneY - 61;
        else {
            return;
        }
        collide(car);
        }

    private static void workshopCollide(Car car, AutoRepairShop<Car> workshop) {


        if (car.isVisible && car.x <= workshop.getX() + 101 && workshop.getX() <= car.x + 100 &&
                        car.y <= workshop.getY() + 96 && workshop.getY() <= car.y + 60) {
            if (car.direction == 0) car.y = workshop.getY() - 60;
            else if (car.direction == 1) car.x = workshop.getX() - 100;
            else if (car.direction == 2) car.y = workshop.getY() + 96;
            else if (car.direction == 3) car.x = workshop.getX() + 101;

            if (car.isVisible && workshop.getCarType().equals(car.getModelName()) &&
                    workshop.getAmountOfCars() < workshop.getMaxCars()) {
                car.isVisible = false;
                workshop.addCar(car);
            }
        }
        else {
            return;
        }
        collide(car);
    }

    private static void collide(Car car) {
        car.direction = (car.direction + 2) % 4; // Change direction
        car.stopEngine();
        car.startEngine();
    }
}
