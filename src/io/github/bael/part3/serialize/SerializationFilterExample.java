package io.github.bael.part3.serialize;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class SerializationFilterExample implements Serializable {

    protected String a = "A";
    protected String b = "B";
    protected transient String c = "C";

    public SerializationFilterExample() {
    }

    protected transient BaseEvent baseEvent;

    /**
     *   private void writeObject(java.io.ObjectOutputStream out)
     *        throws IOException
     *    private void readObject(java.io.ObjectInputStream in)
     *        throws IOException, ClassNotFoundException;
     *    private void readObjectNoData()
     *        throws ObjectStreamException;
     *
     * @param oos
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream oos) throws IOException {
        System.out.println("writeObject(); called" );
        oos.defaultWriteObject();
        oos.writeObject(baseEvent.createdOn);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        System.out.println("readObject(); called" );
        ois.defaultReadObject();
        LocalDateTime createdOn = (LocalDateTime) ois.readObject();
        BaseEvent event = new BaseEvent();
        event.createdOn = createdOn;
        this.baseEvent = event;
    }




    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Class> allowedClasses = Arrays.asList(LocalDateTime.class, SerializationFilterExample.class);
        ObjectInputFilter objectInputFilter = new ObjectInputFilter() {
            @Override
            public Status checkInput(FilterInfo filterInfo) {
                if (filterInfo.serialClass() != null) {
                    if (allowedClasses.stream().noneMatch(allowedClass -> allowedClass.isAssignableFrom(filterInfo.serialClass()))) {
                        System.out.println("Фильтр обнаружил подозрительный класс "  + filterInfo.serialClass() );
                        if (!filterInfo.serialClass().getCanonicalName().equals("java.time.Ser"))
                            return Status.REJECTED;
                    }
                }
//                boolean assignableFrom = getClass().isAssignableFrom(filterInfo.serialClass());
                return Status.UNDECIDED; // assignableFrom ? Status.ALLOWED : Status.REJECTED;
            }
        };

        SerializationFilterExample filterExample1 = new SerializationFilterExample();
        filterExample1.baseEvent = new BaseEvent();
        filterExample1.baseEvent.createdOn = LocalDateTime.now();

        System.out.println(filterExample1);
        String fileName = "FilterExample.bin";
//        System.out.println(Files.readString(Path.of(fileName)));
        Files.deleteIfExists(Path.of(fileName));
        try (var objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            objectOutputStream.writeObject(filterExample1);
        }

        System.out.println("deserialize");
        try (var objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            objectInputStream.setObjectInputFilter(objectInputFilter);
            Object result = objectInputStream.readObject();
            System.out.println(result);
        }
    }

    @Override
    public String toString() {
        return "SerializationFilterExample{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", baseEvent=" + baseEvent +
                '}';
    }
}
