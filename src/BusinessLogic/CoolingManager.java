package BusinessLogic;

import DAO.Cooling.CoolingDAO;
import DAO.Cooling.CoolingDAOImpl;
import MVC.Model.Cooling;

import java.util.ArrayList;

public class CoolingManager {
    private CoolingDAO coolingDAO;
    public CoolingManager() {coolingDAO = new CoolingDAOImpl();}
    public ArrayList<Cooling> getAllCoolings() {return coolingDAO.getAllCoolings();}
}
