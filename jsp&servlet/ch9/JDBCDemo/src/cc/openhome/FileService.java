package cc.openhome;


import java.sql.*;
import java.util.*;
import javax.sql.DataSource;
import javax.naming.*;

public class FileService {
    private DataSource dataSource;
    
    public FileService() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context)
                           initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/demo");
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public File getFile(File file) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        SQLException ex = null;
        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement("SELECT filename, bytes FROM t_files WHERE id=?");
            statement.setLong(1, file.getId());
            result = statement.executeQuery();
            while (result.next()) {
                file = new File();
                file.setFilename(result.getString(1));
                file.setBytes(result.getBytes(2));
            }
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
        return file;
    }
    
    public List<File> getFileList() {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        SQLException ex = null;
        List<File> fileList = null;
        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement("SELECT id, filename, savedTime FROM t_files");
            result = statement.executeQuery();
            fileList = new ArrayList<File>();
            while (result.next()) {
                File file = new File();
                file.setId(result.getLong(1));
                file.setFilename(result.getString(2));
                file.setSavedTime(result.getTimestamp(3));
                fileList.add(file);
            }
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
        return fileList;
    }
    
    public void save(File file) {
        Connection conn = null;
        PreparedStatement statement = null;
        SQLException ex = null;
        try {
            conn = dataSource.getConnection();           
            statement = conn.prepareStatement("INSERT INTO t_files(filename, savedTime, bytes) VALUES(?, ?, ?)");
            statement.setString(1, file.getFilename());
            statement.setTimestamp(2, new Timestamp(file.getSavedTime().getTime()));
            statement.setBytes(3, file.getBytes());
            statement.executeUpdate();
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
    
    public void delete(File file) {
        Connection conn = null;
        PreparedStatement statement = null;
        SQLException ex = null;
        try {
            conn = dataSource.getConnection();           
            statement = conn.prepareStatement("DELETE FROM t_files WHERE id=?");
            statement.setLong(1, file.getId());
            statement.executeUpdate();
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
}
