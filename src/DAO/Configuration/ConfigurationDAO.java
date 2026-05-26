package DAO.Configuration;

import MVC.Model.Configuration;
import MVC.Model.CoolingConfiguration;
import MVC.Model.StorageConfiguration;

import java.sql.Date;

public interface ConfigurationDAO {
    int addConfiguration(Configuration conf);
    void addStorageConfiguration(StorageConfiguration conf);
    void addCoolingConfiguration(CoolingConfiguration conf);
    int countConfiguration(Date date1, Date date2);
}
