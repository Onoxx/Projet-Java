package DAO.Processor;

import MVC.Model.Processor;

import java.util.ArrayList;

public interface ProcessorDAO {
    ArrayList<Processor> getAllProcessors();
    Processor getProcessorByName(String processorName);
    void updateProcessor(String name, Processor processor);
    void addProcessor(Processor processor);
    void removeProcessor(String name);
}
