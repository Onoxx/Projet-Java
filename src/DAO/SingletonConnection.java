package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import io.github.cdimascio.dotenv.Dotenv;

public class SingletonConnection {
    private static Connection uniqueConnection;
    public static Connection getInstance(){
        if(uniqueConnection == null) {
            try{
                Dotenv dotenv = Dotenv.load();
                uniqueConnection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/configurator",
                        dotenv.get("HOST_DB"),
                        dotenv.get("API_KEY")
                );
            }catch(Exception e){
                throw new RuntimeException("Erreur de connexion DB");
            }
        }
        return uniqueConnection;
    }
}
