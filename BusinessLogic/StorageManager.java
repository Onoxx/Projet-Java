package BusinessLogic;

import DAO.Storage.StorageDAO;
import DAO.Storage.StorageDAOImpl;
import MVC.Model.Storage;

import java.util.ArrayList;

public class StorageManager {
    private StorageDAO storageDAO;

    public StorageManager() {
        storageDAO = new StorageDAOImpl();
    }
    public ArrayList<Storage> getAllStorages(){return storageDAO.getAllStorages();};
}
