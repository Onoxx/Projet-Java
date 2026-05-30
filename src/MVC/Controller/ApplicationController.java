package MVC.Controller;

import BusinessLogic.*;
import MVC.Model.*;

import java.util.ArrayList;

public class ApplicationController {
    private ProcessorManager processorManager;
    private SocketManager socketManager;
    private BrandManager brandManager;
    private GraphicCardManager graphicCardManager;
    private CaseManager caseManager;
    private MotherBoardManager motherBoardManager;
    private RamManager ramManager;
    private UserManager userManager;
    private StorageManager storageManager;
    private CoolingManager coolingManager;
    private ConfigurationManager configurationManager;

    public ApplicationController() {
        this.processorManager = new ProcessorManager();
        this.socketManager = new SocketManager();
        this.brandManager = new BrandManager();
        this.graphicCardManager = new GraphicCardManager();
        this.caseManager = new CaseManager();
        this.motherBoardManager = new MotherBoardManager();
        this.ramManager = new RamManager();
        this.userManager = new UserManager();
        this.storageManager = new StorageManager();
        this.coolingManager = new CoolingManager();
        this.configurationManager = new ConfigurationManager();
    }

    public void addProcessor(Processor processor) {
        processorManager.addProcessor(processor);
    }
    public void removeProcessor(String processorName) {
        processorManager.removeProcessor(processorName);
    }
    public void updateProcessor(String processorToModify, Processor processor) {processorManager.updateProcessor(processorToModify, processor);}

    public ArrayList<Processor> getAllProcessors() {
        return processorManager.getAllProcessors();
    }
    public ArrayList<Socket> getAllSockets() {
        return socketManager.getAllSockets();
    }
    public ArrayList<Brand> getAllBrands() {
        return brandManager.getAllBrands();
    }
    public ArrayList<GraphicCard> getAllGraphicCards() {return graphicCardManager.getAllGraphicCards();}
    public ArrayList<Case> getAllCases() {return caseManager.getAllCases();}
    public ArrayList<MotherBoard> getAllMotherBoards() {return motherBoardManager.getAllMotherBoards();}
    public ArrayList<MotherBoardRamBrand> searchByFormatRamMaxPrice(
            String format, String ramName, double maxPrice) {
        return motherBoardManager.searchByFormatRamMaxPrice(format, ramName, maxPrice);
    }
    public ArrayList<Ram> getallRams() {return ramManager.getAllRams();}
    public ArrayList<User> getAllUsers(){return userManager.getAllUsers();}
    public ArrayList<Storage> getAllStorages(){return storageManager.getAllStorages();}
    public ArrayList<Cooling> getAllCoolings(){return coolingManager.getAllCoolings();}

    public Processor getProcessor(String processorName) {
        return processorManager.getProcessor(processorName);
    }

    public int addConfiguration(Configuration configuration) { return configurationManager.addConfiguration(configuration);}
    public void addStorageConfiguration(StorageConfiguration storageConfiguration){configurationManager.addStorageConfiguration(storageConfiguration);}
    public void addCoolingConfiguration(CoolingConfiguration coolingConfiguration){configurationManager.addCoolingConfiguration(coolingConfiguration);}
    public double computeTotalPrice(Configuration config){
        return configurationManager.computeTotalPrice(config);
    }
    public ArrayList<Configuration> getConfigurations(){
        return configurationManager.getConfigurations();
    }
    public ArrayList<ConfigurationSearch> searchConfigByUserDateRGB(User user, int age, boolean hasRGB) {
        return configurationManager.searchConfigByUserDateRGB(user, age, hasRGB);
    }
    public ArrayList<StorageCoolingSearch> searchConfigWithStorageCooling(String storageName, String coolingName, String motherBoard) {
        return configurationManager.searchConfigWithStorageCooling(storageName, coolingName, motherBoard);
    }
}