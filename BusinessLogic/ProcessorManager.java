package BusinessLogic;

import DAO.ProcessorDAOImpl;
import MVC.Model.Processor;
import MVC.Model.Socket;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class ProcessorManager {
    private ProcessorDAOImpl processorDAO;

    public ProcessorManager() {
        this.processorDAO = new ProcessorDAOImpl();
    }

    public ArrayList<Processor> getAllProcessors() {
        ArrayList<Processor> processorsList = processorDAO.getAllProcessors();
        return processorsList;
    }
    public void addProcessor(Processor processor) {
        processorDAO.addProcessor(processor);
    }
}
