package cc.openhome;

import java.io.Serializable;
import java.sql.*;
import java.util.*;

public class GuestBookBean implements Serializable{
    private String jdbcUrl = "jdbc:mysql://localhost:3306/demo";
    private String username = "root";
    private String password = "123456";
    public GuestBookBean() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void setMessage(Message message) {
        Connection conn = null;
        Statement statement = null;
        SQLException ex = null;
        try {
            /* 完成练习内容 */
        } catch (SQLException e) {
            ex = e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                }
                catch(SQLException e) {
                    if(ex == null) {
                        ex = e;
                    }
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                }
                catch(SQLException e) {
                    if(ex == null) {
                        ex = e;
                    }
                }
            }
            
            if(ex != null) {
                throw new RuntimeException(ex);
            }
        }
    }

    public List<Message> getMessages() {
        Connection conn = null;
        Statement statement = null;
        ResultSet result = null;
        SQLException ex = null;
        List<Message> messages = null;
        try {
            /* 完成练习内容 */
        } catch (SQLException e) {
            ex = e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                }
                catch(SQLException e) {
                    if(ex == null) {
                        ex = e;
                    }
                }
            }
            
            if (conn != null) {
                try {
                    conn.close();
                }
                catch(SQLException e) {
                    if(ex == null) {
                        ex = e;
                    }
                }
            }
            
            if(ex != null) {
                throw new RuntimeException(ex);
            }
        }
        return messages;
    }
}
