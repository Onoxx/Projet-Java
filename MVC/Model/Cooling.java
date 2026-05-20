package MVC.Model;

public class Cooling {
    private String name, type, comptabileSocket, brand;
    private int length, height, weight, depth, tdp;
    private double price;

    public Cooling(String name, String type, String comptabileSocket, String brand, int length, int height, int weight, int depth, int tdp, double price) {
        this.name = name;
        this.type = type;
        this.comptabileSocket = comptabileSocket;
        this.brand = brand;
        this.length = length;
        this.height = height;
        this.weight = weight;
        this.depth = depth;
        this.tdp = tdp;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getComptabileSocket() {
        return comptabileSocket;
    }

    public String getBrand() {
        return brand;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getDepth() {
        return depth;
    }

    public int getTdp() {
        return tdp;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString(){
        return name;
    }
}
