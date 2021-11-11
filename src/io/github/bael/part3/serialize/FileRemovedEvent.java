package io.github.bael.part3.serialize;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Serializable маркерный интерфейс, создан в те времена когда не было аннотаций
 */
public class FileRemovedEvent extends BaseEvent implements Serializable {

    protected String fileName = "<Not specified name>";
    protected String filePath;
    {
        System.out.println("Block initialization called!");
        filePath =  "<Blank path>";
    }
    protected Long fileSize;
    /**
     * не храним чувствительную информацию
     */
    protected transient String username;


    public FileRemovedEvent() {
        System.out.println("Constructor called!");
        fileSize = -1L;
        createdOn = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "FileRemovedEvent{" +
                "createdOn=" + createdOn +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
