package DAO.Configuration;

import MVC.Model.*;

import java.sql.Date;
import java.util.HashMap;

public interface ConfigurationDAO {
    void addConfiguration(Configuration config, HashMap<Storage, Integer> storages, HashMap<Cooling, Integer> coolings);
    int countConfiguration(Date date1, Date date2);
}
