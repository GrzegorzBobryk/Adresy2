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

    private void createTable() {
        String createAddress = "CREATE TABLE IF NOT EXISTS adresy (place_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "county varchar(255),road varchar(255),postalcode varchar(255),state varchar(255))";
        try {
            stat.execute(createAddress);
        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
        }
    }


    public void insertAdres(String county, String road, String postalcode, String state) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into adresy values (NULL,?, ?, ?, ?);");
            prepStmt.setString(1, county);
            prepStmt.setString(2, road);
            prepStmt.setString(3, postalcode);
            prepStmt.setString(4, state);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy dodawaniu");
        }
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