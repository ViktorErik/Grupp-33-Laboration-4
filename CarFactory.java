public class CarFactory{

    static Saab95 createSaab95(int x, int y){
        return new Saab95(x, y);
    }
    static Volvo240 createVolvo240(int x, int y){
        return new Volvo240(x, y);
    }
    static Scania createScania(int x, int y){
        return new Scania(x, y);
    }
}
