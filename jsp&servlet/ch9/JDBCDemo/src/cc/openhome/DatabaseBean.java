package cc.openhome;

import java.io.Serializable;import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;

public class DatabaseBean implements Serializable {
    private DataSource dataSource;

    public DatabaseBean() {
        /* 完成练习内容 */
    }

    public boolean isConnectedOK() {
        boolean ok = false;
        Connection conn = null;
        SQLException ex = null;
        try {
            /* 完成练习内容 */
            if (!conn.isClosed()) {
                ok = true;
            }
        } catch (SQLException e) {
            ex = e;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    if(ex == null) {
                        ex = e;
                    }
                }
            }
            if(ex != null) {
                throw new RuntimeException(ex);
            }
        }
        return ok;
    }
}
