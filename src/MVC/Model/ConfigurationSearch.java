package MVC.Model;

public class ConfigurationSearch {
    private int configurationId;

    private String processorName;
    private int nbCores;
    private double baseFrequence;
    private double boostFrequence;
    private String graphicCardName;
    private String chipset;
    private int vRamCapacity;
    private String vRamType;
    private String ramName;
    private int capacity;
    private int nbRamSticks;
    private String ramType;

    public ConfigurationSearch(int configurationId, String processorName, int nbCores, double baseFrequence, double boostFrequence, String graphicCardName, String chipset, int vRamCapacity, String vRamType, String ramName, int capacity, int nbRamSticks, String ramType) {
        this.configurationId = configurationId;
        this.processorName = processorName;
        this.nbCores = nbCores;
        this.baseFrequence = baseFrequence;
        this.boostFrequence = boostFrequence;
        this.graphicCardName = graphicCardName;
        this.chipset = chipset;
        this.vRamCapacity = vRamCapacity;
        this.vRamType = vRamType;
        this.ramName = ramName;
        this.capacity = capacity;
        this.nbRamSticks = nbRamSticks;
        this.ramType = ramType;
    }

    public int getConfigurationId() {
        return configurationId;
    }

    public String getProcessorName() {
        return processorName;
    }

    public int getNbCores() {
        return nbCores;
    }

    public double getBaseFrequence() {
        return baseFrequence;
    }

    public double getBoostFrequence() {
        return boostFrequence;
    }

    public String getGraphicCardName() {
        return graphicCardName;
    }

    public String getChipset() {
        return chipset;
    }

    public int getvRamCapacity() {
        return vRamCapacity;
    }

    public String getvRamType() {
        return vRamType;
    }

    public String getRamName() {
        return ramName;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getNbRamSticks() {
        return nbRamSticks;
    }

    public String getRamType() {
        return ramType;
    }
}
