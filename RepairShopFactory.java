public class RepairShopFactory {

    static AutoRepairShop<Saab95> createSaabWorkshop(int x, int y, int maxCars){
        return new AutoRepairShop<Saab95>(x, y, maxCars, "pics/SaabBrand.jpg", "Saab95");
    }
    static AutoRepairShop<Volvo240> createVolvoWorkshop(int x, int y, int maxCars){
        return new AutoRepairShop<Volvo240>(x, y, maxCars, "pics/VolvoBrand.jpg", "Volvo240");
    }
    static AutoRepairShop<Scania> createScaniaWorkshop(int x, int y, int maxCars, String pic, String brandName){
        return new AutoRepairShop<Scania>(x, y, maxCars, pic, brandName);
    }
}
