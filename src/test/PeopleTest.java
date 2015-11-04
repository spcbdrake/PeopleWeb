import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

/**
 * Created by benjamindrake on 11/4/15.
 */
public class PeopleTest {
    public Connection startConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./test");
        People.createTable(conn);
        return conn;
    }

    public void endConnection(Connection conn) throws SQLException {
        Statement stmt =  conn.createStatement();
        stmt.execute("DROP TABLE people");
        conn.close();
    }

    @Test
    public void peopleTest() throws SQLException {
        Connection conn = startConnection();
        People.insertPerson(conn, "Ben","Drake", "uwotm8@getrekt.com", "USA", "12345.67890");
        Person person = People.selectPerson(conn, 1);
        endConnection(conn);

        assertTrue(person != null);
    }
}