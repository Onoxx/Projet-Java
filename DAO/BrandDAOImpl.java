package DAO;

import Exceptions.AllComponentsException;
import MVC.Model.Brand;
import MVC.Model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BrandDAOImpl implements BrandDAO {

    @Override
    public ArrayList<Brand> getAllBrands() {
        String querry = "SELECT * FROM brand";
        ArrayList<Brand> brands = new ArrayList<>();
        Connection connection = SingletonConnection.getInstance();
        try{
            PreparedStatement statement = connection.prepareStatement(querry);

            ResultSet data = statement.executeQuery();
            while(data.next()){
                String name = data.getString("name");
                String website = data.getString("website");
                String country = data.getString("country");

                Brand brand = new Brand(name, website, country);
                brands.add(brand);
            }
        }catch(SQLException e){
            throw new AllComponentsException("marques");
        }
        return brands;
    }
}
