import java.awt.*;

// i tiltFlatbed - throw error om knasigt argument

public class Scania extends Car implements NonLoadable{
    private double flatbedAngle = 0;
    private final double trimfactor = 1.01;

    protected Scania() {
        super(2, Color.black, 94, "Scania", 0, 0, "pics/Scania.jpg");
    }
    protected Scania(int x, int y) {
        super(2, Color.black, 94, "Scania", x, y, "pics/Scania.jpg");
    }

    public void tiltFlatbed(double angle) {
        if (getCurrentSpeed() > 0) {
            throw new IllegalArgumentException("Cannot tilt flatbed while driving.");
        }
        else if (angle > 70 || angle < 0) {
            throw new IllegalArgumentException("Angle out of range (0-70)");
        }
        flatbedAngle = angle;
    }

    public double getFlatbedAngle() {
        return flatbedAngle;
    }

    @Override
    public void startEngine() {
        if (flatbedAngle == 0) {
            super.startEngine();
            return;
        }
        throw new IllegalArgumentException("Can't start engine with flatbed tilted");
    }

    // You can increase speed by just calling gas
    // (without having used startEngine) for some reason
    // so gas has to be overridden too I think
    @Override
    public void gas(double amount) {
        if (flatbedAngle == 0) {
            super.gas(amount);
            return;
        }
        throw new IllegalArgumentException("Can't gas with flatbed tilted");
    }

    @Override
    protected double speedFactor() {
        return enginePower * trimfactor * 0.02;
    }
}