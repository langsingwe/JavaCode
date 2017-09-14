package cc.openhome;

import java.io.Serializable;
import java.util.Date;

public class File implements Serializable {
    private Long id;
    private String filename;
    private Date savedTime;
    private byte[] bytes;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public Date getSavedTime() {
        return savedTime;
    }
    public void setSavedTime(Date savedTime) {
        this.savedTime = savedTime;
    }
    public byte[] getBytes() {
        return bytes;
    }
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
