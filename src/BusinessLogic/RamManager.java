package BusinessLogic;

import DAO.Ram.RamDAO;
import DAO.Ram.RamDAOImpl;
import MVC.Model.Ram;

import java.util.ArrayList;

public class RamManager {
    private RamDAO ramDAO;
    public RamManager() {this.ramDAO = new RamDAOImpl();}
    public ArrayList<Ram> getAllRams(){
        return ramDAO.getAllRams();
    }
    public Ram getRamByName(String name){return ramDAO.getRamByName(name);}
}
