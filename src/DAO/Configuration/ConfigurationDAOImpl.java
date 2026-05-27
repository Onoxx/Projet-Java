package DAO.Configuration;

import DAO.SingletonConnection;
import Exceptions.FailedToAddComponentException;
import Exceptions.FailedToGetComponentException;
import MVC.Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ConfigurationDAOImpl implements ConfigurationDAO {
    @Override
    public int addConfiguration(Configuration conf) {
        String querryAddConfig =
                "INSERT INTO configuration " +
                        "(user, processor, graphicCard, computerCase, motherBoard, ram, creationDate) " +
                        "VALUES (?,?,?,?,?,?,?)";
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement ps = connection.prepareStatement(
                    querryAddConfig,
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            ps.setInt(1,conf.getUser().getId());
            ps.setString(2,
                    conf.getProcessor() != null
                            ? conf.getProcessor().getName()
                            : null);

            ps.setString(3,
                    conf.getGraphicCard() != null
                            ? conf.getGraphicCard().getName()
                            : null);

            ps.setString(4,
                    conf.getComputerCase() != null
                            ? conf.getComputerCase().getName()
                            : null);

            ps.setString(5,
                    conf.getMotherBoard() != null
                            ? conf.getMotherBoard().getName()
                            : null);

            ps.setString(6,
                    conf.getRam() != null
                            ? conf.getRam().getName()
                            : null);

            ps.setDate(7, new Date(conf.getDate().getTime()));

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            return rs.next() ? rs.getInt(1) : -1;
        }catch(SQLException e){
            throw new FailedToAddComponentException("configuration");
        }
    }
    @Override
    public void addStorageConfiguration(StorageConfiguration conf) {
        String addStorageQuerry = "INSERT INTO storageConfiguration (quantity, storage, configuration) VALUES (?, ?, ?)";
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement ps = connection.prepareStatement(addStorageQuerry);
            ps.setInt(1,conf.getQuantity());
            ps.setString(2,conf.getStorage());
            ps.setInt(3,conf.getConfiguration());

            ps.executeUpdate();
        }catch(SQLException ex){
            throw new FailedToAddComponentException("configuration");
        }
    }
    @Override
    public void addCoolingConfiguration(CoolingConfiguration conf) {
        String addCoolingQuerry = "INSERT INTO coolingConfiguration (quantity, cooling, configuration) VALUES (?, ?, ?)";
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement ps = connection.prepareStatement(addCoolingQuerry);
            ps.setInt(1,conf.getQuantity());
            ps.setString(2,conf.getCooling());
            ps.setInt(3,conf.getConfiguration());

            ps.executeUpdate();
        }catch(SQLException ex){
            throw new FailedToAddComponentException("configuration");
        }
    }

    @Override
    public ArrayList<ConfigurationSearch> searchConfigByUserDateRGB(User user, int age, boolean hasRGB) {
        String querry = "SELECT c.id AS configurationId, p.name AS processorName, p.nbCores, p.baseFrequence, p.boostFrequence, g.name AS graphicCardName, g.chipset, g.capacity AS vRamCapacity, g.vRamType, r.name AS ramName, r.capacity, r.nbRamSticks, r.type AS ramType FROM configuration c JOIN processor p ON c.processor = p.name JOIN graphicCard g ON c.graphicCard = g.name JOIN ram r ON c.ram = r.name JOIN user u ON c.user = u.id JOIN computercase cc ON c.computerCase = cc.name WHERE u.name = ? AND c.creationDate <= DATE_SUB(NOW(), INTERVAL ? MONTH) AND cc.hasRGB = ?";
        ArrayList<ConfigurationSearch> configurationSearches = new ArrayList<>();
        Connection connection = SingletonConnection.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, age);
            preparedStatement.setBoolean(3, hasRGB);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int configurationId = resultSet.getInt("configurationId");

                String processorName = resultSet.getString("processorName");
                int nbCores = resultSet.getInt("nbCores");
                double baseFrequence = resultSet.getDouble("baseFrequence");
                double boostFrequence = resultSet.getDouble("boostFrequence");

                String graphicCardName = resultSet.getString("graphicCardName");
                String chipset = resultSet.getString("chipset");
                int vRamCapacity = resultSet.getInt("vRamCapacity");
                String vRamType = resultSet.getString("vRamType");

                String ramName = resultSet.getString("ramName");
                int capacity = resultSet.getInt("capacity");
                int nbRamSticks = resultSet.getInt("nbRamSticks");
                String ramType = resultSet.getString("ramType");

                ConfigurationSearch configurationSearch = new ConfigurationSearch(
                        configurationId,
                        processorName,
                        nbCores,
                        baseFrequence,
                        boostFrequence,
                        graphicCardName,
                        chipset,
                        vRamCapacity,
                        vRamType,
                        ramName,
                        capacity,
                        nbRamSticks,
                        ramType
                );
                configurationSearches.add(configurationSearch);
            }
        } catch (SQLException ex) {
            throw new FailedToGetComponentException("configuration");
        }
        return configurationSearches;
    }

    @Override
    public ArrayList<StorageCoolingSearch> searchConfigWithStorageCooling(String storageName, String coolingName, String motherBoard) {
        StringBuilder querryBuild = new StringBuilder("SELECT c.id AS configurationId, c.user as userId, c.creationDate, s.capacity AS storageCapacity, s.readingSpeed, s.writingSpeed, co.type AS coolingType, co.length as coolingLength, co.height as coolingHeight FROM configuration c JOIN storageConfiguration sc ON c.id = sc.configuration JOIN storage s ON sc.storage = s.name JOIN coolingConfiguration cc ON c.id = cc.configuration JOIN cooling co ON cc.cooling = co.name JOIN motherboard mb ON c.motherBoard = mb.name WHERE mb.name = ?");
        if (!storageName.isEmpty()) {
            querryBuild.append(" AND s.name = ?");
        }
        if (!coolingName.isEmpty()) {
            querryBuild.append(" AND co.name = ?");
        }
        String querry = querryBuild.toString();
        ArrayList<StorageCoolingSearch> storageCoolingSearches = new ArrayList<>();
        Connection connection = SingletonConnection.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            int index = 1;

            preparedStatement.setString(index++, motherBoard);

            if (!storageName.isEmpty()) {
                preparedStatement.setString(index++, storageName);
            }

            if (!coolingName.isEmpty()) {
                preparedStatement.setString(index++, coolingName);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int configurationId = resultSet.getInt("configurationId");
                int userId = resultSet.getInt("userId");
                Date creationDate = resultSet.getDate("creationDate");

                int storageCapacity = resultSet.getInt("storageCapacity");
                int readingSpeed = resultSet.getInt("readingSpeed");
                int writingSpeed = resultSet.getInt("writingSpeed");

                String coolingType = resultSet.getString("coolingType");
                int coolingLength = resultSet.getInt("coolingLength");
                int coolingHeight = resultSet.getInt("coolingHeight");

                StorageCoolingSearch storageCoolingSearch = new StorageCoolingSearch(
                        configurationId,
                        userId,
                        creationDate,
                        storageCapacity,
                        readingSpeed,
                        writingSpeed,
                        coolingType,
                        coolingLength,
                        coolingHeight
                );
                storageCoolingSearches.add(storageCoolingSearch);
            }
        } catch (SQLException ex) {
            throw new FailedToGetComponentException("configuration");
        }
        return storageCoolingSearches;
    }
}
    @Override
    public Configuration getConfiguration(int id) {
        return null;
    }
    @Override
    public ArrayList<Configuration> getConfigurations() {
        String query =
                "SELECT c.id, c.creationDate, " +
                        "u.id AS userId, u.name AS userName, u.email, " +
                        "p.name AS pName, p.socket AS pSocket, p.brand AS pBrand, p.nbCores, p.nbThreads, p.tdp AS pTdp, p.baseFrequence, p.boostFrequence, p.price AS pPrice, p.hasGPU, p.releaseDate, " +
                        "g.name AS gName, g.chipset, g.capacity AS gCapacity, g.vRamType, g.length AS gLength, g.height AS gHeight, g.depth AS gDepth, g.tdp AS gTdp, g.price AS gPrice, g.brand AS gBrand, " +
                        "cc.name AS ccName, cc.format, cc.price AS ccPrice, cc.maxLengthGPU, cc.maxHeightVentirad, cc.nbFans, cc.hasRGB, cc.brand AS ccBrand, " +
                        "m.name AS mName, m.model, m.socket AS mSocket, m.format AS mFormat, m.ramType, m.brand AS mBrand, m.nbRamSlots, m.price AS mPrice, " +
                        "r.name AS rName, r.type AS rType, r.brand AS rBrand, r.capacity AS rCapacity, r.nbRamSticks, r.frequency, r.price AS rPrice " +
                        "FROM configuration c " +
                        "JOIN user u ON c.user = u.id " +
                        "LEFT JOIN processor p ON c.processor = p.name " +
                        "LEFT JOIN graphicCard g ON c.graphicCard = g.name " +
                        "LEFT JOIN computerCase cc ON c.computerCase = cc.name " +
                        "LEFT JOIN motherBoard m ON c.motherBoard = m.name " +
                        "LEFT JOIN ram r ON c.ram = r.name";

        Connection connection = SingletonConnection.getInstance();
        ArrayList<Configuration> configurations = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("c.id");
                User user = new User(rs.getInt("userId"), rs.getString("userName"), rs.getString("email"));

                Processor processor = null;
                if (rs.getString("pName") != null) {
                    processor = new Processor(
                            rs.getString("pName"), rs.getString("pSocket"), rs.getString("pBrand"),
                            rs.getInt("nbCores"), rs.getInt("nbThreads") == 0 ? null : rs.getInt("nbThreads"),
                            rs.getInt("pTdp"), rs.getDouble("baseFrequence"),
                            rs.getDouble("boostFrequence") == 0 ? null : rs.getDouble("boostFrequence"),
                            rs.getDouble("pPrice"), rs.getBoolean("hasGPU"),
                            rs.getDate("releaseDate")
                    );
                }

                GraphicCard graphicCard = null;
                if (rs.getString("gName") != null) {
                    graphicCard = new GraphicCard(
                            rs.getString("gName"), rs.getString("chipset"), rs.getInt("gCapacity"),
                            rs.getString("vRamType"), rs.getInt("gLength"), rs.getInt("gHeight"),
                            rs.getInt("gDepth"), rs.getInt("gTdp"), rs.getDouble("gPrice"),
                            rs.getString("gBrand")
                    );
                }

                Case computerCase = null;
                if (rs.getString("ccName") != null) {
                    computerCase = new Case(
                            rs.getString("ccName"), rs.getString("format"), rs.getString("ccBrand"),
                            rs.getDouble("ccPrice"), rs.getInt("maxLengthGPU"),
                            rs.getInt("maxHeightVentirad"), rs.getInt("nbFans"), rs.getBoolean("hasRGB")
                    );
                }

                MotherBoard motherBoard = null;
                if (rs.getString("mName") != null) {
                    motherBoard = new MotherBoard(
                            rs.getString("mName"), rs.getString("model"), rs.getString("mSocket"),
                            rs.getString("mFormat"), rs.getString("ramType"),
                            rs.getString("mBrand"), rs.getInt("nbRamSlots"), rs.getDouble("mPrice")
                    );
                }

                Ram ram = null;
                if (rs.getString("rName") != null) {
                    ram = new Ram(
                            rs.getString("rName"), rs.getString("rType"), rs.getString("rBrand"),
                            rs.getInt("rCapacity"), rs.getInt("nbRamSticks"),
                            rs.getInt("frequency"), rs.getDouble("rPrice")
                    );
                }

                HashMap<Storage, Integer> storages = getStoragesForConfig(id, connection);
                HashMap<Cooling, Integer> coolings = getCoolingsForConfig(id, connection);

                configurations.add(new Configuration(
                        user, processor, graphicCard, computerCase,
                        motherBoard, ram, storages, coolings,
                        rs.getDate("c.creationDate")
                ));
            }
        } catch (SQLException e) {
            throw new FailedToGetComponentException("configurations");
        }
        return configurations;
    }
    private HashMap<Storage, Integer> getStoragesForConfig(int configId, Connection connection) throws SQLException {
        String query =
                "SELECT s.name, s.type, s.interface AS sInterface, s.brand AS sBrand, " +
                        "s.capacity, s.writingSpeed, s.readingSpeed, s.price AS sPrice, sc.quantity " +
                        "FROM storageConfiguration sc " +
                        "JOIN storage s ON sc.storage = s.name " +
                        "WHERE sc.configuration = ?";
        HashMap<Storage, Integer> storages = new HashMap<>();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, configId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Storage storage = new Storage(
                    rs.getString("name"), rs.getString("type"), rs.getString("sInterface"),
                    rs.getString("sBrand"), rs.getInt("capacity"),
                    rs.getInt("writingSpeed"), rs.getInt("readingSpeed"), rs.getDouble("sPrice")
            );
            storages.put(storage, rs.getInt("quantity"));
        }
        return storages;
    }
    private HashMap<Cooling, Integer> getCoolingsForConfig(int configId, Connection connection) throws SQLException {
        String query =
                "SELECT co.name, co.type AS coType, co.compatibleSocket, co.brand AS coBrand, " +
                        "co.length, co.height, co.weight, co.depth, co.tdp, co.price AS coPrice, coc.quantity " +
                        "FROM coolingConfiguration coc " +
                        "JOIN cooling co ON coc.cooling = co.name " +
                        "WHERE coc.configuration = ?";
        HashMap<Cooling, Integer> coolings = new HashMap<>();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, configId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Cooling cooling = new Cooling(
                    rs.getString("name"), rs.getString("coType"), rs.getString("compatibleSocket"),
                    rs.getString("coBrand"), rs.getInt("length"), rs.getInt("height"),
                    rs.getInt("weight"), rs.getInt("depth"), rs.getInt("tdp"), rs.getDouble("coPrice")
            );
            coolings.put(cooling, rs.getInt("quantity"));
        }
        return coolings;
    }
}