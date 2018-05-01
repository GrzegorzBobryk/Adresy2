import java.sql.*;


public class InsertDataInToDB {

    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:adresy.db";

    private Connection conn;
    private Statement stat;

    public InsertDataInToDB() {
        try {
            Class.forName(InsertDataInToDB.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }

        createTable();
    }

    private boolean createTable() {
        String createAddress = "CREATE TABLE IF NOT EXISTS adresy (place_id INTEGER PRIMARY KEY, display_name varchar(255))";
        try {
            stat.execute(createAddress);
        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean insertAdres(int id, String name) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into adresy values (?, ?);");
            prepStmt.setInt(1, id);
            prepStmt.setString(2, name);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy dodawaniu");
            return false;
        }
        return true;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }
}