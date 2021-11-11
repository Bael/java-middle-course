package io.github.bael.part1.initialization;

public class InitObject {
    //    private static int i = j; // won't compile
    public static int j = 2;
    public static int i = getJ();
    private static int getJ() {
        System.out.println("getJ is called!");
        return j;
    }
    static {
        System.out.println("Called static initialization block for j");
        j = 3;
    }

    private int aFromJ = getJ();

    {
        System.out.println("Init block #1 for a1 is called!");
        a1 = 100;
    }
    private int a1;

    private int a2;

    {
        System.out.println("Init block #2 for a2 is called!");
        // a2 = a3; // won't compile
        a2 = getA3(); // won't compile
        System.out.println("a2 = " + a2);

    }


    private int a3 = 300;
    private int getA3() {
        return a3;
    }

    public InitObject(int a1) {
        System.out.println("Constructor for a1 is called!");
        this.a1 = a1;
    }

    public InitObject(int a1, int a2) {
        this(a1);
        System.out.println("Constructor for a1 and a2 is called!");
        this.a2 = a2;
        // this(a1); won't compile
    }


    @Override
    public String toString() {
        return "InitObject{" +
                "aFromJ=" + aFromJ +
                ", a1=" + a1 +
                ", a2=" + a2 +
                '}';
    }
}
