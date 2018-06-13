package doctor.s.assist;
import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {

    public static Connection databaseConnect() {
        Connection dbConnection = null;
        try {
            Class.forName("org.postgresql.Driver");
            dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost:1111/Doctor assist",
                    "postgres", "sams");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbConnection;
    }

}