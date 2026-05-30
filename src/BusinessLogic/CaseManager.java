package BusinessLogic;

import DAO.Case.CaseDAO;
import DAO.Case.CaseDAOImpl;
import MVC.Model.Case;

import java.util.ArrayList;

public class CaseManager {
    private CaseDAO caseDAO;

    public CaseManager() {
        this.caseDAO = new CaseDAOImpl();
    }
    public ArrayList<Case> getAllCases() {
        return caseDAO.getAllCases();
    }
}
