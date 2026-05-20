package DAO.MotherBoard;

import MVC.Model.MotherBoard;

import java.util.ArrayList;

public interface MotherBoardDAO {
    ArrayList<MotherBoard> getAllMotherBoards();
    MotherBoard getMotherBoardByName(String name);
}
