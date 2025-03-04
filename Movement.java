public class Movement {

    public static void move(Car car) {
        System.out.println(car.x + " " + car.y + " " + car.getCurrentSpeed());
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
        workshopCollide(car);
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

    private static void workshopCollide(Car car) {

        if (car.isVisible && car.x <= DrawPanel.volvoWorkshopPoint.getX() + 101 && DrawPanel.volvoWorkshopPoint.getX() <= car.x + 100 &&
                        car.y <= DrawPanel.volvoWorkshopPoint.getY() + 96 && DrawPanel.volvoWorkshopPoint.getY() <= car.y + 60) {
            if (car.direction == 0) car.y = DrawPanel.volvoWorkshopPoint.getY() - 60;
            else if (car.direction == 1) car.x = DrawPanel.volvoWorkshopPoint.getX() - 100;
            else if (car.direction == 2) car.y = DrawPanel.volvoWorkshopPoint.getY() + 96;
            else if (car.direction == 3) car.x = DrawPanel.volvoWorkshopPoint.getX() + 101;

            if (car.isVisible && car instanceof Volvo240) {
                car.isVisible = false;
            }
        }
        else {
            return;
        }
        collide(car);
    }

    private static void collide(Car car) {
        System.out.println("HEJS");
        car.direction = (car.direction + 2) % 4; // Change direction
        car.stopEngine();
        car.startEngine();
    }
}
