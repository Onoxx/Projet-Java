package MVC.Model;

public class Storage {
    String name, type, interfaceStorage, brand;
    int capacity, writingSpeed, readingSpeed;
    double price;

    public Storage(String name, String type, String interfaceStorage, String brand, int capacity, int writingSpeed, int readingSpeed, double price) {
        this.name = name;
        this.type = type;
        this.interfaceStorage = interfaceStorage;
        this.brand = brand;
        this.capacity = capacity;
        this.writingSpeed = writingSpeed;
        this.readingSpeed = readingSpeed;
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

    public double getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return name;
    }
}
