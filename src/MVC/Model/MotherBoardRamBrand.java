package MVC.Model;

public class MotherBoardRamBrand {
    private String motherBoardName;
    private String motherBoardmodel;
    private int nbRamSlots;
    private String ramType;
    private int ramCapacity;
    private double frequency;
    private String brandName;
    private String website;
    private String country;

    public MotherBoardRamBrand(String motherBoardName, String motherBoardmodel, int nbRamSlots,
                                  String ramType, int ramCapacity, double frequency,
                                  String brandName, String website, String country) {
        this.motherBoardName = motherBoardName;
        this.motherBoardmodel = motherBoardmodel;
        this.nbRamSlots = nbRamSlots;
        this.ramType = ramType;
        this.ramCapacity = ramCapacity;
        this.frequency = frequency;
        this.brandName = brandName;
        this.website = website;
        this.country = country;
    }

    public String getMotherBoardName() {
        return motherBoardName;
    }

    public String getMotherBoardmodel() {
        return motherBoardmodel;
    }

    public int getNbRamSlots() {
        return nbRamSlots;
    }

    public String getRamType() {
        return ramType;
    }

    public int getRamCapacity() {
        return ramCapacity;
    }

    public double getFrequency() {
        return frequency;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getWebsite() {
        return website;
    }

    public String getCountry() {
        return country;
    }
}
