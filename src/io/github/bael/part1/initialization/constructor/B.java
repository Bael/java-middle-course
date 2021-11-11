package io.github.bael.part1.initialization.constructor;

public class B extends A {

    static {
        System.out.println("class B static init");
    }

    {
        System.out.println("class B init!");
    }

    public B(int a) {
//        super(a);
        System.out.println("class B constructor a called!");
        this.a = a * 2;
    }

    public B(int a, int b, int c) {
        super(a, b);
        System.out.println("class B constructor a, b, c called!");
        this.c = c * 2;
    }

    public B(int a, int b) {
        this(a);
        System.out.println("class B constructor a, b called!");
        this.b = b;
    }


    private int d;

    public B(int a, int b, int c, int d) {
        // this(a); won't compile
        super(a, b, c);
        this.d = d;
        System.out.println("class B constructor a, b, c, d called!");
    }

    @Override
    public String toString() {
        return "B{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                '}';
    }
}
