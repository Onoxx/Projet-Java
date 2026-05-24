package DAO.Configuration;

import MVC.Model.*;

import java.util.ArrayList;
import java.util.Date;

public interface ConfigurationDAO {
    int addConfiguration(Configuration conf);
    void addStorageConfiguration(StorageConfiguration conf);
    void addCoolingConfiguration(CoolingConfiguration conf);

    ArrayList<ConfigurationSearch> searchConfigByUserDateRGB(User user, int age, boolean hasRGB);
}
