package edu.icet.hospital_system.DB;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class DBConnection {
    private static DBConnection instance;
    private final Connection connection;
    private DBConnection() throws SQLException {
        String URL="jdbc:mysql://localhost:3306/hospital";
        String userName="root";
        String password="1234";
        connection = DriverManager.getConnection(URL, userName, password);
    }

    public static DBConnection getInstance() throws SQLException {
        return instance==null?instance=new DBConnection():instance;
    }
}
