package MVC.Model;

public class Case {
    private String name, fomat, brand;
    private double price;
    private int maxLengthGPU,maxHeightVentirad, nbFans;
    private boolean hasRGB;

    public Case(String name, String fomat, String brand, double price, int maxLengthGPU, int maxHeightVentirad, int nbFans, boolean hasRGB) {
        this.name = name;
        this.fomat = fomat;
        this.brand = brand;
        this.price = price;
        this.maxLengthGPU = maxLengthGPU;
        this.maxHeightVentirad = maxHeightVentirad;
        this.nbFans = nbFans;
        this.hasRGB = hasRGB;
    }

    public String getName() {
        return name;
    }

    public String getFomat() {
        return fomat;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public int getMaxLengthGPU() {
        return maxLengthGPU;
    }

    public int getMaxHeightVentirad() {
        return maxHeightVentirad;
    }

    @Override
    public String toString(){
        return name;
    }
}
