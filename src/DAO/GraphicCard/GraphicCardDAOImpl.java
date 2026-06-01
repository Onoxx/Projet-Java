package DAO.GraphicCard;

import DAO.SingletonConnection;
import Exceptions.AllComponentsException;
import Exceptions.FailedToGetComponentException;
import MVC.Model.GraphicCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GraphicCardDAOImpl implements GraphicCardDAO {
    @Override
    public ArrayList<GraphicCard> getAllGraphicCards() {
        String querry = "SELECT * FROM graphiccard";
        ArrayList<GraphicCard> graphicCards = new ArrayList<>();
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement statement = connection.prepareStatement(querry);

            ResultSet data = statement.executeQuery(querry);
            while(data.next()){
                String name = data.getString("name");
                String chipset = data.getString("chipset");
                int capacity = data.getInt("capacity");
                String vramType = data.getString("vramType");
                int length = data.getInt("length");
                int height = data.getInt("height");
                int depth = data.getInt("depth");
                int tdp = data.getInt("tdp");
                double price = data.getDouble("price");
                String brand = data.getString("brand");

                GraphicCard graphicCard = new GraphicCard(name, chipset, capacity, vramType, length, height, depth, tdp, price, brand);
                graphicCards.add(graphicCard);
            }
        }catch(SQLException e){
            throw new AllComponentsException("cartes graphiques");
        }
        return graphicCards;
    }
}
