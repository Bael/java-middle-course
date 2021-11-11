package io.github.bael.part1.override;

import java.io.EOFException;

/**
 * переопределение старается соблюдать принцип Лисков для
 * возвращаемых параметров, контролируемых исключений, и области видимости.
 * т.е. подклассы могут уточнять (наследовать) типы возвращаемых параметров и контролируемых исключений и расширять область видимости.
 * либо оставить все как есть
 */
public class B extends A {

    @Override
    A doSomething(A b) {
        System.out.println("Called doSomething from class B");
        return null;
    }


//    also valid
//    @Override
//    B doSomething(A a) {
//        return null;
//    }


//    @Override
//    void doWithPossibleError() throws IOException {
//
//    }

    // also valid (LISKOV)
    @Override
    void doWithPossibleError() throws EOFException {

    }

//    @Override ERROR can't override and can't hide
//    void canNotOverride() {
//        System.out.println("I'm a final!");
//    }


    // @Override is not valid. it's hiding

    static void doStatic() {
        System.out.println("doStatic from B!");
    }

    public static void main(String[] args) {
        // dynamic dispatch/ late binding
        // virtual method is called
        A a = new B();
        a.doSomething(a);
        System.out.println(a);

    }
}
