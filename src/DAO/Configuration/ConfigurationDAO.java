package DAO.Configuration;

import MVC.Model.*;
import java.util.ArrayList;
import java.sql.Date;

public interface ConfigurationDAO {
    int addConfiguration(Configuration conf);
    void addStorageConfiguration(StorageConfiguration conf);
    void addCoolingConfiguration(CoolingConfiguration conf);
    ArrayList<Configuration> getConfigurations();
    ArrayList<ConfigurationSearch> searchConfigByUserDateRGB(User user, Date date, boolean hasRGB);
    ArrayList<StorageCoolingSearch> searchConfigWithStorageCooling(String storageName, String coolingName, String motherBoard);
}
