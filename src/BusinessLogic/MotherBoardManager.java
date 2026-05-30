package BusinessLogic;

import DAO.MotherBoard.MotherBoardDAO;
import DAO.MotherBoard.MotherBoardDAOImpl;
import MVC.Model.MotherBoard;
import MVC.Model.MotherBoardRamBrand;

import java.util.ArrayList;

public class MotherBoardManager {
    private MotherBoardDAO motherBoardDAO;

    public MotherBoardManager() {this.motherBoardDAO = new MotherBoardDAOImpl();}
    public ArrayList<MotherBoard> getAllMotherBoards() {
        return motherBoardDAO.getAllMotherBoards();
    }
    public ArrayList<MotherBoardRamBrand> searchByFormatRamMaxPrice(
            String format,
            String ramName,
            double maxPrice
    ) {
        return motherBoardDAO.searchByFormatRamMaxPrice(format, ramName, maxPrice);
    }
}
