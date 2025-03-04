import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel <ACar extends Car> extends JPanel{

    private ArrayList<ACar> cars;
    private ArrayList<AutoRepairShop> workshops;
    // Just a single image, TODO: Generalize
    // BufferedImage carImage;
    // To keep track of a single car's position
    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage scaniaImage;
    BufferedImage carImage;

    // BufferedImage volvoWorkshopImage;
    // static Point volvoWorkshopPoint = new Point(300,300);
    BufferedImage workshopImage;
    // static Point workshopPoint = new Point(400, 400);

    // TODO: Make this general for all cars
    void updateInfo(ArrayList<ACar> cars, ArrayList<AutoRepairShop> workshops) {
        this.cars = cars;
        this.workshops = workshops;

    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        /*
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
         */

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // lägg till filnamn fält i varje Carobjekt

         // see javadoc for more info on the parameters
        if (!(cars == null)) {
            for (Car car : cars) {
                if (car.isVisible) {
                    try {
                        carImage = ImageIO.read(DrawPanel.class.getResourceAsStream(car.getPic()));
                        g.drawImage(carImage, (int) car.getX(), (int) car.getY(), null);
                    }
                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        if (!(workshops == null)) {
            for (AutoRepairShop workshop: workshops) {
                System.out.println(workshop.getPic());
                if (workshop.getPic() != null) {
                    try {
                        workshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream(workshop.getPic()));
                        g.drawImage(workshopImage, workshop.getX(), workshop.getY(), null);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        // g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
        }
}
