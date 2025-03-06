import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Application {
    CarView frame;
    CarController cc;
    static ArrayList<Car> cars;
    static ArrayList<AutoRepairShop> workshops;
    protected static final int X = 1000;
    protected static final int Y = 800;
    protected static final int paneY = Y - 240;
    private final int delay = 20;
    private Timer timer = new Timer(delay, new TimerListener());

    Application(){
        cc = new CarController();
        frame = new CarView("CarSim");
        cars = new ArrayList<>();
        workshops = new ArrayList<>();
        frame.add(cc.gasPanel);
        frame.add(cc.controlPanel);
        frame.add(cc.turnPanel);
        frame.add(cc.startButton);
        frame.add(cc.stopButton);
    }
    public static void main(String[] args) {
        Application app = new Application();
        app.cars.add(CarFactory.createVolvo240(200,0));
        app.cars.add(CarFactory.createScania(300, 0));
        app.cars.add(CarFactory.createSaab95(100, 0));
        app.workshops.add(RepairShopFactory.createSaabWorkshop(200, 350, 2));
        app.workshops.add(RepairShopFactory.createScaniaWorkshop(450, 350, 1));
        app.workshops.add(RepairShopFactory.createVolvoWorkshop(700, 350, 1));
        app.timer.start();
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
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
}
