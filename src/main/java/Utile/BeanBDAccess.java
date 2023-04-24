package Utile;
import java.sql.*;

public class BeanBDAccess {
    private Connection con = null;
    private ResultSet resultat = null;
    private Statement instruc = null;

    public BeanBDAccess() {
    }

    public void connectBD(String DB_name, String Username, String Password){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection(DB_name,Username,Password);
            instruc = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet SelectBD(String requete)
    {
        try {
            resultat = instruc.executeQuery(requete);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultat;
    }

    public void InsertBD(String requete)
    {
        try {
            instruc.executeUpdate(requete);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
