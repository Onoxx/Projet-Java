package MVC.Controller;

import BusinessLogic.BrandManager;
import BusinessLogic.ProcessorManager;
import BusinessLogic.SocketManager;
import MVC.Model.Brand;
import MVC.Model.Processor;
import MVC.Model.Socket;

import java.util.ArrayList;

public class ApplicationController {
    private ProcessorManager processorManager;
    private SocketManager socketManager;
    private BrandManager brandManager;

    public ApplicationController() {
        this.processorManager = new ProcessorManager();
        this.socketManager = new SocketManager();
        this.brandManager = new BrandManager();
    }
    public ArrayList<Processor> getAllProcessors() {
        return processorManager.getAllProcessors();
    }

    public void addProcessor(Processor processor) {
        processorManager.addProcessor(processor);
    }
    public ArrayList<Socket> getAllSockets() {
        return socketManager.getAllSockets();
    }

    public ArrayList<Brand> getAllBrands() {
        return brandManager.getAllBrands();
    }
}
