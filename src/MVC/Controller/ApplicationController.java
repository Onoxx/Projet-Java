package MVC.Controller;

import BusinessLogic.*;
import MVC.Model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
    public ArrayList<Ram> getallRams() {return ramManager.getAllRams();}
    public ArrayList<User> getAllUsers(){return userManager.getAllUsers();}
    public ArrayList<Storage> getAllStorages(){return storageManager.getAllStorages();}
    public ArrayList<Cooling> getAllCoolings(){return coolingManager.getAllCoolings();}

    public Processor getProcessor(String processorName) {
        return processorManager.getProcessor(processorName);
    }
    public void addConfiguration(Configuration config, HashMap<Storage, Integer> storages, HashMap<Cooling, Integer> coolings){
        configurationManager.addConfiguration(config, storages, coolings);
    }
    public int countConfiguration(Date date1, Date date2){
        return configurationManager.countConfiguration(date1, date2);
    }
}