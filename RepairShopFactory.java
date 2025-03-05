public class RepairShopFactory {

    static AutoRepairShop<Saab95> createSaabWorkshop(int x, int y, int maxCars, String pic, String brandName){
        return new AutoRepairShop<Saab95>(x, y, maxCars, pic, brandName);
    }
    static AutoRepairShop<Volvo240> createVolvoWorkshop(int x, int y, int maxCars, String pic, String brandName){
        return new AutoRepairShop<Volvo240>(x, y, maxCars, pic, brandName);
    }
    static AutoRepairShop<Scania> createScaniaWorkshop(int x, int y, int maxCars, String pic, String brandName){
        return new AutoRepairShop<Scania>(x, y, maxCars, pic, brandName);
    }
}
