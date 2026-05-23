package BusinessLogic;

import MVC.Model.Processor;
import DAO.Processor.*;

import java.util.ArrayList;

public class ProcessorManager {
    private ProcessorDAO processorDAO;

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
    public void removeProcessor(String processorName) { processorDAO.removeProcessor(processorName); }
    public Processor getProcessor(String processorName) {return processorDAO.getProcessorByName(processorName);}
    public void updateProcessor(String processorToModify, Processor processor) {processorDAO.updateProcessor(processorToModify, processor);}
}