package io.github.bael.part3.serialize;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ExampleOne  {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Object> list = new ArrayList<>();
        FileRemovedEvent event = new FileRemovedEvent();
        event.fileName = "winrar.exe";
        list.add(event);

        FileRemovedEvent event2 = new FileRemovedEvent();
        event2.fileName = "access.log";
        list.add(event2);

        String fileName = "./events_removed.bin";
        System.out.println("Start serialize!");

        serialize(list, fileName);

        System.out.println("Start deserialize!");
        List<Object> deserialized = deserialize(fileName, 2);
        System.out.println(deserialized);

    }

    public static void serialize(List<Object> objects, String fileName) throws IOException {
        try (var objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
//            objectOutputStream.write(objects.size());

            for (Object object : objects) {
                objectOutputStream.writeObject(object);
            }
        }
    }

    public static List<Object> deserialize(String fileName, int count) throws IOException, ClassNotFoundException {
        List<Object> objects = new ArrayList<>();
        Path path = Paths.get(fileName);
        File file = new File(fileName);
        System.out.println(file.exists());

        try (var objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {

//            int count = objectInputStream.readInt();
            System.out.println(count);
            while (count-- > 0) {
                objects.add(objectInputStream.readObject());
            }
        }
        return objects;
    }


}
