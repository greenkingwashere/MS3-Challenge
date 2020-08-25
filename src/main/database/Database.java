package database;

import java.sql.*;


public class Database {

    private Connection conn;
    private String file;

    public Database(String file) {
        try{
            this.file = file;
            this.conn = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\data\\"+this.file+".db");
            this.clearTable();
            this.createTable();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static boolean assertSqliteIsLoaded(){
        try{
            Connection testConnection = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\data\\test.db");
            return true;
        } catch (SQLException e){
            return false;
        }
    }

    public boolean push(String queryString) {
        try{
            this.conn.createStatement().execute(queryString);
            return true;
        } catch (SQLException e){
            //System.out.println(e.getMessage()+  " query: " + queryString);
            return false;
        }
    }

    public ResultSet pull(String queryString) {
        try{
            return this.conn.createStatement().executeQuery(queryString);
        } catch (SQLException e){
            //System.out.println(e.getMessage() +  " query: " + queryString);
            return null;
        }
    }

    public boolean pushRow(String[] cells){
        String sql = "INSERT INTO csv (A, B, C, D, E, F, G, H, I, J) VALUES ('" + cells[0] + "', '" + cells[1] + "', '" + cells[2] + "', '" + cells[3] + "', '" + cells[4] + "', '" + cells[5] + "', '" + cells[6] + "', '" + cells[7] + "', '" + cells[8] + "', '" + cells[9] + "');";
        return push(sql);
    }

    private boolean createTable(){
        return this.push("CREATE TABLE IF NOT EXISTS csv (A TEXT, B TEXT, C TEXT, D TEXT, E TEXT, F TEXT, G TEXT, H TEXT, I TEXT, J TEXT);");
    }

    public boolean clearTable(){
        return this.push("DELETE FROM csv;");
    }

}
