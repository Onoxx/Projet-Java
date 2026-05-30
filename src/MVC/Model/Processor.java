package MVC.Model;

import java.util.Date;

public class Processor {
    private String name, socket, brand;
    private int nbCores, tdp;
    private Integer nbThreads;
    private double baseFrequence, price;
    private Double boostFrequence;
    private boolean hasGPU;
    private Date releaseDate;

    public Processor(String name, String socket, String brand, int nbCores, Integer nbThreads, int tdp, double baseFrequence, Double boostFrequence, double price, boolean hasGPU, Date releaseDate) {
        this.name = name;
        this.socket = socket;
        this.brand = brand;
        this.nbCores = nbCores;
        this.nbThreads = nbThreads;
        this.tdp = tdp;
        this.baseFrequence = baseFrequence;
        this.boostFrequence = boostFrequence;
        this.price = price;
        this.hasGPU = hasGPU;
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public String getSocket() {
        return socket;
    }

    public String getBrand() {
        return brand;
    }

    public int getNbCores() {
        return nbCores;
    }

    public Integer getNbThreads() {
        return nbThreads;
    }

    public int getTdp() {
        return tdp;
    }

    public double getBaseFrequence() {
        return baseFrequence;
    }

    public Double getBoostFrequence() {
        return boostFrequence;
    }

    public double getPrice() {
        return price;
    }

    public boolean isHasGPU() {
        return hasGPU;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
    @Override
    public String toString(){
        return name;
    }
}
