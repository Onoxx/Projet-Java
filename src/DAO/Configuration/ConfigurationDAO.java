package DAO.Configuration;

import MVC.Model.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.ArrayList;

public interface ConfigurationDAO {
    int addConfiguration(Configuration conf);
    void addStorageConfiguration(StorageConfiguration conf);
    void addCoolingConfiguration(CoolingConfiguration conf);
    Configuration getConfiguration(int id);
    ArrayList<Configuration> getConfigurations();
    ArrayList<ConfigurationSearch> searchConfigByUserDateRGB(User user, int age, boolean hasRGB);
    ArrayList<StorageCoolingSearch> searchConfigWithStorageCooling(String storageName, String coolingName, String motherBoard);
}
