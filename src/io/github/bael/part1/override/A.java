package io.github.bael.part1.override;

import java.io.IOException;

public class A {
    A doSomething(A a) {
        System.out.println("Called doSomething from class A");
        return null;
    }


    void doWithPossibleError() throws IOException {

    }

    static void doStatic() {
        System.out.println("doStatic from A!");
    }

    final void canNotOverride() {
        System.out.println("I'm a final!");
    }
}
