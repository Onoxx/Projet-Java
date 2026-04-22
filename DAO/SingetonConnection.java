package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingetonConnection {
    private static Connection uniqueConnection;
    public static Connection getInstance(){
        if(uniqueConnection == null) {
            try{
                uniqueConnection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/configurator",
                        "root",
                        "+sV*4-mySQLAcc-+sV*4"
                );
            }catch(Exception e){
                throw new RuntimeException("Erreur de connexion DB");
            }
        }
        return uniqueConnection;
    }
}
