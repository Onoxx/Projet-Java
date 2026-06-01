package MVC.Model;

public class MotherBoard {
    private String name, model, socket, format, ramType, brand;
    private int nbRamSlots;
    private double price;

    public MotherBoard(String name, String model, String socket, String format, String ramType, String brand, int nbRamSlots, double price) {
        this.name = name;
        this.model = model;
        this.socket = socket;
        this.format = format;
        this.ramType = ramType;
        this.brand = brand;
        this.nbRamSlots = nbRamSlots;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getSocket() {
        return socket;
    }

    public String getFormat() {
        return format;
    }

    public String getRamType() {
        return ramType;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }
    @Override
    public String toString(){
        return name;
    }
}