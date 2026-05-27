package MVC.Model;

public class StorageConfiguration {
    private int quantity;
    private String storage;
    private int configuration;

    public StorageConfiguration(int quantity, String storage, int configuration) {
        this.quantity = quantity;
        this.storage = storage;
        this.configuration = configuration;
    }

    public int getQuantity() {
        return quantity;
    }
    public String getStorage() {
        return storage;
    }
    public int getConfiguration(){return configuration;}
}
