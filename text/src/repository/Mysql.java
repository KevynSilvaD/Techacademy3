package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysql {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://192.168.1.188:3306/kevyn_silva",
                    "kevyn_silva",
                    "12345"
            );
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao tentar connectar com o banco.");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao tentar importar o driver mysql");
        }
        return null;
    }
}
