package DAO.Case;

import MVC.Model.Case;

import java.util.ArrayList;

public interface CaseDAO {
    ArrayList<Case> getAllCases();
    Case getCaseByName(String name);
}
