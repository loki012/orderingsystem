package data;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class DBOperations {

    // Connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/food_ordering";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void setDataOrDelete(String query, String msg) {
        try {

            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement st = con.createStatement();

            st.executeUpdate(query);
            if (!msg.equals("")) {
                JOptionPane.showMessageDialog(null, msg);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void executeBatchQueries(List<String> queries, String msg) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            con.setAutoCommit(false);

            Statement st = con.createStatement();

            for (String query : queries) {
                st.executeUpdate(query);
            }

            con.commit();

            if (!msg.isEmpty()) {
                JOptionPane.showMessageDialog(null, msg);
            }

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException rollbackEx) {
                    JOptionPane.showMessageDialog(null, rollbackEx, "Rollback Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex, "Connection Close Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static ResultSet getData(String query) {
        try {
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
