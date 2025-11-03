package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/modelo_relacional";
    private static final String USER = "root";
    private static final String PASSWORD = "Temibebe1030";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar: " + e.getMessage());
            return null;
        }
    }
}
