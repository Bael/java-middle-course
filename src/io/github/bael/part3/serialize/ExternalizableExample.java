package io.github.bael.part3.serialize;


import java.io.*;

/**
 * https://javarush.ru/groups/posts/2023-znakomstvo-s-interfeysom-externalizable
 */
public class ExternalizableExample implements Externalizable {

    private String a;
    private String b;

    public ExternalizableExample() {
    }

    public ExternalizableExample(String a) {
        this.a = a;
        b = "bbbbbb";
    }

    /**
     * полный контроль над сериализацией
     *
     * @param out
     * @throws IOException
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
//        out.writeInt(a.length());
        out.writeObject(a);


//        out.writeInt(b.length());
        out.writeObject(b);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.a = (String) in.readObject();
        this.b = (String) in.readObject();


//        int countA = in.readInt();
//        char[] aArray = new char[countA];
//        for (int i = 0; i < countA; i++) {
//            aArray[i++] = in.readChar();
//        }
//        this.a = new String(aArray);
//
//        int countB = in.readInt();
//        char[] bArray = new char[countB];
//        for (int i = 0; i < countB; i++) {
//            bArray[i++] = in.readChar();
//        }
//        this.b = new String(bArray);

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ExternalizableExample externalizableExample = new ExternalizableExample("aaaaaaaaaaa");

        String fileName = "externalizableExample.bin";

        try (var objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            objectOutputStream.writeObject(externalizableExample);
        }

        try (var objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            Object result = objectInputStream.readObject();
            System.out.println(result);
        }

    }

    @Override
    public String toString() {
        return "ExternalizableExample{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                '}';
    }
}
