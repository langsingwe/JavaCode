package cc.openhome;

import java.io.Serializable;
import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class TFilesInfo implements Serializable {
    private DataSource dataSource;
    
    public TFilesInfo() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context)
                           initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/demo");
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public List<ColumnInfo> getAllColumnInfo() {
        Connection conn = null;
        ResultSet crs = null;
        SQLException ex = null;
        List<ColumnInfo> infos = null;
        try {
            conn = dataSource.getConnection();
            DatabaseMetaData meta = conn.getMetaData();
            crs = meta.getColumns("demo", null, "t_files", null);
            infos = new ArrayList<ColumnInfo>();
            while(crs.next()) {
                ColumnInfo info = new ColumnInfo();
                info.setName(crs.getString("COLUMN_NAME"));
                info.setType(crs.getString("TYPE_NAME"));
                info.setSize(crs.getInt("COLUMN_SIZE"));
                info.setNullable(crs.getBoolean("IS_NULLABLE"));
                info.setDef(crs.getString("COLUMN_DEF"));
                infos.add(info);
            }
        } catch (SQLException e) {
            ex = e;
        }
        finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    if(ex == null) {
                        ex = e;
                    }
                }
            }
        }
        if(ex != null) {
            throw new RuntimeException(ex);
        }
        
        return infos;
    }
}
