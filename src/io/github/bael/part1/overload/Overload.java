package io.github.bael.part1.overload;

/**
 * Перегрузка методов в java
 * If more than one member method is both accessible and applicable to a method invocation,
 * it is necessary to choose one to provide the descriptor for the run-time method dispatch.
 * The Java programming language uses the rule that the most specific method is chosen.
 */
public class Overload {
    public void method(Object o) {
        System.out.println("Object");
    }

    public void method(java.io.FileNotFoundException f) {
        System.out.println("FileNotFoundException");
    }

    public void method(java.io.IOException i) {
        System.out.println("IOException");
    }

    public void a(int a, long b) {

    }

//    public void a(Long b, Integer a) {
//
//    }

    public static void main(String args[]) {
        Overload test = new Overload();
        test.method(null);
        test.a(2, 3);
    }

}