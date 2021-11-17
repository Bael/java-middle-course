package io.github.bael.part3.serialize;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ExampleBigFiles {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileRemovedEvent event = new FileRemovedEvent();
        Path path2Logs = Paths.get("/home/dk/work/jetalon_logs/jetalabor/hibernate.log");
        System.out.println("Files.size(path2Logs) = " + Files.size(path2Logs));
        event.filePath = Files.readString(path2Logs);
        String fileName = "big.bin";
        try (var objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            objectOutputStream.writeObject(event);
        }

        try (var ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            FileRemovedEvent event1 = (FileRemovedEvent) ois.readObject();
//            System.out.println(event1);
        }

    }
}
