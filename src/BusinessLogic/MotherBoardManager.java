package BusinessLogic;

import DAO.MotherBoard.MotherBoardDAO;
import DAO.MotherBoard.MotherBoardDAOImpl;
import MVC.Model.MotherBoard;

import java.util.ArrayList;

public class MotherBoardManager {
    private MotherBoardDAO motherBoardDAO;

    public MotherBoardManager() {this.motherBoardDAO = new MotherBoardDAOImpl();}
    public ArrayList<MotherBoard> getAllMotherBoards() {
        return motherBoardDAO.getAllMotherBoards();
    }
    public MotherBoard getMotherBoardByName(String name) {
        return motherBoardDAO.getMotherBoardByName(name);
    }
}
