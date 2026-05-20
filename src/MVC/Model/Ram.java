package MVC.Model;

public class Ram {
    private String name, type, brand;
    private int capacity, nbRamSticks, frequency;
    private double price;

    public Ram(String name, String type, String brand, int capacity, int nbRamSticks, int frequency, double price) {
        this.name = name;
        this.type = type;
        this.brand = brand;
        this.capacity = capacity;
        this.nbRamSticks = nbRamSticks;
        this.frequency = frequency;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getNbRamSticks() {
        return nbRamSticks;
    }

    public int getFrequency() {
        return frequency;
    }

    public double getPrice() {
        return price;
    }
    @Override
    public String toString(){
        return name;
    }

}
