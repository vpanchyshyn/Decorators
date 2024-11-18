package ua.edu.ucu.apps.decorator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lombok.SneakyThrows;

public class DBConnection {

    public static DBConnection dbConnection;
    private Connection connection;

    @SneakyThrows
    private DBConnection() {
        this.connection = DriverManager
            .getConnection("jdbc:sqlite:/Users/vladapanchyshyn/cache.db");
    }

    @SneakyThrows
    public String getDocument(String gcsPath) {
        PreparedStatement statement 
            = connection.prepareStatement("SELECT * FROM document WHERE path=?");
        statement.setString(1, gcsPath);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.getString("parsed");
        
    }

    @SneakyThrows
    public void createDocument(String gcsPath, String parsed) {
        PreparedStatement preparedstatement 
            = connection.prepareStatement("INSERT INTO document (path, parsed) VALUES (?, ?)");
        preparedstatement.setString(1, gcsPath);
        preparedstatement.setString(2, parsed);
        preparedstatement.executeUpdate();
        preparedstatement.close();
    }



    public static DBConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }
}

