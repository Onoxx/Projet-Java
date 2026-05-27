package BusinessLogic;

import DAO.Configuration.ConfigurationDAO;
import DAO.Configuration.ConfigurationDAOImpl;
import Exceptions.IncompatibleComponentException;
import Exceptions.InvalidQuantityException;
import MVC.Model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class ConfigurationManager{
    private ConfigurationDAO configurationDAO;

    public ConfigurationManager() {
        this.configurationDAO = new ConfigurationDAOImpl();
    }

    public int addConfiguration(Configuration conf) {
        return configurationDAO.addConfiguration(conf);
    }
    public void addStorageConfiguration(StorageConfiguration storageConfiguration) {
        configurationDAO.addStorageConfiguration(storageConfiguration);
    }
    public void addCoolingConfiguration(CoolingConfiguration coolingConfiguration) {
        configurationDAO.addCoolingConfiguration(coolingConfiguration);
    }
    public double computeTotalPrice(Configuration config) {
        double totalPrice = 0;

        if (config.getProcessor() != null)    totalPrice += config.getProcessor().getPrice();
        if (config.getMotherBoard() != null)  totalPrice += config.getMotherBoard().getPrice();
        if (config.getRam() != null)          totalPrice += config.getRam().getPrice();
        if (config.getComputerCase() != null) totalPrice += config.getComputerCase().getPrice();
        if (config.getGraphicCard() != null)  totalPrice += config.getGraphicCard().getPrice();

        for (HashMap.Entry<Storage, Integer> entry : config.getStorages().entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        for (HashMap.Entry<Cooling, Integer> entry : config.getCoolings().entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }

        return totalPrice;
    }
    public ArrayList<Configuration> getConfigurations() {
        return configurationDAO.getConfigurations();
    }
    public void verifyConfiguration(Configuration configuration, HashMap<Storage, Integer> storages, HashMap<Cooling, Integer> coolings) {
        Processor currentProcessor = configuration.getProcessor();
        GraphicCard currentGraphicCard = configuration.getGraphicCard();
        Case computerCase = configuration.getComputerCase();
        MotherBoard currentMotherBoard = configuration.getMotherBoard();
        Ram currentRam = configuration.getRam();
        if(currentProcessor != null && currentMotherBoard != null
                && !Objects.equals(currentProcessor.getSocket(), currentMotherBoard.getSocket())){
            throw new IncompatibleComponentException("socket","processeur","carte mère");
        }
        if(currentGraphicCard != null && computerCase != null
                && currentGraphicCard.getLength() > computerCase.getMaxLengthGPU()){
            throw new IncompatibleComponentException("longueur","carte graphique","boitier");
        }
        if(computerCase != null && currentMotherBoard != null
                && !Objects.equals(computerCase.getFomat(), currentMotherBoard.getFormat())){
            throw new IncompatibleComponentException("fomat","boitier","carte mère");
        }
        if(currentMotherBoard != null && currentRam != null
                && !Objects.equals(currentMotherBoard.getRamType(), currentRam.getType())){
            throw new IncompatibleComponentException("type de ram","carte mère","ram");
        }
        for(HashMap.Entry<Cooling, Integer> entry : coolings.entrySet()) {
            Cooling cooling = entry.getKey();
            int quantity = entry.getValue();
            if(quantity < 1){
                throw new InvalidQuantityException(quantity);
            }
            if(currentProcessor != null &&
                    !Objects.equals(
                            cooling.getComptabileSocket(),
                            currentProcessor.getSocket())){
                throw new IncompatibleComponentException(
                        "socket",
                        "refroidissement",
                        "processeur"
                );
            }
            if(computerCase != null &&
                    cooling.getHeight() > computerCase.getMaxHeightVentirad()){
                throw new IncompatibleComponentException("taille","refroidissement","boitier");
            }
        }
        for(HashMap.Entry<Storage, Integer> entry : storages.entrySet()) {
            int quantity = entry.getValue();
            if(quantity < 1){
                throw new InvalidQuantityException(quantity);
            }
        }
    }
}

