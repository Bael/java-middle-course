package io.github.bael.part1.initialization.constructor;

public class A {
    protected int a;
    protected int b;
    protected int c;

    static {
        System.out.println("class A static init");
    }

    {
        System.out.println("class A init!");
    }

    public A() {
        System.out.println("class A empty constructor called!");
    }

    public A(int a, int b, int c) {
        this(a, b);
        System.out.println("A constructor a,b,c called");
        this.c = c;
    }

    public A(int a, int b) {
        this(a);
        this.b = b;
        System.out.println("class A constructor a,b called");
    }

    public A(int a) {
        System.out.println("class A constructor a called");
        this.a = a;
    }

    void call() {
        System.out.println("class A method call called!");
    }
}
