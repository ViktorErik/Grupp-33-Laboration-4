public class Movement {

    public void move(Car car) {
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
        borderCollide(car);
        workshopCollide(car);
    }

    private void borderCollide(Car car) {
        collide(car);
        if (car.x < 0) car.x = 1;
        if (car.x + 100 > CarView.X) car.x = CarView.X - 101;
        if (car.y < 0) car.y = 1;
        if (car.y + 60 > CarView.paneY) car.y = CarView.paneY - 61;
        }

    private void workshopCollide(Car car) {
        if (car.isVisible && car instanceof Volvo240) {
            car.isVisible = false;
        }

        if (car.direction == 0) car.y = DrawPanel.volvoWorkshopPoint.getY() - 60;
        if (car.direction == 1) car.x = DrawPanel.volvoWorkshopPoint.getX() - 100;
        if (car.direction == 2) car.y = DrawPanel.volvoWorkshopPoint.getY() + 96;
        if (car.direction == 3) car.x = DrawPanel.volvoWorkshopPoint.getX() + 101;
        collide(car);
    }

    private void collide(Car car) {
        car.direction = (car.direction + 2) % 4; // Change direction
        car.stopEngine();
        car.startEngine();
    }
}
