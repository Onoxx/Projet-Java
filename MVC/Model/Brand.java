package MVC.Model;

public class Brand {
    private String name;
    private String website;
    private String country;

    public Brand(String name, String website, String country) {
        this.name = name;
        this.website = website;
        this.country = country;
    }

    public String getName() {
        return name;
    }
}
