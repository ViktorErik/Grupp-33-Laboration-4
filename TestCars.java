import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TestCars {

    //<editor-fold desc="------------------------Volvo240Tests----------------------------------">
    @Test
    void speedTestV() {
        Volvo240 v = new Volvo240();
        v.gas(1);
        assertEquals(1.25, v.getCurrentSpeed());
        assertThrows(IllegalArgumentException.class, () -> v.gas(2));
        v.brake(1);
        assertEquals(0, v.getCurrentSpeed());
    }
    @Test
    void positionTestV() {
        Volvo240 v = new Volvo240();
        v.move();
        assertEquals(0, v.x);
        assertEquals(0, v.y);
        v.gas(1);
        v.move();
        assertEquals(0, v.x);
        assertEquals(1.25, v.y);
        v.turnRight();
        v.move();
        assertEquals(1.25, v.x);
        assertEquals(1.25, v.y);
        v.turnRight();
        v.move();
        assertEquals(1.25, v.x);
        assertEquals(0, v.y);
    }
    @Test
    void testCurrentSpeedV() {
        // Checks that speed can't go beyond the car's engine power
        // and that using Car.gas won't decrease the speed
        Volvo240 v = new Volvo240();
        double tempSpeed = v.getCurrentSpeed();
        for (int n = 0; n < 1000; n++) {
            v.gas(1);
        }
        assertTrue(tempSpeed <= v.getCurrentSpeed());
        assertTrue(v.getCurrentSpeed() <= v.getEnginePower());
    }
    //</editor-fold>

    //<editor-fold desc="------------------------Sabb95Tests------------------------------------">
    @org.junit.jupiter.api.Test
    void speedTestSB() {
        Saab95 s = new Saab95();
        s.gas(1);
        assertEquals(1.25, s.getCurrentSpeed());
        assertThrows(IllegalArgumentException.class, () -> s.gas(2));
        s.brake(1);
        assertEquals(0, s.getCurrentSpeed());
    }

    @org.junit.jupiter.api.Test
    void positionTestSB() {
        Saab95 s = new Saab95();
        s.move();
        assertEquals(0, s.x);
        assertEquals(0, s.y);
        s.gas(1);
        s.move();
        assertEquals(0, s.x);
        assertEquals(1.25, s.y);
        s.turnRight();
        s.move();
        assertEquals(1.25, s.x);
        assertEquals(1.25, s.y);
        s.turnRight();
        s.move();
        assertEquals(1.25, s.x);
        assertEquals(0, s.y);
    }

    @org.junit.jupiter.api.Test
    void testCurrentSpeedSB() {
        // Checks that speed can't go beyond the car's engine power
        // and that using Car.gas won't decrease the speed
        Saab95 s = new Saab95();
        double tempSpeed = s.getCurrentSpeed();
        for (int n = 0; n < 1000; n++) {
            s.gas(1);
        }
        assertTrue(tempSpeed <= s.getCurrentSpeed());
        assertTrue(s.getCurrentSpeed() <= s.getEnginePower());
    }
    //</editor-fold>

    //<editor-fold desc="------------------------ScaniaTests------------------------------------">
    @org.junit.jupiter.api.Test
    void testAngleSC() {
        Scania s = new Scania();
        double tempAngle = s.getFlatbedAngle();

        assertThrows(InternalError.class, () -> s.tiltFlatbed(-1));

        assertThrows(InternalError.class, () -> s.tiltFlatbed(91));
        s.tiltFlatbed(45);
        assertEquals(45,s.getFlatbedAngle());
    }
    @org.junit.jupiter.api.Test
    void testNoTiltMovingSC() {
        Scania s = new Scania();
        s.startEngine();
        s.gas(1);
        assertThrows(InternalError.class, () -> s.tiltFlatbed(45));
    }

    @org.junit.jupiter.api.Test
    void testNoMoveTilting() {
        Scania s = new Scania();
        s.stopEngine();
        s.tiltFlatbed(45);
        assertThrows(InternalError.class, () -> s.startEngine());
        s.tiltFlatbed(0);
        s.startEngine();
        s.gas(1);
        s.stopEngine();
        double tempAngle = s.getFlatbedAngle();
        s.tiltFlatbed(45);
        assertNotEquals(tempAngle, s.getFlatbedAngle());
    }
    //</editor-fold>

    //<editor-fold desc="------------------------CarTransportTruckTests-------------------------">

    @org.junit.jupiter.api.Test
    void testLoadNonLoadable() {
        CarTransportTruck CT1 = new CarTransportTruck();
        CarTransportTruck CT2 = new CarTransportTruck();
        Scania S = new Scania();
        assertThrows(InternalError.class, () -> CT1.loadCar(CT2));
        assertThrows(InternalError.class, () -> CT1.loadCar(S));
    }
    @org.junit.jupiter.api.Test
    void testTiltWhileMoving(){
        CarTransportTruck ct = new CarTransportTruck();
        ct.startEngine();
        ct.gas(1);
        assertThrows(InternalError.class, () -> ct.tiltFlatbed(true));
    }
    @org.junit.jupiter.api.Test
    void testMoveWhileTilted(){
        CarTransportTruck ct = new CarTransportTruck();
        ct.tiltFlatbed(true);
        assertThrows(InternalError.class, () -> ct.startEngine());
        assertThrows(InternalError.class, () -> ct.gas(1));
    }
    @org.junit.jupiter.api.Test
    void testLoadDownAndClose(){
        CarTransportTruck ct = new CarTransportTruck();
        Volvo240 v = new Volvo240();
        ct.tiltFlatbed(true);
        ct.loadCar(v);
        assertNotEquals(0,ct.getLoadedCars().size());

        ct.disengageCar();
        ct.tiltFlatbed(false);
        ct.startEngine();
        ct.gas(1);
        ct.gas(1);
        ct.gas(1);
        ct.move();
        ct.stopEngine();
        ct.tiltFlatbed(true);
        assertThrows(InternalError.class,() ->ct.loadCar(v));
    }
    @org.junit.jupiter.api.Test
    void testDisengageWhileMoving(){
        CarTransportTruck ct = new CarTransportTruck();
        Volvo240 v = new Volvo240();
        ct.tiltFlatbed(true);
        ct.loadCar(v);
        ct.tiltFlatbed(false);
        assertThrows(InternalError.class, () -> ct.disengageCar());
        ct.tiltFlatbed(true);
        ct.disengageCar();
        assertEquals(ct.x - ct.xRange, v.x);
    }
    @org.junit.jupiter.api.Test
    void testDisengageInOrder() {
        CarTransportTruck ct = new CarTransportTruck();
        Volvo240 v1 = new Volvo240();
        Saab95 s = new Saab95();
        Volvo240 v2 = new Volvo240();

        ct.tiltFlatbed(true);
        ct.loadCar(v1);
        ct.loadCar(s);
        ct.loadCar(v2);

        assertEquals(v2, ct.getLoadedCars().peek());
        ct.disengageCar();
        assertEquals(s, ct.getLoadedCars().peek());
        ct.disengageCar();
        assertEquals(v1, ct.getLoadedCars().peek());
        ct.disengageCar();
    }
    @org.junit.jupiter.api.Test
    void testTransportedCarXandY(){
        CarTransportTruck ct = new CarTransportTruck();
        Volvo240 v = new Volvo240();

        ct.tiltFlatbed(true);
        ct.loadCar(v);
        ct.tiltFlatbed(false);
        ct.startEngine();
        ct.gas(1);
        ct.move();
        assertEquals(ct.x, v.x);
        assertEquals(ct.y, v.y);
    }
    @org.junit.jupiter.api.Test
    void testGasCarWhileLoaded(){
        CarTransportTruck ct = new CarTransportTruck();
        Volvo240 v = new Volvo240();

        ct.tiltFlatbed(true);
        ct.loadCar(v);
        ct.tiltFlatbed(false);
        assertThrows(IllegalArgumentException.class,() ->v.gas(1));
    }
    //</editor-fold>

    //<editor-fold desc="------------------------AutoRepairShopTests----------------------------">
    @org.junit.jupiter.api.Test
    void testAddCarAdds() {
        AutoRepairShop<Volvo240> autoRepairShop = new AutoRepairShop<>(5);
        assertDoesNotThrow(() -> autoRepairShop.addCar(new Volvo240()));
    }
    @org.junit.jupiter.api.Test
    void testRemoveNonExistentCar() {
        AutoRepairShop<Volvo240> autoRepairShop = new AutoRepairShop<>(5);
        Volvo240 v1 = new Volvo240();
        Volvo240 v2 = new Volvo240();
        autoRepairShop.addCar(v1);

        assertThrows(InternalError.class, () -> autoRepairShop.getCar(v2));

    }
    //</editor-fold>
}
