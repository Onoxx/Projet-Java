package BusinessLogic;

import DAO.Brand.*;
import MVC.Model.Brand;

import java.util.ArrayList;

public class BrandManager {
    private BrandDAO brandDAO;
    public BrandManager() {this.brandDAO = new BrandDAOImpl();}

    public ArrayList<Brand> getAllBrands() {
        return brandDAO.getAllBrands();
    }
}
