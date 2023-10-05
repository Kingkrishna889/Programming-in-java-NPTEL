
import java.sql.*;
import java.lang.*;
public class CreateTable {
    public static void main(String args[]) {
        try {
              Connection conn = null;
              Statement stmt = null;
              String DB_URL = "jdbc:sqlite:/tempfs/db";
              System.setProperty("org.sqlite.tmpdir", "/tempfs");

              // Open a connection
              conn = DriverManager.getConnection(DB_URL);
              stmt = conn.createStatement();
              // The statement containing SQL command to create table "players"
              String CREATE_TABLE_SQL="CREATE TABLE players (UID INT, First_Name VARCHAR(45), Last_Name VARCHAR(45), Age INT);";

              // Execute the statement containing SQL command
              stmt.executeUpdate(CREATE_TABLE_SQL);

              //There is some invisible code here
            }
       catch(Exception e){ System.out.println(e);}
    }
}
