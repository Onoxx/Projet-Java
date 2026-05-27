package MVC.Model;

public class CoolingConfiguration {
    private int quantity;
    private String cooling;
    private int configuration;

    public CoolingConfiguration(int quantity, String cooling, int configuration) {
        this.quantity = quantity;
        this.cooling = cooling;
        this.configuration = configuration;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getCooling() {
        return cooling;
    }
    public int getConfiguration() {return configuration;}
}
