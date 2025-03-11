import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel <ACar extends Car> extends JPanel{

    private ArrayList<String> carPicNames;
    private ArrayList<Integer> carXs;
    private ArrayList<Integer> carYs;
    private ArrayList<String> workshopPicNames;
    private ArrayList<Integer> workshopXs;
    private ArrayList<Integer> workshopYs;
    // Just a single image, TODO: Generalize
    // BufferedImage carImage;
    // To keep track of a single car's position
    BufferedImage carImage;
    BufferedImage workshopImage;

    // TODO: Make this general for all cars

    void updateCarInfo(ArrayList<Integer> xs, ArrayList<Integer> ys, ArrayList<String> carPicNames) {
        this.carXs = xs;
        this.carYs = ys;
        this.carPicNames = carPicNames;
    }

    void updateWorkshopInfo(ArrayList<Integer> xs, ArrayList<Integer> ys, ArrayList<String> workshopPicNames) {
        this.workshopXs = xs;
        this.workshopYs = ys;
        this.workshopPicNames = workshopPicNames;
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

         */

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // lägg till filnamn fält i varje Carobjekt

         // see javadoc for more info on the parameters
        if (!(carPicNames == null)) {
            for (int i = 0; i < this.carPicNames.size(); i++) {
                int x = this.carXs.get(i);
                int y = this.carYs.get(i);
                String picName = this.carPicNames.get(i);
                try {
                    carImage = ImageIO.read(DrawPanel.class.getResourceAsStream(picName));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g.drawImage(carImage, (int) x, (int) y, null);
            }
        }
            if (!(workshopPicNames == null)) {
                for (int i = 0; i < this.workshopPicNames.size(); i++) {
                    int x = this.workshopXs.get(i);
                    int y = this.workshopYs.get(i);
                    String picName = this.workshopPicNames.get(i);
                    try {
                        workshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream(picName));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    g.drawImage(workshopImage, x, y, null);

                }
            }
        }
}
