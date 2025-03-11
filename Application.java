import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Application {
    CarView frame;
    CarController cc;
    static ArrayList<Car> cars;
    static ArrayList<AutoRepairShop> workshops = new ArrayList<>();
    protected static final int X = 1000;
    protected static final int Y = 800;
    protected static final int paneY = Y - 240;
    private final int delay = 20;
    private Timer timer = new Timer(delay, new TimerListener());
    private static ArrayList<Integer> carXs = new ArrayList<>();
    private static ArrayList<Integer> carYs = new ArrayList<>();
    private static ArrayList<String> carPicNames = new ArrayList<>();
    private static ArrayList<Integer> workshopXs = new ArrayList<>();
    private static ArrayList<Integer> workshopYs = new ArrayList<>();
    private static ArrayList<String> workshopPicNames = new ArrayList<>();

    Application(){
        cc = new CarController();
        frame = new CarView("CarSim");
        // cars = new ArrayList<>();
        workshops = new ArrayList<>();
        frame.add(cc.gasPanel);
        frame.add(cc.controlPanel);
        frame.add(cc.turnPanel);
        frame.add(cc.startButton);
        frame.add(cc.stopButton);
        frame.drawPanel.updateWorkshopInfo(workshopXs, workshopYs, workshopPicNames);
    }
    public static void main(String[] args) {
        Application app = new Application();
        workshops.add(RepairShopFactory.createVolvoWorkshop(700, 350, 1));
        workshops.add(RepairShopFactory.createSaabWorkshop(200, 300, 2));
        app.timer.start();
    }

    private static void resetData() {
        carPicNames.clear();
        carXs.clear();
        carYs.clear();
        workshopPicNames.clear();
        workshopXs.clear();
        workshopYs.clear();
    }

    // Update Car and Workshop data that DrawPanel can use
    // instead of using the Car and Workshop objects
    // this removes dependency from DrawPanel to Car and AutoRepairShop
    // Keep this method in here instead of CarManager to keep Controller thin
    public static void getNewInfo(ArrayList<Car> cars) {

        resetData();
        for (Car car : cars) {
            if (car.isVisible) {
                carPicNames.add(car.getPic());
                carXs.add((int) car.getX());
                carYs.add((int) car.getY());
            }
        }
        for (AutoRepairShop workshop : workshops) {
            workshopXs.add(workshop.getX());
            workshopYs.add(workshop.getY());
            workshopPicNames.add(workshop.getPic());
        }
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // We have to call a function to update cars
            // in the game loop:
            cars = CarManager.updateCars();
            getNewInfo(cars); // Update names positional values
            for (Car car : cars) {
                for (AutoRepairShop workshop : workshops) {
                    car.move(workshop);
                }
            }
            // Notify DrawPanel with new information without making
            // DrawPanel dependent on Car
            if (!(carPicNames == null)) {
                frame.drawPanel.updateCarInfo(carXs, carYs, carPicNames);
            }
            // repaint() calls the paintComponent method of the panel
            frame.drawPanel.repaint();
        }
    }
}
