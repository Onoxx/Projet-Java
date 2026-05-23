package DAO.MotherBoard;

import MVC.Model.MotherBoard;
import MVC.Model.MotherBoardRamBrand;

import java.util.ArrayList;

public interface MotherBoardDAO {
    ArrayList<MotherBoard> getAllMotherBoards();

    MotherBoard getMotherBoardByName(String name);

    ArrayList<MotherBoardRamBrand> searchByFormatRamMaxPrice(
            String format,
            String ramName,
            double maxPrice
    );
}