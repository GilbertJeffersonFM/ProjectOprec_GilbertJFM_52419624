package javaderbygui;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JavaDerbyGUI {
    public static void main(String[] args) {
        //Initialize JFrame
        JFrame jframe = new JFrame();
        
        //Display JFrame
        jframe.setVisible(true);
        
        
        // Create a connection to a Derby database
        String url = "jdbc:derby:/Documents/NetBeansProjects/JavaDerbyGUI/saved data/item.db;create=true";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            return;
        }
        
        //Create and Save Data on Local Hardrive
        createTable(conn);
    }
    
    private static void createTable(Connection conn) {
        try (PreparedStatement stmt = conn.prepareStatement("CREATE TABLE item (itemName VARCHAR(50), itemPrice INTEGER)")) {
            stmt.executeUpdate();
            System.out.println("Table created successfully.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) {
                System.out.println("Table already exists.");
            }else{
                System.out.println("Error creating table: " + e.getMessage());
            }
        }
    }
}
