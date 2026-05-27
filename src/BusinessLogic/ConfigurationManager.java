package BusinessLogic;

import DAO.Configuration.ConfigurationDAO;
import DAO.Configuration.ConfigurationDAOImpl;
import MVC.Model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ConfigurationManager {
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
    public ArrayList<ConfigurationSearch> searchConfigByUserDateRGB(User user, int age, boolean hasRGB) {
        return configurationDAO.searchConfigByUserDateRGB(user, age, hasRGB);
    }

    public ArrayList<StorageCoolingSearch> searchConfigWithStorageCooling(String storageName, String coolingName, String motherBoard) {
        return configurationDAO.searchConfigWithStorageCooling(storageName, coolingName, motherBoard);
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

}

