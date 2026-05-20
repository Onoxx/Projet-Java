package MVC.Model;

public class GraphicCard {
    private String name;
    private String chipset;
    private int capacity;
    private String vramType;
    private int length;
    private int height;
    private int depth;
    private int tdp;
    private double price;
    private String brand;

    public GraphicCard(String name, String chipset, int capacity, String vramType, int length, int height, int depth, int tdp, double price, String brand) {
        this.name = name;
        this.chipset = chipset;
        this.capacity = capacity;
        this.vramType = vramType;
        this.length = length;
        this.height = height;
        this.depth = depth;
        this.tdp = tdp;
        this.price = price;
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public String getChipset() {
        return chipset;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getVramType() {
        return vramType;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
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

    public String getBrand() {
        return brand;
    }
    @Override
    public String toString() {
        return name;
    }
}
