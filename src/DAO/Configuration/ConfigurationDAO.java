package DAO.Configuration;

import MVC.Model.Configuration;
import MVC.Model.CoolingConfiguration;
import MVC.Model.StorageConfiguration;

public interface ConfigurationDAO {
    int addConfiguration(Configuration conf);
    void addStorageConfiguration(StorageConfiguration conf);
    void addCoolingConfiguration(CoolingConfiguration conf);
}
