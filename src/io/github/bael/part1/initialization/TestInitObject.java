package io.github.bael.part1.initialization;

import java.lang.reflect.Field;

public class TestInitObject {
    public static void main(String[] args) {
        System.out.println("Step 1");
        Class<InitObject> initObjectClass = InitObject.class;
        Field[] fields = initObjectClass.getFields();

        System.out.println("Step 2");
        InitObject object = new InitObject(1, 2);
        System.out.println(object);
    }
}
