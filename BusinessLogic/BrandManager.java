package BusinessLogic;

import DAO.BrandDAOImpl;
import MVC.Model.Brand;

import java.util.ArrayList;

public class BrandManager {
    private BrandDAOImpl brandDAO;
    public BrandManager() {this.brandDAO = new BrandDAOImpl();}

    public ArrayList<Brand> getAllBrands() {
        ArrayList<Brand> brands = brandDAO.getAllBrands();
        return brands;
    }
}
